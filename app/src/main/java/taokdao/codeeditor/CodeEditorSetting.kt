package taokdao.codeeditor

import android.content.Context
import android.os.Build
import android.widget.Magnifier
import taokdao.api.data.bean.Properties
import taokdao.api.data.mmkv.IMMKV
import taokdao.api.main.IMainContext
import taokdao.api.setting.preference.wrapped.*
import taokdao.api.ui.content.editor.base.io.ITextIOController
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.code.language.Language
import tiiehenry.ideditor.R.string
import java.io.File

class CodeEditorSetting(val main: IMainContext, val editor: CodeIEditor, private val mmkv: IMMKV, private val contentFragment: CodeEditorFragment) {

    class Font(main: Context, prefix: String) : CategoryPreference(Properties(prefix + "font", main, string.codeeditor_setting_font)) {
        val fontSize = Properties("fontSize", main, string.codeeditor_setting_fontsize)
        val fontPath = Properties("fontPath", main, string.codeeditor_setting_fontpath)
        val customFont = Properties("customFont", main, string.codeeditor_setting_customfont)
    }

    class Edit(main: Context, prefix: String) : CategoryPreference(Properties(prefix + "edit", main, string.codeeditor_setting_edit)) {
        val wordwrap = Properties("wordwrap", main, string.codeeditor_setting_wordwrap)
        val compatibilityMode = Properties("compatibilityMode", main, string.codeeditor_setting_compatibilitymode, string.codeeditor_setting_compatibilitymode_description)
        val useTab = Properties("useTab", main, string.codeeditor_setting_usetab)
        val tabWidth = Properties("tabWidth", main, string.codeeditor_setting_tabwidth, string.codeeditor_setting_tabwidth_des)
        val indentNumber = Properties("indentNumber", main, string.codeeditor_setting_indentnumber, string.codeeditor_setting_indentnumber_des)
    }


    class Navigate(main: Context, prefix: String) : CategoryPreference(Properties(prefix + "navigate", main, string.codeeditor_setting_navigate)) {
        val showMagnifier = Properties("showMagnifier", main, string.codeeditor_setting_showmagnifier, string.codeeditor_setting_showmagnifier_des)
        val showLineNumber = Properties("showLineNumber", main, string.codeeditor_setting_showlinenumber)
        val highlightRow = Properties("highlightLineNumber", main, string.codeeditor_setting_highlightrow)
        val showNonPrinting = Properties("showNonPrinting", main, string.codeeditor_setting_shownonprinting)
    }

    class Block(main: Context, prefix: String) : CategoryPreference(Properties(prefix + "block", main, string.codeeditor_setting_block)) {
        val showBlockLine = Properties("showBlockLine", main, string.codeeditor_setting_block_showblockline)
        val highlightBlockLine = Properties("highlightBlockLine", main, string.codeeditor_setting_block_highlightblockline)
    }

    class Encode(main: Context, prefix: String) : CategoryPreference(Properties(prefix + "encode", main, string.codeeditor_setting_encode)) {
        val defaultReadEncode = Properties("defaultReadEncode", main, string.codeeditor_setting_encode_defaultreadencode)
        val defaultWriteEncode = Properties("defaultWriteEncode", main, string.codeeditor_setting_encode_defaultwriteencode)
        val reloadWithEncode = Properties("reloadWithEncode", main, string.codeeditor_setting_encode_reloadwithencode)
        val convertToEncode = Properties("convertToEncode", main, string.codeeditor_setting_encode_converttoencode)
    }


    class Display(main: Context, prefix: String) : CategoryPreference(Properties(prefix + "display", main, string.codeeditor_setting_display)) {
        val language = Properties("language", main, string.codeeditor_setting_display_language)
    }

    private fun getNavigate(prefix: String): CategoryPreference {
        val keys = Navigate(main.context, prefix)

        val showMagnifier = SwitchPreference(mmkv, true, keys.showMagnifier) { isOn ->
            if (isOn) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    editor.setMagnifier(Magnifier.Builder(editor).build())
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    editor.setMagnifier(Magnifier(editor))
                }
            } else {
                editor.setMagnifier(null)
            }
        }
        val showLineNumber = SwitchPreference(mmkv, true, keys.showLineNumber) { isOn ->
            editor.getSetting().isShowLineNumbers = isOn
        }

        val highlightRow = SwitchPreference(mmkv, true, keys.highlightRow) { isOn ->
            editor.getSetting().isHighlightCurrentRow = isOn
        }

        val showNonPrinting = SwitchPreference(mmkv, false, keys.showNonPrinting) { isOn ->
            editor.getSetting().isShowNonPrinting = isOn
        }

        keys.numberList = listOf(showMagnifier, showLineNumber, highlightRow,showNonPrinting)
        return keys
    }

    private fun getEdit(prefix: String): CategoryPreference {
        val keys = Edit(main.context, prefix)
        val compatibilityMode = SwitchPreference(mmkv, false, keys.compatibilityMode) { isOn ->
            editor.setting.isCompatibilityMode = isOn
        }

        val autoWordWrap = SwitchPreference(mmkv, false, keys.wordwrap) { isOn ->
            editor.isWordWrap = isOn
        }


        val tabWidth = SingleChoicePreference(mmkv, 2, keys.tabWidth) { _, text ->
            try {
                editor.getSetting().tabLength = text.toInt()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.apply {
            setItemList((1 until 7).map { it.toString() })
        }

        val useTab = SwitchPreference(mmkv, false, keys.useTab) { isOn ->
            editor.language.indentChar = if (isOn) '\t' else ' '
            tabWidth.setEnable(isOn)
        }

        val indentNumber = SingleChoicePreference(mmkv, 0, keys.indentNumber) { _, text ->
            try {
                editor.getSetting().autoIndentWidth = text.toInt()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.apply {
            setItemList((1 until 7).map { it.toString() })
        }
        keys.numberList = listOf(compatibilityMode, autoWordWrap, useTab, tabWidth, indentNumber)
        return keys
    }

    private fun getBlock(prefix: String): CategoryPreference {
        val keys = Block(main.context, prefix)

        val showBlockLine = SwitchPreference(mmkv, true, keys.showBlockLine) { isOn ->
            editor.getSetting().isShowBlockRegionLines = isOn
        }
        val highlightBlockLine = SwitchPreference(mmkv, true, keys.highlightBlockLine) { isOn ->
            editor.getSetting().isShowBlockRegionHighlightLine = isOn
        }

        keys.numberList = listOf(showBlockLine, highlightBlockLine)
        return keys
    }

    private fun getDisplay(prefix: String): CategoryPreference {
        val keys = Display(main.context, prefix)

        val language = TextPreference(mmkv, "", keys.language) { textPreference, text ->
//            Dialogs.global
//                    .asList()
//                    .typeSingleChoice()
//                    .items()
//                    .itemsCallbackSingleChoice()
//                    .positiveText()
//                    .negativeText()
        }

        keys.numberList = listOf(language)
        return keys
    }

    private fun getFont(prefix: String): CategoryPreference {
        val keys = Font(main.context, prefix)

        val fontSize = SingleChoicePreference(mmkv, 4, keys.fontSize) { _, text ->
            try {
                editor.setTextSizeSP(text.toInt())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.apply {
            setItemList((11 until 22).map { it.toString() })
        }

        val fontPath = EditTextPreference(mmkv, getDefaultFontFile(main).absolutePath, keys.fontPath) { text ->
            File(text).let {
                if (it.isFile) {
                    editor.initFontFile(it)
                }
            }
        }

        val customFont = SwitchPreference(mmkv, true, keys.customFont) { isOn ->
            if (isOn)
                fontPath.load()
            else
                editor.defaultFont()
            fontPath.setEnable(isOn)
        }


        keys.numberList = listOf(fontSize, customFont, fontPath)
        return keys
    }

//    private var dontChange: Boolean=false
//    private var writeLocked = false
    private fun getEncode(prefix: String): CategoryPreference {
        val keys = Encode(main.context, prefix)
        val ioController = editor.ioController as ITextIOController

        val charsetList = CharsetCollection.values().map { it.charset }


        val defIndex = charsetList.indexOf(Charsets.UTF_8)
        val defaultReadEncode = SingleChoicePreference(mmkv, defIndex, keys.defaultReadEncode) { index, text ->
            try {
                if (index == 0) {
                    ioController.readCharset = null
                } else {
                    ioController.readCharset = charsetList[index - 1]
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.apply {
            val items = charsetList.map { it.displayName() + "(" + it.name() + ")" }
                    .toMutableList()
            items.add(0, main.getString(string.codeeditor_setting_encode_autodetect))
            setItemList(items)
        }

        val defaultWriteEncode = SingleChoicePreference(mmkv, defIndex, keys.defaultWriteEncode) { index, text ->
//            if (writeLocked) {
//                return@SingleChoicePreference
//            }

            try {
                if (index == 0) {
                    ioController.writeCharset = null
                } else {
                    ioController.writeCharset = charsetList[index - 1]
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.apply {
            val items = charsetList.map { it.displayName() + "(" + it.name() + ")" }
                    .toMutableList()
            items.add(0, main.getString(string.codeeditor_setting_encode_keepreadencode))
            setItemList(items)
        }
        val selectItems = charsetList.map { it.displayName()}
        val reloadWithEncode = ClickablePreference(keys.reloadWithEncode, {
            var index = charsetList.indexOf(Charsets.UTF_8)
            ioController.currentCharset?.let {
                index = charsetList.indexOf(it)
            }
            Dialogs.global
                    .asList()
                    .typeSingleChoice()
                    .title(keys.reloadWithEncode.label)
                    .items(selectItems)
                    .itemsCallbackSingleChoice(index) { _, position, text ->
                        contentFragment.reloadFile(charsetList[position])
                        return@itemsCallbackSingleChoice true
                    }
                    .positiveText()
                    .negativeText()
                    .show()
        }, null)

        val convertToEncode = ClickablePreference(keys.convertToEncode, {
            var index = charsetList.indexOf(Charsets.UTF_8)
            ioController.writeCharset?.let {
                index = charsetList.indexOf(it)
            }
            Dialogs.global
                    .asList()
                    .typeSingleChoice()
                    .title(keys.convertToEncode.label)
                    .items(selectItems)
                    .itemsCallbackSingleChoice(index) { _, position, _ ->
//                        writeLocked = true
//                        dontChange=true
                        ioController.convertTo(charsetList[position])
                        return@itemsCallbackSingleChoice true
                    }
                    .positiveText()
                    .negativeText()
//                    .itemsCallback { _, position, text ->
//                        try {
//                            ioController.convertTo(charsetList[position])
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
                    .show()
        }, null)


        keys.numberList = listOf(defaultReadEncode, defaultWriteEncode, reloadWithEncode, convertToEncode)
        return keys
    }

    private fun loadSettingListForLanguage(language: Language): List<CategoryPreference> {
        val prefix = language.name
        val preferenceList = listOf(getFont(prefix), getEdit(prefix), getNavigate(prefix), getBlock(prefix), getEncode(prefix))
        preferenceList.forEach { it.load() }
        return preferenceList
    }

    fun applySettingListForLanguage() {
//        if (dontChange)
//            return
        val preferenceList = loadSettingListForLanguage(editor.language)
        contentFragment.settingList.let {
            it.clear()
            it.addAll(preferenceList)
        }
    }

    companion object {
        fun getDefaultFontFile(main: IMainContext): File {
            return File(main.fileSystem.workDir, "Editor/font/FiraCode-Retina.ttf")
        }
    }

}