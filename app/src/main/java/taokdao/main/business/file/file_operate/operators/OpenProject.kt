package taokdao.main.business.file.file_operate.operators

import taokdao.api.data.bean.Properties
import taokdao.api.main.IMainContext
import taokdao.api.project.bean.ProjectConfigJson
import taokdao.main.business.file.file_operate.operator.SimpleIFileOperator
import tiiehenry.ideditor.R
import java.io.File

class OpenProject(val main: IMainContext) : SimpleIFileOperator(
        Properties("openProject", main.context, R.string.file_operators_openproject_label)
) {
    override fun isSupport(path: String): Boolean {
        val file = File(path)
        if (file.isFile) {
            if (file.name == ProjectConfigJson.configFileName) {
                return true
            }
        } else if (file.isDirectory) {
            if (File(file, ProjectConfigJson.configFileName).isFile) {
                return true
            }
        }
        return false
    }

    override fun call(main: IMainContext, path: String): Boolean {
        main.projectManager.closeProject()
        var file = File(path)
        if (file.isFile && file.name == ProjectConfigJson.configFileName) {

        } else if (file.isDirectory) {
            File(file, ProjectConfigJson.configFileName).let {
                if (it.isFile) {
                    file = it
                }
            }
        } else {
            return false
        }
        return main.projectManager.openProject(file)
    }
}