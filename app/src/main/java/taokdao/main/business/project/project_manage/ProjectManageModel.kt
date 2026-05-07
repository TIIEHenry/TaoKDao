package taokdao.main.business.project.project_manage

import taokdao.api.project.bean.Project
import taokdao.api.project.bean.ProjectConfigJson
import taokdao.api.project.load.IProjectLoader
import taokdao.main.business.project.project_manage.load.ProjectLoader
import java.io.File

class ProjectManageModel : ProjectManageContract.M {

    private var currentProject: Project? = null
    override fun setProject(project: Project?) {
        currentProject = project
    }

    override fun getProject(): Project? {
        return currentProject
    }

    override fun isOpenedProject(): Boolean {
        return getProject() != null
    }


    private var projectLoader: IProjectLoader = ProjectLoader()
    override fun setProjectLoader(iProjectLoader: IProjectLoader) {
        this.projectLoader = iProjectLoader
    }

    override fun getProjectLoader(): IProjectLoader = projectLoader

    override fun isProjectConfigFile(file: File): Boolean {
        return file.name == ProjectConfigJson.configFileName
    }

    override fun getProjectConfigFileForDir(file: File): File {
        return getProjectLoader().getProjectConfigFile(file)
    }

    override fun loadProjectFormConfigFile(configFile: File): Project {
        val config = getProjectLoader().loadProjectConfigFromFile(configFile)
        return getProjectLoader().loadProject(config)
    }

    override fun applyProjectPluginsForProjectOpened(project: Project) {
        for (plugin in project.plugins) {
            plugin.plugin.onProjectOpened(project, plugin.parameters)
        }
    }

    override fun applyProjectPluginsForProjectClosed(project: Project) {
        for (plugin in project.plugins) {
            plugin.plugin.onProjectClosed(project)
        }
    }
}
