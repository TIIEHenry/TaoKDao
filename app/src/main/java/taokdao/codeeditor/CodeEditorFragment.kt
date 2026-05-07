package taokdao.codeeditor

import android.graphics.drawable.ColorDrawable
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import taokdao.api.data.bean.Properties
import taokdao.api.event.senders.ContentSender
import taokdao.api.file.util.FileUtils
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.action.MainAction
import taokdao.api.main.action.MainActionObserver
import taokdao.api.setting.theme.ThemeParts
import taokdao.api.ui.content.editor.base.io.ITextIOController
import taokdao.api.ui.content.manage.callback.ContentStateObserver
import taokdao.api.ui.content.menu.QuickMenu
import taokdao.api.ui.content.state.ContentState
import taokdao.api.ui.content.wrapped.ContentFragment
import taokdao.api.ui.progressbar.ProgressBarSet
import taokdao.codeeditor.ieditor.CEIOController
import taokdao.codeeditor.ieditor.CESearcher
import taokdao.codeeditor.layout.FindReplaceLayout
import taokdao.codeeditor.layout.QuickControlLayout
import taokdao.codeeditor.layout.autocomplete.AutoCompleteAdapter
import taokdao.codeeditor.layout.quickedit.QuickEditLayout
import taokdao.codeeditor.layout.quickedit.QuickEditMenuPool
import taokdao.codeeditor.layout.quickinput.QuickInputLayout
import taokdao.codeeditor.layout.selectoperate.SelectOperateLayout
import taokdao.main.IMainView
import taokdao.main.business.drawable_manage.DrawableManagePresenter
import tiiehenry.code.language.Language
import tiiehenry.code.language.text.TextLanguage
import tiiehenry.code.view.ColorScheme
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ContentsCodeeditorBinding
import tiiehenry.ideditor.databinding.QuickInputLayoutBinding
import tiiehenry.ktx.res.getAttrColor
import tiiehenry.ktx.res.getColorCompat
import java.io.File
import java.nio.charset.Charset

open class CodeEditorFragment(
    val main: IMainView,
    path: String,
    val binding:ContentsCodeeditorBinding= ContentsCodeeditorBinding.inflate(main.activity.layoutInflater),
//    layout: View = main.activity.layoutInflater.inflate(
//        R.layout.contents_codeeditor,
//        null
//    ),
) : ContentFragment(
    Properties(InnerIdentifier.Content.CODE_EDITOR),
    DrawableManagePresenter.getForFile(main, File(path)),
    File(path),
    binding.root,
    binding.codeIeditorEditor
) {

    init {
        /*setOnResumeObserver {
            Log.e(javaClass.simpleName, "showLayout: "+this )
            Log.e(javaClass.simpleName, "showLayout: "+layout)
            Log.e(javaClass.simpleName, "showLayout: "+layout.findViewById<TextView>(R.id.edit_name) )
            Log.e(javaClass.simpleName, "showLayout: "+layout.findViewById<TextView>(R.id.edit_name)?.text )
        }*/
        QuickEditMenuPool.initMenuList(main)
        quickMenuList.add(
            QuickMenu(
                main.getDrawable(R.drawable.codeeditor_quickmenu_search),
                "search",
                {
                    codeEditor.searcher.showFinder()
                },
                {
                    codeEditor.searcher.showReplacer()
                })
        )
        val readOnly =
            QuickMenu(main.getDrawable(R.drawable.codeeditor_quickmenu_editable_on), "search", {
            }, {
            })
        readOnly.click = View.OnClickListener {
            codeEditor.isEditable = !codeEditor.isEditable
            onEditableStateChanged(codeEditor.isEditable)
            readOnly.icon = main.getDrawable(
                if (codeEditor.isEditable) {
                    R.drawable.codeeditor_quickmenu_editable_on
                } else {
                    R.drawable.codeeditor_quickmenu_editable_off
                }
            )
            main.contentManager.refreshQuickMenu()
        }
        quickMenuList.add(readOnly)
    }

    private var codeEditorSetting: CodeEditorSetting? = null
    private val contentStateObserver = object : ContentStateObserver {
        private val mainObserver = MainActionObserver {
            codeEditorSetting?.applySettingListForLanguage()
            codeEditor.updateStartIndicator()
        }

        override fun onShow() {
//            Log.e("CodeEditorFragment", "onShow: " + path)
            codeEditor.updateStartIndicator()
            codeEditor.updateEndIndicator(codeEditor.currentLine)
        }

        override fun onHide() {

        }

        override fun onAdding() {
            MainAction.onContentSettingChanged.addObserver(mainObserver)
        }

        override fun onRemoved() {
            MainAction.onContentSettingChanged.removeObserver(mainObserver)
        }

    }
    private lateinit var selectOperateLayout: SelectOperateLayout
    private lateinit var findReplaceLayout: FindReplaceLayout
    private lateinit var quickEditLayout: QuickEditLayout
    private lateinit var quickControlLayout: QuickControlLayout
    private lateinit var quickInputLayout: QuickInputLayout
     val codeEditor =binding.codeIeditorEditor

    private val sender = ContentSender(this)

    override fun initView(view: View) {
        initCodeEditor(codeEditor)
        initCodeEditorSetting(codeEditor)

        selectOperateLayout =
            SelectOperateLayout(codeEditor, view.findViewById(R.id.select_operate_layout))
        codeEditor.setOnSelectionChangedListener { active, _, _ ->
            if (active) {
                codeEditor.updateStartIndicator()
//                codeEditor.updateEndIndicator()
                codeEditor.updateEndIndicator(-1)
                selectOperateLayout.show()
            } else {
                selectOperateLayout.hide()
            }
        }

        quickControlLayout = QuickControlLayout(main, codeEditor, binding).init()
        quickEditLayout =
            QuickEditLayout(main, codeEditor, view.findViewById(R.id.quick_edit_float_ibtn)).init()
        quickInputLayout =
            QuickInputLayout(
                codeEditor,
                QuickInputLayoutBinding.bind(view.findViewById(R.id.quick_input_layout))
            ).init()
        findReplaceLayout = FindReplaceLayout(
            codeEditor,
            view.findViewById(R.id.find_layout),
            view.findViewById(R.id.replace_layout)
        ).init()
        codeEditor.ceSearcher = CESearcher(codeEditor, findReplaceLayout, quickInputLayout)

        openFile()
    }

    private fun onEditableStateChanged(editable: Boolean) {
        if (editable) {
            quickInputLayout.show()
            quickControlLayout.show()
            quickEditLayout.show()
        } else {
            quickInputLayout.hide()
            quickControlLayout.hide()
            quickEditLayout.hide()
            findReplaceLayout.hideAll()
            selectOperateLayout.hide()
        }
    }

    private fun openFile() {
        main.launchMain {
            ProgressBarSet.BOTTOM_HORIZONTAL.addUser(PROGRESSBAR_USER_ID_OPEN_FILE)
            try {
                codeEditor.ioController.let {
                    val data = withContext(Dispatchers.IO) {
                        val canRead = it.open(path)
                        if (!canRead)
                            throw RuntimeException("can't open: $path")
                        it.read()
                    }
                    it.importData(data)
                }
                codeEditor.updateStartIndicator()
                codeEditor.updateEndIndicator(codeEditor.currentLine)
            } catch (e: Exception) {
                e.printStackTrace()
                main.contentManager.remove(this@CodeEditorFragment)
                sender.openFailed(path).send(main)
            }
            ProgressBarSet.BOTTOM_HORIZONTAL.removeUser(PROGRESSBAR_USER_ID_OPEN_FILE)
        }
    }

    fun reloadFile(charset: Charset) {
        main.launchMain {
            ProgressBarSet.BOTTOM_HORIZONTAL.addUser(PROGRESSBAR_USER_ID_OPEN_FILE)
            try {
                (codeEditor.ioController as ITextIOController<String>).let {
                    it.reloadWith(charset)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                main.contentManager.remove(this@CodeEditorFragment)
                sender.openFailed(path).send(main)
            }
            ProgressBarSet.BOTTOM_HORIZONTAL.removeUser(PROGRESSBAR_USER_ID_OPEN_FILE)
        }
    }

    fun close() {
        val path = codeEditor.ioController.currentPath ?: return
        try {
            codeEditor.ioController.let {
                val closed = it.close()
                if (!closed)
                    throw RuntimeException("can't close: $path")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            sender.openFailed(path).send(main)
        }
    }

    private fun initCodeEditor(editor: CodeIEditor) {
        editor.language = getLanguageForPath() ?: TextLanguage.getInstance()
//            isWordWrap = language.isWordWrap

        editor.apply {
            setLexTask(editor.language.newLexTask())
            setRowListener {
                val i = if (selectionStart == selectionEnd) {
                    getLineNumberForIndex(selectionStart) + 1
                } else -1
                updateEndIndicator(i)
            }
            setNonPrintingCharVisibility(false)
        }

        editor.autoCompletePanel.apply {
            setAdapter(AutoCompleteAdapter(main.context))
            setBackground(ColorDrawable(main.themeManager.getThemeColors(ThemeParts.CONTENT).backgroundColor))
            panel.animationStyle = R.style.CodeEditor_AutoComplete_PopupAnimation
            panel.setListSelector(
                main.themeManager.getThemeDrawables(ThemeParts.CONTENT).getRectangleSelector(main)
            )

        }
    }


    private fun initCodeEditorSetting(editor: CodeIEditor) {
        if (main.themeManager.shouldDark()) {
            ColorScheme.Colorable.CARET_BACKGROUND.color =
                main.context.getColorCompat(R.color.blueGrey_400)
        } else {
            ColorScheme.Colorable.CARET_BACKGROUND.color =
                main.context.getAttrColor(ideditor.api.skin.R.attr.main_appbar_background_color)
        }
        editor.setDark(main.themeManager.shouldDark())
        codeEditorSetting =
            CodeEditorSetting(main, editor, main.mmkvManager.getContentMMKV(this), this)
        codeEditorSetting?.applySettingListForLanguage()
    }

    private fun CodeIEditor.updateStartIndicator() {
        if (contentState != ContentState.STATE_SHOWING)
            return
        val ioc = (ioController as CEIOController)
        val text = ioc.currentCharset?.displayName()
            ?: main.getString(R.string.codeeditor_setting_encode)
        main.indicatorManager.startIndicator.setText(text)
    }

    private fun CodeIEditor.updateEndIndicator(line: Int = -1) {
        val selection = selector.selection
        val text = if (line < 0 && selection != null) {
            "${selection.start},${selection.end}" + ":" + selection.length().toString()
        } else {
            "$line:${lineCount + 1}"
        }
        main.indicatorManager.endIndicator.setText(text)
    }

    private fun getLanguageForPath(): Language? {
        return CodeEditorLanguageManager.getLanguageForSuffix(FileUtils.getSuffix(path))
    }

    override fun getStateObserver(): ContentStateObserver? {
        return contentStateObserver;
    }

    private val PROGRESSBAR_USER_ID_OPEN_FILE = id() + ".openFile"


}