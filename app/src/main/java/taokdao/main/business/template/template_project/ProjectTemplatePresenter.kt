package taokdao.main.business.template.template_project

import taokdao.api.project.template.IProjectTemplate
import taokdao.api.project.template.ProjectTemplatePool
import java.io.File


class ProjectTemplatePresenter(internal val view: ProjectTemplateContract.V) : ProjectTemplateContract.P {
    private val model = ProjectTemplateModel()
    override fun init() {
        model.init()
        view.addDefaultProjectTemplate()
    }

    override fun showChooseDialog(dir: File) {
        view.launchMain {
            val templateList = ProjectTemplatePool.getInstance().all.toMutableList()
            view.showProjectTemplateChooseDialog(templateList, dir)
        }
    }

    override fun showInfo(template: IProjectTemplate) {
        template.showInfo()
    }

    override fun generate(template: IProjectTemplate, dir: File) {
        model.generate(template, dir)
    }

}