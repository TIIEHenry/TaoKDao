package tiiehenry.taokdao.app


import android.app.Application
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.tencent.mmkv.MMKV
import org.jetbrains.anko.runOnUiThread
import taokdao.api.file.system.IFileSystem
import tiiehenry.ideditor.BuildConfig
import xcrash.XCrash
import java.io.File


class App : Application(), IFileSystem, IFileSystemWrapper {

    private lateinit var settingDir: File

    override fun onCreate() {
        super.onCreate()

//        XUI.init(this) //初始化UI框架
//        XUI.debug(false)  //开启UI框架调试日志
//        QMUISwipeBackActivityManager.init(this)

        app = this
        settingDir = File(MMKV.initialize(getDir("mmkv", Context.MODE_PRIVATE).absolutePath))

        init(this)

        initXCrash()

//        AndroidKtxConfig.init(this, isDebug = BuildConfig.DEBUG, defaultLogTag = "taokdao", sharedPrefName = packageName + "_preferences")


    }

    fun init(app: App) {
        app.workDir.mkdirs()
        app.internalWorkDir.mkdirs()
        app.externalWorkDir.mkdirs()
        app.cacheDir.mkdirs()
        app.internalCacheDir.mkdirs()
        app.externalCacheDir.mkdirs()
        app.pluginDir.mkdirs()
        app.pluginWorkDir.mkdirs()
        app.crashDir.mkdirs()
        app.projectDir.mkdirs()
    }

    private fun initXCrash() {
        val parameters = XCrash.InitParameters()
                // 设置xCrash在处理完Java异常后是否应将Java异常重新抛出给系统。 （默认值：true）
                .setJavaRethrow(true)
                .setAnrRethrow(true)
                // 设置要保存在日志目录中的Java故障日志文件的最大数量。 （预设值：10）
                .setJavaLogCountMax(30)
                .setLogDir(crashDir.absolutePath)
                .setAnrCallback { logPath, emergency ->
                    runOnUiThread {
                        Toast.makeText(this@App, "出现闪退了正在把日志保存到" + logPath + "目录下", Toast.LENGTH_LONG).show()
                    }
                }
                .setJavaCallback { logPath, _ ->
                    runOnUiThread {
                        Toast.makeText(this@App, "出现闪退了正在把日志保存到" + logPath + "目录下", Toast.LENGTH_LONG).show()
                    }
                }
        XCrash.init(this, parameters)
    }

    override fun getSettingDir(): File {
        return settingDir
    }

    override fun getInternalWorkDir(): File {
        return getDir("work", Context.MODE_PRIVATE)
    }

    override fun getWorkDir(): File {
        return File(Environment.getExternalStorageDirectory(), "TaoKDao")
    }

    override fun getExternalWorkDir(): File {
        return getExternalFilesDir(null) ?: workDir
    }

    override fun getInternalCacheDir(): File {
        return super.getCacheDir()
    }

    override fun getCacheDir(): File {
        return File(workDir, "Cache")
    }

    override fun getExternalCacheDir(): File {
        return super.getExternalCacheDir() ?: cacheDir
    }

    companion object {
        lateinit var app: App

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

}