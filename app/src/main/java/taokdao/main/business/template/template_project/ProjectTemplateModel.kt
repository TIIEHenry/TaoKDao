package taokdao.main.business.template.template_project

import taokdao.api.project.template.IProjectTemplate
import taokdao.api.project.template.ProjectTemplatePool
import java.io.File

class ProjectTemplateModel : ProjectTemplateContract.M {
    override fun init() {
        ProjectTemplatePool.newInstance()
    }

    override fun generate(template: IProjectTemplate, dir: File) {
        template.generate(dir)
    }
}
