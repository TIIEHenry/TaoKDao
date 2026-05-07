package taokdao.main.business.file.file_operate.operators

import taokdao.api.data.bean.Properties
import taokdao.api.main.IMainContext
import taokdao.main.business.file.file_operate.operator.SimpleIFileOperator
import tiiehenry.ideditor.R

class CreateFromTemplate(val main: IMainContext) : SimpleIFileOperator(
        Properties("createFromTemplate", main.context, R.string.file_operators_createfromtemplate_label)
) {
    override fun call(main: IMainContext, path: String): Boolean {
        main.fileTemplateGenerator.showChooseDialog(main.fileSystem.projectDir)
        return true
    }
}