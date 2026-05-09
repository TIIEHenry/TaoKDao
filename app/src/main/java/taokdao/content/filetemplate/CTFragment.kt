package taokdao.content.filetemplate

import android.view.View
import taokdao.api.internal.InnerIdentifier
import taokdao.codeeditor.CodeEditorFragment
import taokdao.codeeditor.CodeEditorLanguageManager
import taokdao.main.IMainView
import tiiehenry.android.text.SimpleTextWatcher
import tiiehenry.ideditor.databinding.ContentsFileTemplateBinding
import java.io.File

open class CTFragment(
    main: IMainView,
    path: String,
    val templateBinding: ContentsFileTemplateBinding = ContentsFileTemplateBinding.inflate(main.layoutInflater)
    // main.activity.main_viewpager
) : CodeEditorFragment(main, path, templateBinding.contentsCodeeditor) {
//
//    override fun getView(): View? {
//        val v = super.getView()
////        Log.e(javaClass.simpleName, "getView: "+v )
//        return v
//    }

    override fun id(): String {
        return InnerIdentifier.Content.CODE_TEMPLATE_EDITOR
    }

    override fun getLabel(): String {
        return codeEditor.ioController.currentPath?.let { File(it).nameWithoutExtension }
            ?: super.getLabel()
    }

    override fun initView(view: View) {
        templateBinding.etExtension.addTextChangedListener(SimpleTextWatcher.newAfterWatcher { s ->
            CodeEditorLanguageManager.getLanguageForSuffix(s)?.let {
                codeEditor.lexTask = it.newLexTask()
            }
        })
        codeEditor.ioController = CTIOController(main, templateBinding, codeEditor)
        codeEditor.dataController = CTDataController(codeEditor)
        super.initView(view)
    }

}