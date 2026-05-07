package taokdao.codeeditor

import taokdao.api.data.bean.Properties
import taokdao.api.file.open.wrapped.SuffixFileOpener
import taokdao.api.file.open.wrapped.SuffixFileOpener.Callback
import taokdao.main.IMainView
import taokdao.main.business.setting_main.UpdateManager
import tiiehenry.ideditor.R
import tiiehenry.io.Zipl
import tiiehenry.taokdao.ui.dialog.ErrorDialog
import java.io.File
import java.io.IOException

class CodeEditorOpener(val main: IMainView)
    : SuffixFileOpener(
        CodeEditorLanguageManager.supportSuffix.keys.toTypedArray(),
        Properties("CodeEditor", main.context, R.string.contents_codeeditor_label, R.string.contents_codeeditor_description), main.getDrawable(
        tiiehenry.ideditor.R.mipmap.ic_launcher_round), null
) {
    init {
        if (UpdateManager.shouldUpdateLocal(main.context, main.mmkvManager.getMMKV("shouldUpdateLocal")))
            unZipTTFFile()
        click = Callback { _, manager, path ->
            val fragment = CodeEditorFragment(main, path)
            fragment.opener = id
            manager.add(fragment)
        }
    }


    private fun unZipTTFFile() {
        val file = CodeEditorSetting.getDefaultFontFile(main)
        if (!file.exists()) {
            try {
                Zipl(File(main.context.applicationInfo.publicSourceDir))
                        .unZip("assets/codeeditor/fonts/FiraCode-Retina.ttf", file)
            } catch (e: IOException) {
                e.printStackTrace()
                main.launchMain {
                    ErrorDialog(main.context, e.message.toString()).show()
                }
            }
        }
    }
}