package taokdao.main.business.file.file_operate

import taokdao.api.file.operate.IFileOperateManager
import taokdao.api.file.operate.IFileOperator
import taokdao.main.IMainView
import tiiehenry.android.ui.dialogs.api.IDialog
import java.io.File

interface FileOperateContract {

    interface V : IMainView {
        val fileOperatePresenter: FileOperatePresenter
        fun addDefaultFileOperator()
        fun showOperateDialog(file: File, operatorList: List<IFileOperator>): IDialog
    }

    interface P : IFileOperateManager

    interface M {
        fun initFileOperatorPool()

    }
}