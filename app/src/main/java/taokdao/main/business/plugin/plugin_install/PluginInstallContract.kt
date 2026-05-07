package taokdao.main.business.plugin.plugin_install

import android.content.Intent
import taokdao.api.file.system.IFileSystem
import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginManifest
import taokdao.api.plugin.install.IPluginInstaller
import taokdao.api.plugin.load.IPluginLoader
import taokdao.main.IMainView
import tiiehenry.android.ui.dialogs.api.IDialog


interface PluginInstallContract {
    interface V : IMainView {
        val pluginInstallPresenter: PluginInstallPresenter

        fun showLoadingPluginDialog(): IDialog
        fun onLoadPluginError(message: String?)

        fun showPluginInfoDialogForInstall(newPlugin: Plugin, oldPlugin: Plugin?)
        fun showPluginInfoDialogForUninstall(plugin: Plugin)

        fun onPluginInstallSuccess()
        fun onPluginInstallFailed(message: String?)

        fun onPluginUninstallSuccess()
        fun onPluginUninstallFailed(message: String?)


    }

    interface P : IPluginInstaller {

        fun handleInstallPluginAction(newPlugin: Plugin, oldPlugin: Plugin?)
        fun handleUninstallPluginAction(plugin: Plugin)
        fun handleIntent(intent: Intent)
    }

    interface M {
        fun installPlugin(plugin: Plugin, fileSystem: IFileSystem)
        fun uninstallPlugin(plugin: Plugin)

        fun callOnDownGrade(old: Plugin?, new: Plugin): Boolean
        fun callOnUpGrade(old: Plugin?, new: Plugin): Boolean

        fun loadPluginManifest(path: String, pluginLoader: IPluginLoader): PluginManifest
        fun loadPlugin(path: String, pluginLoader: IPluginLoader): Plugin
    }
}