package taokdao.main.business.project.project_manage

import androidx.annotation.StringRes
import taokdao.api.project.bean.Project
import taokdao.api.project.load.IProjectLoader
import taokdao.api.project.manage.IProjectManager
import taokdao.main.IMainView
import java.io.File


interface ProjectManageContract {
    interface V : IMainView {
        fun onOpenProjectFailed(message: String?)
        fun onOpenProjectFailedPluginError(message: String?)
        fun onOpenProjectFailed(@StringRes message: Int)
        fun notifyOnProjectOpened()
        fun notifyOnProjectClosed()

        val projectManagePresenter: ProjectManagePresenter

    }

    interface P : IProjectManager

    interface M {
        fun setProject(project: Project?)
        fun getProject(): Project?
        fun isOpenedProject(): Boolean

        fun setProjectLoader(iProjectLoader: IProjectLoader)
        fun getProjectLoader(): IProjectLoader
        fun isProjectConfigFile(file: File): Boolean
        fun loadProjectFormConfigFile(configFile: File): Project
        fun applyProjectPluginsForProjectOpened(project: Project)
        fun applyProjectPluginsForProjectClosed(project: Project)
        fun getProjectConfigFileForDir(file: File): File
    }
}