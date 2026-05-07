package taokdao.main.business.file.file_operate

import taokdao.api.file.operate.IFileOperator
import java.io.File

class FileOperatePresenter(val view: FileOperateContract.V) : FileOperateContract.P {
    val model = FileOperateModel()
    override fun init() {
        model.initFileOperatorPool()
        view.addDefaultFileOperator()
    }

    override fun showOperateDialog(file: File, operatorList: List<IFileOperator>) {
        view.showOperateDialog(file, operatorList)
    }
}