package taokdao.main.business.exit_control

import taokdao.api.plugin.bean.PluginType
import taokdao.main.business.session_control.SessionControlVariable
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R


interface ExitControlView : ExitControlContract.V {
    override fun closeWindowIfShown(): Boolean {
        when {
            explorerWindow.isWindowShown -> {
                explorerWindow.hideWindow()
            }
            toolPageWindow.isWindowShown -> {
                toolPageWindow.hideWindow()
            }
            else -> {
                return false
            }
        }
        return true
    }

    override fun showExitConfirmDialog() {
        Dialogs.global
                .asConfirm()
                .title(R.string.business_exit_control_confirm_dialog_title)
                .content(R.string.business_exit_control_confirm_dialog_content)
                .negativeText()
                .positiveText()
                .onPositive {
                    pluginManager.callPluginOnDestroy(PluginType.TYPE_ENGINE)
                    pluginManager.callPluginOnDestroy(PluginType.TYPE_COMMON)
                    SessionControlVariable.saveOpenedFiles = false
                    activity.finish()
                }
                .show()
    }
}