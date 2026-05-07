package taokdao.main.business.plugin.plugin_install

import taokdao.api.file.system.IFileSystem
import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginManifest
import taokdao.api.plugin.load.IPluginLoader
import taokdao.api.plugin.load.PluginLoadException
import tiiehenry.io.Filej
import java.io.File

class PluginInstallModel : PluginInstallContract.M {

    override fun installPlugin(plugin: Plugin, fileSystem: IFileSystem) {
        plugin.engine.engine.onInstallPlugin(plugin)
        Filej(plugin.pluginDir).copyTo(fileSystem.getPluginDir(plugin.manifest), true)
    }

    override fun uninstallPlugin(plugin: Plugin) {
        plugin.engine.engine.onUninstallPlugin(plugin)
        Filej(plugin.pluginDir).apply {
            deleteAll()
        }
    }

    override fun callOnDownGrade(old: Plugin?, new: Plugin): Boolean {
        if (old != null && old.version.code > new.version.code) {
            old.onDownGrade()
            return true
        }
        return false
    }

    override fun callOnUpGrade(old: Plugin?, new: Plugin): Boolean {
        if (old != null && old.version.code < new.version.code) {
            new.onUpGrade()
            return true
        }
        return false
    }

    @Throws(PluginLoadException::class)
    override fun loadPluginManifest(path: String, pluginLoader: IPluginLoader): PluginManifest {
        val file = File(path)
        return if (file.isDirectory) {
            pluginLoader.loadPluginManifestFromDir(file)
        } else {
            pluginLoader.loadPluginManifestFromAPK(file)
        }
    }

    @Throws(PluginLoadException::class)
    override fun loadPlugin(path: String, pluginLoader: IPluginLoader): Plugin {
        val manifest = loadPluginManifest(path, pluginLoader)
        return pluginLoader.loadPlugin(manifest)
    }

    companion object {
        val packageSuffix = ".plugin.apk"

    }
}
