package taokdao.main.business.plugin.plugin_install

import android.content.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import taokdao.api.internal.InnerIdentifier
import taokdao.api.plugin.bean.Plugin
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import java.io.File
import java.io.IOException


class PluginInstallPresenter(internal val view: PluginInstallContract.V) : PluginInstallContract.P {
    private val model = PluginInstallModel()

    override fun handleIntent(intent: Intent) {
        if (intent.getStringExtra(InnerIdentifier.Intent.PARAMETER_ACTION) == InnerIdentifier.Intent.ACTION_INSTALL_PLUGIN) {
            requestInstallFromIntent(intent)
        }
//        view.pluginManager.pluginInstaller.requestInstallFromIntent(intent)
    }

    /**
     * 发起安装插件的请求
     *
     * @param file 插件文件路径
     * @return 是否接受处理
     */
    override fun requestInstallFromFile(file: File): Boolean {
        when (file.extension) {
            "apk" -> {
                val loadingDialog = view.showLoadingPluginDialog()
                view.async {
                    try {
                        val plugin = model.loadPlugin(file.path, view.pluginLoader)

                        launch(Dispatchers.Main){
                            val oldPlugin = view.main.pluginManager.getPlugin(plugin.id)
                            view.showPluginInfoDialogForInstall(plugin, oldPlugin)
                        }
//                        view.launchMain {
//                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        view.onLoadPluginError(e.message)
                    } finally {
                        view.launchMain {
                            loadingDialog.dismiss()
                        }
                    }
                }
                return true
            }
        }
        return false
    }

    /**
     * 发起安装插件的请求
     *
     * @param intent Intent
     * @return 是否接受处理
     */
    override fun requestInstallFromIntent(intent: Intent): Boolean {
        var handled = tryInstallFormIntentForPath(intent)
        if (!handled) {
            handled = tryInstallFormIntentForPackage(intent)
        }
        return handled
    }

    private fun tryInstallFormIntentForPath(intent: Intent): Boolean {
        val filesPath = intent.getStringExtra(InnerIdentifier.Intent.PARAMETER_PATH) ?: return false
        val file = File(filesPath)
        if (file.isFile) {
            return requestInstallFromFile(file)
        }
        return false
    }

    private fun tryInstallFormIntentForPackage(intent: Intent): Boolean {
        val packageName = intent.getStringExtra(InnerIdentifier.Intent.PARAMETER_PACKAGE)
                ?: return false
//        Log.e(javaClass.simpleName, "tryInstallFormIntentForPackage: " + packageName)
        val manager = view.context.packageManager
        val apkPath = manager.getPackageInfo(packageName, 0).applicationInfo?.publicSourceDir?:return false
        val file = File(apkPath)
        if (file.isFile) {
            return requestInstallFromFile(file)
        }
        return false
    }

    /**
     * 发起卸载插件的请求
     *
     * @param id 插件id
     * @return 是否接受处理
     */
    override fun requestUninstall(id: String): Boolean {
        val plugin = view.main.pluginManager.getPlugin(id) ?: return false
        view.showPluginInfoDialogForUninstall(plugin)
        return true
    }

    override fun handleUninstallPluginAction(plugin: Plugin) {
        Dialogs.global
                .asLoading()
                .addLoadingTask(R.string.business_plugin_uninstall_dialog_uninstalling) { dialog ->
                    try {
                        uninstallPlugin(plugin)
                        view.onPluginUninstallSuccess()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        view.onPluginUninstallFailed(e.message)
                    } finally {
                        plugin.onDestroy()
                        view.launchMain {
                            dialog.dismiss()
                        }
                    }
                }
                .minDisplayTime(500)
                .cancelable(false)
                .cancelOnTouchOutside(false)
                .show()
    }

    override fun handleInstallPluginAction(newPlugin: Plugin, oldPlugin: Plugin?) {
        Dialogs.global
                .asLoading()
                .addLoadingTask(R.string.business_plugin_install_dialog_installing) { dialog ->
                    try {
                        model.callOnDownGrade(oldPlugin, newPlugin)
                        oldPlugin?.onDestroy()
                        if (oldPlugin != null) {
                            uninstallPlugin(oldPlugin)
                        }
                        installPlugin(newPlugin)
                        view.onPluginInstallSuccess()
                        model.callOnUpGrade(oldPlugin, newPlugin)
                        newPlugin.onCreate()
                        newPlugin.onInit()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        view.onPluginInstallFailed(e.message)
                    } finally {
                        view.launchMain {
                            dialog.dismiss()
                        }
                    }
                }
                .minDisplayTime(500)
                .cancelable(false)
                .cancelOnTouchOutside(false)
                .show()

    }

    @Throws(IOException::class)
    override fun installPlugin(plugin: Plugin) {
        model.installPlugin(plugin, view.fileSystem)
    }

    @Throws(IOException::class)
    override fun uninstallPlugin(plugin: Plugin) {
        model.uninstallPlugin(plugin)
    }
}