package taokdao.main.business.project.project_manage

import taokdao.api.event.senders.ProjectLoaderSender
import taokdao.api.main.action.MainAction
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.dialog.ErrorDialog


interface ProjectManageView : ProjectManageContract.V {
    override fun onOpenProjectFailed(message: String?) {
        launchMain {
            if (message != null) {
                ProjectLoaderSender.loadConfigError(message).send(main)
            } else {
                ErrorDialog(main.context, context.getString(R.string.business_project_manage_openfailed_error)).show()
            }
        }
    }

    override fun onOpenProjectFailedPluginError(message: String?) {
        if (message != null) {
            ProjectLoaderSender.pluginIncompatible(R.string.business_project_manage_openfailed_error_plugin).send(main)
            ProjectLoaderSender.exception(message).send(main)
        }
    }

    override fun onOpenProjectFailed(message: Int) {
        onOpenProjectFailed(getString(message))
    }

    override fun notifyOnProjectOpened() {
        MainAction.onProjectOpened.runObservers(main)
    }

    override fun notifyOnProjectClosed() {
        MainAction.onProjectClosed.runObservers(main)
    }
}