package taokdao.builder.filebuilders

//import taokdao.plugin.engines.rhino.RhinoPluginEngine
//import tiiehenry.script.rhino.RhinoEngineFactory
import android.content.Intent
import android.net.Uri
import taokdao.api.builder.wrapped.BuildOption
import taokdao.api.data.bean.Properties
import taokdao.api.file.build.wrapped.FileBuilder
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.IMainContext
import tiiehenry.ideditor.R
import tiiehenry.taokdao.activity.MarkedActivity
import tiiehenry.taokdao.activity.WebActivity
import java.io.File


class MarkdownBuilder(val main: IMainContext) : FileBuilder(
        Properties(InnerIdentifier.FileBuilder.MARKDOWN_VIEWER, main, R.string.file_builders_markdownviewer_label)) {
    private val preview = BuildOption<File>(Properties(InnerIdentifier.FileBuilder.MARKDOWN_VIEWER + ".preview", main, R.string.file_builders_markdownviewer_preview_label)) { main, config, _ ->
        startActivity(main, config, false)
    }

    init {
        getSuffixes().addAll(mutableListOf("md"))
        getBuildOptionList().add(preview)
    }


    private fun startActivity(main: IMainContext, config: File, setUseWideViewPort: Boolean): Boolean {
        try {
            main.activity.startActivity(Intent(main.activity, MarkedActivity::class.java).apply {
//                data = Uri.fromFile(config)
                putExtra(MarkedActivity.EXTRA_KEY_PATH,config.absolutePath)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        } catch (e: Exception) {
            e.printStackTrace()
            main.send(e.message)
        }
        return true
    }
//
//    private fun StringBuilder.newLine() {
//        this.append("\n")
//    }
//
//    private fun StringBuilder.newLine(l: String) {
//        this.append("\n")
//        this.append(l)
//    }
//
//    private fun StringBuilder.tagOpen(tag: String) {
//        this.append("<$tag>")
//    }
//
//    private fun StringBuilder.tagClose(tag: String) {
//        this.append("</$tag>")
//    }
//
//
//    private fun generateHtml(main: IMainContext, config: File): Boolean {
//        val txt = main.contentManager.current?.editor?.dataController?.data.toString()
//        val plugin = RhinoPluginEngine(main)
//        RhinoEngineFactory().newScriptEngine().apply {
//            create()
//            fun getSourceDir(): String = main.context.getDir("src", Context.MODE_PRIVATE).absolutePath
//            requirer.findPathList.add(getSourceDir())
//
//            requirer.require("builder/markdown/marked.js")
//            requirer.require("builder/markdown/md2html.js")
//            varBridge.set("txt", txt)
//            stringEvaluator.evalSafely("md2html(txt,false)")?.getString()?.let {
//                val sb = StringBuilder()
//                sb.append("<!DOCTYPE html>")
//                sb.newLine()
//                sb.tagOpen("html")
//                sb.newLine()
//                sb.tagOpen("head")
//
//                sb.tagOpen("title")
//                sb.append(config.nameWithoutExtension)
//                sb.tagClose("title")
//                sb.newLine("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />")
//                sb.newLine("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n")
//
//                sb.tagOpen("style")
//                sb.newLine(File(plugin.getSourceDir(), "builder/markdown/bootstrap.css").readText())
//                sb.newLine(File(plugin.getSourceDir(), "builder/markdown/github.css").readText())
///*                main.assets.open("html/js/styles/github.css").use { inp ->
//                    sb.newLine(inp.bufferedReader().readText())
//                }*/
//                sb.newLine("img { max-width: 100%; }")
//                sb.tagClose("style")
//
////                sb.newLine("<link rel=\"stylesheet\"\n" + "href=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.10/styles/default.min.css\">\n")
////                sb.newLine("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">")
//
//
//                sb.tagClose("head")
//                sb.tagOpen("body")
//
//                sb.newLine(imgToBase64(it))
//
//                sb.newLine("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.10/highlight.min.js\"></script>")
//                sb.newLine("<script>hljs.initHighlightingOnLoad();</script>")
//
//                sb.tagClose("body")
//
//                sb.tagClose("html")
//                val htmlFile = File(config.parentFile, config.nameWithoutExtension + ".html").apply {
//                    writeText(sb.toString())
////                    writeText(render(txt).toString())
//                }
//                if (htmlFile.isFile) {
////                    callback.onDone(config)
//                    main.send("已生成：" + htmlFile.name)
//                    main.runOnUIThread {
//                        try {
//                            main.activity.startActivity(Intent(main.activity, MarkedActivity::class.java).apply {
//                                data = Uri.fromFile(htmlFile)
//                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                            })
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                            main.send(e.message)
//                        }
//                    }
//                }
//            }
//        }
//        return true
////        return startActivity(main, config, callback, true)
//    }

/*    fun render(mdText: String): String? {
//        val options = MutableDataSet()
        val options = PegdownOptionsAdapter.flexmarkOptions(true,
                Extensions.ALL
        )
        // uncomment to set optional extensions
        //options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        val parser = Parser.builder(options).build()
        val renderer = HtmlRenderer.builder(options).build()

        // You can re-use parser and renderer instances
        val document = parser.parse(mdText)
        return renderer.render(document).apply {
            loge(this)
        }  // "<p>This is <em>Sparta</em></p>\n"
    }*/

//
//    private fun imgToBase64(mdText: String): String {
//        val TAG = "MDViewer"
//        val IMAGE_PATTERN = "!\\[(.*)\\]\\((.*)\\)"
//        val ptn = Pattern.compile(IMAGE_PATTERN)
//        val matcher = ptn.matcher(mdText)
//        if (!matcher.find()) {
//            return mdText
//        }
//
//        val imgPath = matcher.group(2)
//        if (isUrlPrefix(imgPath!!) || !isPathExChack(imgPath)) {
//            return mdText
//        }
//        val baseType = imgEx2BaseType(imgPath)
//        if (baseType == "") {
//            // image load error.
//            return mdText
//        }
//
//        val file = File(imgPath)
//        val bytes = ByteArray(file.length().toInt())
//        try {
//            val buf = BufferedInputStream(FileInputStream(file))
//            buf.read(bytes, 0, bytes.size)
//            buf.close()
//        } catch (e: FileNotFoundException) {
//            Log.e(TAG, "FileNotFoundException:$e")
//        } catch (e: IOException) {
//            Log.e(TAG, "IOException:$e")
//        }
//
//        val base64Img = baseType + Base64.encodeToString(bytes, Base64.NO_WRAP)
//
//        return mdText.replace(imgPath, base64Img)
//    }
//
//    private fun isUrlPrefix(text: String): Boolean {
//        return text.startsWith("http://") || text.startsWith("https://")
//    }
//
//    private fun isPathExChack(text: String): Boolean {
//        return (text.endsWith(".png")
//                || text.endsWith(".jpg")
//                || text.endsWith(".jpeg")
//                || text.endsWith(".gif"))
//    }
//
//    private fun imgEx2BaseType(text: String): String {
//        return if (text.endsWith(".png")) {
//            "data:image/png;base64,"
//        } else if (text.endsWith(".jpg") || text.endsWith(".jpeg")) {
//            "data:image/jpg;base64,"
//        } else if (text.endsWith(".gif")) {
//            "data:image/gif;base64,"
//        } else {
//            ""
//        }
//    }
}