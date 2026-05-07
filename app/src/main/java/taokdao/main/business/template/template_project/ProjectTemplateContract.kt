package taokdao.main.business.template.template_project

import taokdao.api.project.template.IProjectTemplate
import taokdao.api.project.template.IProjectTemplateGenerator
import taokdao.main.IMainView
import java.io.File


interface ProjectTemplateContract {
    interface V : IMainView {
        fun showProjectTemplateChooseDialog(templateList: MutableList<IProjectTemplate>, dir: File)
        fun addDefaultProjectTemplate()

        val projectTemplatePresenter: ProjectTemplatePresenter

    }

    interface P : IProjectTemplateGenerator {

        fun init()
    }

    interface M {
        fun generate(template: IProjectTemplate, dir: File)
        fun init()

    }
}