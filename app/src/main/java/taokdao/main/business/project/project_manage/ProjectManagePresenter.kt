package taokdao.main.business.project.project_manage

import taokdao.api.project.bean.Project
import taokdao.api.project.load.IProjectLoader
import tiiehenry.ideditor.R
import java.io.File


class ProjectManagePresenter(internal val view: ProjectManageContract.V) : ProjectManageContract.P {
    private val model = ProjectManageModel()
    override fun setProject(project: Project?) {
        model.setProject(project)
    }

    override fun getProject(): Project? {
        return model.getProject()
    }

    override fun isOpenedProject(): Boolean {
        return model.isOpenedProject()
    }

    override fun setProjectLoader(projectLoader: IProjectLoader) {
        return model.setProjectLoader(projectLoader)
    }

    override fun getProjectLoader(): IProjectLoader {
        return model.getProjectLoader()
    }

    override fun reopenProject(): Boolean {
        project?.configFile?.let {
            closeProject()
            return openProject(it)
        }
        return false
    }

    override fun openProject(file: File): Boolean {
        if (isOpenedProject) {
            return false
        }
        val configFile = if (file.isFile) {
            val isConfigFile = model.isProjectConfigFile(file)
            if (!isConfigFile) {
                view.onOpenProjectFailed(R.string.business_project_manage_openfailed_notconfig)
                return false
            }
            file
        } else if (file.isDirectory) {
            val it = model.getProjectConfigFileForDir(file)
            if (it.isFile) {
                it
            } else {
                view.onOpenProjectFailed(R.string.business_project_manage_openfailed_notprojectdir)
                return false
            }
        } else
            return false
        try {
            val project = model.loadProjectFormConfigFile(configFile)
            setProject(project)
            model.applyProjectPluginsForProjectOpened(project)
        } catch (e: Exception ) {
            e.printStackTrace()
            view.onOpenProjectFailed(e.message)
            return false
        }catch (e:Error){
            e.printStackTrace()
            view.onOpenProjectFailedPluginError(e.message)
            return false
        }
        view.notifyOnProjectOpened()
        return true
    }

    override fun closeProject() {
        if (isOpenedProject) {
            project?.let {
                model.applyProjectPluginsForProjectClosed(it)
            }
            project = null
            view.notifyOnProjectClosed()
        }
    }


}