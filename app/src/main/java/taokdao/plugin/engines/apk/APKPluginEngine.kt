package taokdao.plugin.engines.apk

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.appcompat.view.ContextThemeWrapper
import dalvik.system.DexClassLoader
import taokdao.api.data.bean.Properties
import taokdao.api.event.senders.PluginEngineSender
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.IMainContext
import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginActions
import taokdao.api.plugin.bridge.invoke.IInvokeCallback
import taokdao.api.plugin.engine.wrapped.PluginEngine
import taokdao.api.plugin.entrance.IDynamicPluginEntrance
import tiiehenry.android.dl.bean.APKConfig
import tiiehenry.android.dl.bean.DLPluginPackage
import tiiehenry.android.dl.manage.DLPluginManager
import java.io.File
import java.io.IOException

class APKPluginEngine(val main: IMainContext)
    : PluginEngine(Properties(InnerIdentifier.PluginEngine.APK, "APK")) {

    override val icon: Drawable? get() = null
    private val pluginManager = DLPluginManager.getInstance(main.context)
    private val apkPluginWorkDir = File(main.fileSystem.internalWorkDir, "plugin/apk").apply { mkdirs() }

    private val pluginEnvList = HashMap<Plugin, IDynamicPluginEntrance>()

    private val sender = PluginEngineSender(this)

    override fun onCreateEngine() {
    }

    override fun onDestroyEngine() {
        pluginEnvList.onEach {
            it.value.onDestroy(main, it.key.manifest)
        }
        pluginEnvList.clear()
    }


    @Throws(Exception::class)
    override fun onInstallPlugin(plugin: Plugin) {
        val entrance = loadEntrance(plugin)
//        val parameter = loadParameter(plugin)
        if (entrance != null) {
            checkPluginOnInstalling(plugin, entrance.first, entrance.second)
        } else {
            throw RuntimeException("error entrance")
        }
    }

    override fun onUninstallPlugin(plugin: Plugin) {
    }

    override fun invokePlugin(plugin: Plugin, method: String, params: String?, invokeCallback: IInvokeCallback?): String? {
        return getPluginModuleEnv(plugin)?.onInvoke(main, plugin.manifest, method, params, invokeCallback)
    }

    override fun callPluginAction(plugin: Plugin, action: PluginActions) {
        try {
            when (action) {
                PluginActions.onUpGrade -> callOnUpGrade(plugin)
                PluginActions.onDownGrade -> callOnDownGrade(plugin)
                PluginActions.onCreate -> callOnCreate(plugin)
                PluginActions.onDestroy -> callOnDestroy(plugin)
                PluginActions.onInit -> callOnInit(plugin)
                PluginActions.onCall -> callOnCall(plugin)
                PluginActions.onPause -> callOnPause(plugin)
                PluginActions.onResume -> callOnResume(plugin)
                PluginActions.onInvoke -> callOnInvoke(plugin)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            sender.callError(plugin, e, main).log(main)
        }
    }

    private fun callOnUpGrade(plugin: Plugin) {
        getPluginModuleEnv(plugin)?.onUpGrade(main, plugin.manifest)
    }

    private fun callOnDownGrade(plugin: Plugin) {
        getPluginModuleEnv(plugin)?.onDownGrade(main, plugin.manifest)
    }

    private fun callOnCreate(plugin: Plugin) {
        if (getPluginModuleEnv(plugin) != null) {
            return
        }
        loadPluginSafely(plugin)
    }

    private fun callOnDestroy(plugin: Plugin) {
        getPluginModuleEnv(plugin)?.onDestroy(main, plugin.manifest)
        removePluginModuleEnv(plugin)
    }

    private fun callOnInit(plugin: Plugin) {
        getPluginModuleEnv(plugin)?.onInit(main, plugin.manifest)
    }

    private fun callOnCall(plugin: Plugin) {
        getPluginModuleEnv(plugin)?.onCall(main, plugin.manifest)
    }

    private fun callOnPause(plugin: Plugin) {
        getPluginModuleEnv(plugin)?.onPause(main, plugin.manifest)
    }

    private fun callOnResume(plugin: Plugin) {
        getPluginModuleEnv(plugin)?.onResume(main, plugin.manifest)
    }

    private fun callOnInvoke(plugin: Plugin) {
        // onInvoke is handled via invokePlugin method
    }


    @Throws(Exception::class)
    private fun loadPluginSafely(plugin: Plugin) {
        val entrance = loadEntrance(plugin) ?: return
        val dlPluginPackage = loadDLPluginSafely(plugin, entrance.first, entrance.second) ?: return
        onPluginLoaded(plugin, dlPluginPackage, entrance.second)
    }

    //NoClassDefFoundError 无法捕捉
    private fun onPluginLoaded(plugin: Plugin, pluginPackage: DLPluginPackage, className: String) {
        val env: IDynamicPluginEntrance
        try {
            val clazz = pluginPackage.classLoader.loadClass(className)
            env = clazz.newInstance() as IDynamicPluginEntrance

//            val style = if (main.themeManager.currentUIMode.isDark) R.style.Theme_AppCompat else R.style.Theme_AppCompat_Light
            val style = main.themeManager.themeId
//            val contextThemeWrapper = ContextThemeWrapper(pluginPackage.context, style)
            val contextThemeWrapper = ContextThemeWrapper(pluginPackage.context, main.activity.theme)
            contextThemeWrapper.setTheme(style)
            env.onAttach(contextThemeWrapper)
        } catch (e: NoClassDefFoundError) {
            sender.pluginError(plugin, "error load plugin class: " + plugin.id + "/" + className).log(main)
            sender.errorMsg("message:" + e.message).log(main)
            return
        } catch (e: Throwable) {
            sender.pluginError(plugin, "error load plugin class: " + plugin.id + "/" + className).log(main)
            sender.errorMsg("message:" + e.message).log(main)
            return
        }
        setPluginModuleEnv(plugin, env)
        env.onCreate(main, plugin.manifest)
    }

    private fun loadDLPlugin(plugin: Plugin, pkgName: String, className: String): DLPluginPackage {
        if (plugin.engine.parameter.contains("installed") && isApkInstalled(pkgName)) {
            /**
             *debug 模式 优先加载安装的
             */
            return loadDLPluginInstalled(plugin, pkgName)
        } else {
            return loadDLPluginFromApk(plugin, pkgName, className)
        }
    }

    private fun loadDLPluginSafely(plugin: Plugin, pkgName: String, className: String): DLPluginPackage? {
        try {
            return loadDLPlugin(plugin, pkgName, className)
        } catch (e: Exception) {
            sender.pluginError(plugin, e.message.toString()).log(main)
        }
        return null
    }

    @Throws(Exception::class)
    private fun loadDLPluginInstalled(plugin: Plugin, packageName: String): DLPluginPackage {
        val manager = main.context.packageManager
        val apkPath = manager.getPackageInfo(packageName, 0).applicationInfo?.publicSourceDir?:throw IOException("can not find package applicationInfo: $packageName")
        return loadDLPluginFromPath(plugin, apkPath)
    }

    @Throws(Exception::class)
    private fun loadDLPluginFromPath(plugin: Plugin, path: String): DLPluginPackage {
        try {
            val pluginDir = File(apkPluginWorkDir, plugin.id)
            val odexDir = File(pluginDir, "odex").apply {
                mkdirs()
            }.absolutePath
            val soDir = File(pluginDir, "so").apply {
                mkdirs()
            }.absolutePath
            val config = APKConfig(path, odexDir, soDir)
            return pluginManager.loadAPK(config)
        } catch (e: Exception) {
            throw Exception("load apk error: " + e.message)
        }
    }

    private fun loadDLPluginFromApk(plugin: Plugin, pkgName: String, className: String): DLPluginPackage {
        val apkPath = getPluginAPKFile(plugin).absolutePath
        return loadDLPluginFromPath(plugin, apkPath)
    }

    /**
     * 检查插件是否能加载
     */
    private fun checkPluginOnInstalling(plugin: Plugin, pkgName: String, className: String) {
//        try {
        val apkFile = getPluginAPKFile(plugin)
        if (apkFile.exists()) {
            loadDLPluginFromPath(plugin, apkFile.absolutePath)
            return
        }

        if (isApkInstalled(pkgName)) {
            loadDLPluginInstalled(plugin, pkgName)
            return
        }
        val file = File(plugin.pluginDir, "$pkgName.apk")
//        Log.e(javaClass.simpleName, "checkPluginOnInstalling: " + file)
        if (dexPlugin(file) == null) {
            throw Exception("load plugin error :loaded dex but return null")
        }
//        } catch (e: Exception) {
//            sender.entranceIncorrect(plugin, e.message.toString()).log(main)
//        }
    }

    private fun isApkInstalled(pkgName: String): Boolean {
        try {
            val packageInfo = main.context.packageManager.getPackageInfo(pkgName, 0)
            return packageInfo != null
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return false
    }

    private fun getPluginAPKFile(plugin: Plugin): File {
        return File(plugin.pluginDir, "${plugin.id}.apk.bak")
    }

    /**
     * @return packageName to className
     */
    private fun loadEntrance(plugin: Plugin): Pair<String, String>? {
        plugin.engine.entrance?.let {
            if (it.contains("/")) {
                val ss = it.split("/")
                var pkgName = ss.first()
                if (pkgName.isEmpty())
                    pkgName = plugin.id
                var className = ss.last()
                if (className.startsWith(".")) {
                    className = pkgName + className
                }
                return pkgName to className
            }
        }
        return null
    }

    private fun dexPlugin(file: File): DexClassLoader? {
        when (file.extension) {
            "dex", "apk", "jar" -> {
                try {
                    return main.dexLoader.loadDexFile(file)
                } catch (e: Exception) {
                    throw Exception("load entrance file error: " + e.message)
                }
            }
            else -> {
                throw Exception("entrance file type is not supported: " + file.absolutePath)
            }
        }
    }

    private fun getPluginModuleEnv(plugin: Plugin): IDynamicPluginEntrance? {
        return pluginEnvList[plugin]
    }

    private fun setPluginModuleEnv(plugin: Plugin, env: IDynamicPluginEntrance) {
        pluginEnvList[plugin] = env
    }

    private fun removePluginModuleEnv(plugin: Plugin) {
        pluginEnvList.remove(plugin)
    }
}