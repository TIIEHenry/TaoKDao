package taokdao.main.business.file.file_operate.operators

import taokdao.api.data.bean.Properties
import taokdao.api.main.IMainContext
import taokdao.api.main.action.MainAction
import taokdao.main.business.file.file_operate.operator.SimpleIFileOperator
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.io.Filej
import tiiehenry.ideditor.R
import java.io.File

class DeleteFileOrDir(val main: IMainContext) : SimpleIFileOperator(
        Properties("delete", main.context, R.string.file_operators_delete_label)
) {

    override fun call(main: IMainContext, path: String): Boolean {
        Dialogs.global
                .asConfirm()
                .title(R.string.file_operators_delete_dialog_title)
                .content(path)
                .positiveText()
                .negativeText()
                .onPositive { _ ->
                    Filej(path).deleteAll()
                    if (!File(path).exists()) {
                        MainAction.onFileDeleted.runObservers(main)
                    }
                }
                .show()
        return true
    }

}