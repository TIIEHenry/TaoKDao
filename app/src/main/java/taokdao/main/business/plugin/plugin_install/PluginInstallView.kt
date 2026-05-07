package taokdao.main.business.plugin.plugin_install

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Process
import android.os.SystemClock
import android.view.View
import taokdao.api.event.senders.PluginInstallerSender
import taokdao.api.plugin.bean.Plugin
import taokdao.main.business.plugin.plugin_manage.launcher.PluginInfoDialog
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.dialog.ErrorDialog
import kotlin.system.exitProcess


interface PluginInstallView : PluginInstallContract.V {
    override fun showLoadingPluginDialog(): IDialog {
        return Dialogs.global
            .asProgress()
            .limitIconToDefaultSize()
            .title(R.string.business_plugin_install_loadplugin_dialog_title)
            .progress(true, 0)
            .progressIndeterminateStyle(false)
            .show()
    }

    override fun onLoadPluginError(message: String?) {
        launchMain {
            if (message != null) {
                PluginInstallerSender.loadPluginError(message).send(main)
            } else {
                ErrorDialog(
                    main.context,
                    context.getString(R.string.business_plugin_install_loadpluginerror)
                ).show()
            }
        }
    }

    override fun showPluginInfoDialogForUninstall(plugin: Plugin) {
        Dialogs.global
            .asCustom()
            .title(plugin.getInformation(main).label)
            .customView(PluginInfoDialog(main, plugin).binding.root, true)
            .positiveText(R.string.business_plugin_install_install_dialog_uninstall)
            .onPositive { dialog ->
                dialog.dismiss()
                pluginInstallPresenter.handleUninstallPluginAction(plugin)
            }
            .negativeText()
            .show()
    }

    override fun showPluginInfoDialogForInstall(newPlugin: Plugin, oldPlugin: Plugin?) {
        var downGrade = false
        val installRes = if (oldPlugin == null) {
            R.string.business_plugin_install_dialog_install
        } else {
            when {
                newPlugin.version.code > oldPlugin.version.code -> {
                    R.string.business_plugin_install_dialog_action_upgrade
                }
                newPlugin.version.code < oldPlugin.version.code -> {
                    downGrade = true
                    R.string.business_plugin_install_dialog_action_degrade
                }
                else -> {
                    R.string.business_plugin_install_dialog_action_recover
                }
            }
        }
        val allowDownGrade = newPlugin.version.downgrade
        val information = newPlugin.getInformation(main)
        Dialogs.global
            .asCustom()
            .title(information.label)
            .customView(PluginInfoDialog(main, newPlugin).binding.apply {
                if (oldPlugin != null) {
                    llVersionOld.visibility = View.VISIBLE
                    tvVersionOld.text = "${oldPlugin.version.name} (${oldPlugin.version.code})"
                }
            }.root, true).apply {
                if (downGrade && !allowDownGrade) {

                } else {
                    positiveText(installRes)
                    onPositive { dialog ->
                        dialog.dismiss()
                        pluginInstallPresenter.handleInstallPluginAction(newPlugin, oldPlugin)
                    }
                }
            }
            .negativeText()
            .show()

    }

    override fun onPluginInstallSuccess() {
        launchMain {
            main.pluginManagePresenter.reloadPluginList()
            Dialogs.global
                .asConfirm()
                .title(R.string.business_plugin_install_install_success_dialog_title)
                .content(R.string.business_plugin_install_install_success_dialog_content)
                .positiveText()
                .onPositive {
                    val intent =
                        main.context.packageManager.getLaunchIntentForPackage(main.context.packageName)
                            ?: return@onPositive
                    val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
                    } else {
                        PendingIntent.FLAG_ONE_SHOT
                    }
                    val restartIntent = PendingIntent.getActivity(
                        main.context.applicationContext,
                        0,
                        intent,
                        flag
                    )
                    val alarmManager =
                        main.context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        alarmManager.setExact(
                            AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime(), restartIntent
                        )
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(
                            AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime(), restartIntent
                        )
                    }
                    alarmManager[AlarmManager.RTC, System.currentTimeMillis() + 1000] =
                        restartIntent
                    exitProcess(0)
                    Process.killProcess(Process.myPid())
//                        activity.startActivity<MainActivity>(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                        android.os.Process.killProcess(android.os.Process.myPid())
                }
                .negativeText()
                .show()
        }
        main.send(R.string.business_plugin_install_dialog_install_success)
    }

    override fun onPluginUninstallSuccess() {
        launchMain {
            main.pluginManagePresenter.reloadPluginList()
            main.send(R.string.business_plugin_install_uninstall_success)
        }
    }

    override fun onPluginUninstallFailed(message: String?) {
        launchMain {
            if (message != null) {
                main.send(R.string.business_plugin_install_uninstall_failed)
                PluginInstallerSender.uninstallError(message)
            } else {
                ErrorDialog(
                    main.context,
                    context.getString(R.string.business_plugin_install_uninstallpluginerror)
                ).show()
            }
        }
    }

    override fun onPluginInstallFailed(message: String?) {
        launchMain {
            if (message == null) {
                main.send(R.string.business_plugin_install_dialog_install_failed)
                PluginInstallerSender.installError(message)
            } else {
                ErrorDialog(
                    main.context,
                    context.getString(R.string.business_plugin_install_installpluginerror)
                ).show()
            }
        }
    }

}