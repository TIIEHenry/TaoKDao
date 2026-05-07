package taokdao.builder.filebuilders

//
//import android.content.Intent
//import android.net.Uri
//import taokdao.api.builder.wrapped.BuildOption
//import taokdao.api.data.bean.Properties
//import taokdao.api.file.build.IFileBuilder
//import taokdao.api.main.IMainContext
//import tiiehenry.script.app.rhino.RhinoActivity
//import tiiehenry.script.engine.android.ScriptContextConst
//import java.io.File
//
//object JsBuilder : IFileBuilder {
//    private val runWithRhino = BuildOption<File>(Properties("runWithRhino", "runWithRhino")) { main, config, _ ->
//        startActivity(main, config, false)
//    }
//
//
//    private val buildOptionList = mutableListOf(runWithRhino)
//
//    override fun getBuildOptionList(): MutableList<BuildOption<File>> {
//        return buildOptionList
//    }
//
//    override fun getSuffixes(): MutableList<String> {
//        return mutableListOf("js", "jsx")
//    }
//
//
//    fun startActivity(main: IMainContext, config: File, appCompat: Boolean): Boolean {
//        val theme = "Packages.tiiehenry.taokdao.R.style.Theme_AppCompat"
//        try {
//            main.activity.startActivity(Intent(main.activity, RhinoActivity::class.java).apply {
////                putExtra(ScriptContextConst.ENGINE_FACTORY_NAME,"Rhino")
//                putExtra(ScriptContextConst.Companion.INTENT.SCRIPT_BEFORE_DATA, "activity.setTheme($theme" + (
//                        if (main.themeManager.shouldDark())
//                            ""
//                        else
//                            "_Light"
//                        ) + ");")
//                data = Uri.fromFile(File(config.absolutePath))
////                putExtra(ScriptContextConst.Companion.INTENT.SCRIPT_AFTER_DATA, "load(\"${config.absolutePath}\");")
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            })
//        } catch (e: Exception) {
//            e.printStackTrace()
//            main.send(e.message)
//        }
//        return true
//    }
//}