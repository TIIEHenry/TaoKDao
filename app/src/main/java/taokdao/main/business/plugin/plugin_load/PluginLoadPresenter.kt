package taokdao.main.business.plugin.plugin_load

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import taokdao.api.internal.InnerIdentifier
import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginManifest
import taokdao.api.plugin.bean.PluginType
import taokdao.api.plugin.engine.PluginEnginePool
import taokdao.api.plugin.load.PluginLoadException
import tiiehenry.io.Filej
import tiiehenry.io.Zipl
import java.io.File
import java.io.IOException


class PluginLoadPresenter(private val view: PluginLoadContract.V) : PluginLoadContract.P {
    private val model = PluginLoadModel()

    internal val vw: PluginLoadContract.VW by lazy {
        PluginLoadViewWrapper(view, this)
    }

    private fun initFilesCacheDir(): Filej {
        val f = Filej(view.fileSystem.externalCacheDir, "plugin")
        f.deleteAll()
        f.mkdir()
        return f
    }

    override fun loadPlugin(pluginManifest: PluginManifest): Plugin {
        val plugin: Plugin
        try {
            plugin = Plugin(pluginManifest)
//            Log.e(javaClass.simpleName, "loadPlugin: " + pluginManifest)
            plugin.engine.engine = PluginEnginePool.getInstance().get(pluginManifest.engine.id)
                ?: throw Exception("cannot load engine:" + pluginManifest.engine.id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw PluginLoadException(
                pluginManifest,
                e.message,
                view.languageManager.languageCountry
            )
        }
        return plugin
    }

    override fun loadPluginManifestFromFile(manifestFile: File): PluginManifest {
        return PluginManifest.from(manifestFile.readText()).apply {
            pluginDir = manifestFile.parentFile
            this.manifestFile = manifestFile
            pluginType = PluginType.valueOf(type)
            check()
        }
    }

    @Throws(Exception::class)
    override fun loadPluginManifestFromAPK(apkFile: File): PluginManifest {
        val outDir = initFilesCacheDir()
        try {
            val pm = view.context.packageManager
            val info = pm.getPackageArchiveInfo(
                apkFile.absolutePath,
                PackageManager.GET_ACTIVITIES or PackageManager.GET_META_DATA
            )
                ?: throw RuntimeException("Plugin APK load error, packageInfo null")

            val applicationInfo = info.applicationInfo
                ?: throw IOException("Plugin APK load error, applicationInfo null")
            val isSetup =
                applicationInfo.metaData.getString(InnerIdentifier.MetaData.PARAMETER_TYPE) == InnerIdentifier.MetaData.TYPE_PLUGIN_SETUP
            if (!isSetup) {
                throw RuntimeException("APK is not plugin setup")
            }
            val path =
                applicationInfo.metaData.getString(InnerIdentifier.MetaData.PARAMETER_PLUGIN_SRC_PATH)
                    ?: throw RuntimeException("Plugin APK load error, plugin src path null")

            Zipl(apkFile).unZipDir(path, outDir)

            Filej(apkFile).copyTo(File(outDir, "${info.packageName}.apk.bak"))
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("extract files from apk failed(file:" + apkFile.absolutePath + ", message:" + e.message + ")")
        }
        return loadPluginManifestFromDir(outDir.absoluteFile)
    }

    @Throws(Exception::class)
    override fun loadPluginManifestFromZIP(zipFile: File): PluginManifest {
        val outDir = initFilesCacheDir()
        try {
            Zipl(zipFile).unZipAll(outDir)
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("extract files from zip failed(file:" + zipFile.absolutePath + ", message:" + e.message + ")")
        }
        return loadPluginManifestFromDir(outDir.absoluteFile)
    }


    override fun loadPluginIcon(plugin: Plugin): Drawable {
        var icon: Drawable? = plugin.getInformation(view).icon
        if (icon != null) {
            return icon
        }
        if (plugin.manifest.getInformation(view).icon == "apk") {
            val file = File(plugin.pluginDir, plugin.id + ".apk.bak")
            if (file.exists()) {
                val packageManager = view.applicationContext.packageManager
                val packageInfo = packageManager.getPackageArchiveInfo(
                    file.absolutePath,
                    PackageManager.GET_ACTIVITIES
                )

                val applicationInfo = packageInfo?.applicationInfo
                if (applicationInfo != null) {
                    applicationInfo.sourceDir = file.absolutePath
                    applicationInfo.publicSourceDir = file.absolutePath
                    try {
                        icon = applicationInfo.loadIcon(packageManager)
                    } catch (e: OutOfMemoryError) {
                        e.printStackTrace()
                    }
                }
                if (icon == null) {
                    packageInfo?.applicationInfo?.let {
                        icon = packageManager.getApplicationIcon(it)
                    }
                }
            }
        } else {
            val iconFile = File(plugin.pluginDir, plugin.manifest.getInformation(view).icon)
            if (iconFile.exists())
                icon = Drawable.createFromPath(iconFile.absolutePath)
        }
        return icon ?: vw.getDefaultPluginIcon()
    }
}