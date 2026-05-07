package taokdao.builder.filebuilders


import android.content.Intent
import android.net.Uri
import taokdao.api.builder.wrapped.BuildOption
import taokdao.api.data.bean.Properties
import taokdao.api.file.build.wrapped.FileBuilder
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.IMainContext
import tiiehenry.ideditor.R
import tiiehenry.taokdao.activity.WebActivity
import java.io.File

class HtmlViewer(val main: IMainContext) : FileBuilder(
        Properties(InnerIdentifier.FileBuilder.HTML_VIEWER, main, R.string.file_builders_htmlviewer_label)) {
    private val preview = BuildOption<File>(
            Properties(InnerIdentifier.FileBuilder.HTML_VIEWER + ".preview", main, R.string.file_builders_htmlviewer_preview_label))
    { main, config, _ ->
        startActivity(main, config, false)
    }

    init {
        getSuffixes().addAll(mutableListOf("html", "htm", "mht", "txt", "text"))
        getBuildOptionList().add(preview)
    }

    fun startActivity(main: IMainContext, config: File, setUseWideViewPort: Boolean): Boolean {
        try {
            main.activity.startActivity(Intent(main.activity, WebActivity::class.java).apply {
                data = Uri.fromFile(config)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra("setUseWideViewPort", setUseWideViewPort)
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }
}