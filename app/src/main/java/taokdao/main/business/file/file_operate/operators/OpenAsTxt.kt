package taokdao.main.business.file.file_operate.operators

import taokdao.api.data.bean.Properties
import taokdao.api.main.IMainContext
import taokdao.main.business.file.file_open.opener.SuffixOpenerChooserOpener
import taokdao.main.business.file.file_operate.operator.SimpleIFileOperator
import tiiehenry.ideditor.R
import java.io.File

class OpenAsTxt(val main: IMainContext) : SimpleIFileOperator(
        Properties("openAsTxt", main.context, R.string.file_operators_openastext_label)
) {
    override fun isSupport(path: String): Boolean {
        return File(path).isFile
    }

    override fun call(main: IMainContext, path: String): Boolean {
        SuffixOpenerChooserOpener(main, path, "txt").click()
        return true
    }
}