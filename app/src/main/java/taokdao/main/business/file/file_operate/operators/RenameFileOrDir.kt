package taokdao.main.business.file.file_operate.operators

import taokdao.api.data.bean.Properties
import taokdao.api.main.IMainContext
import taokdao.api.main.action.MainAction
import taokdao.main.business.file.file_operate.operator.SimpleIFileOperator
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.dialog.addFileNameChecker
import tiiehenry.taokdao.ui.dialog.getNameError
import tiiehenry.taokdao.ui.dialog.inputRangeFile
import tiiehenry.taokdao.ui.dialog.inputTypeFile
import java.io.File

class RenameFileOrDir(val main: IMainContext) : SimpleIFileOperator(
        Properties("rename", main.context, R.string.file_operators_rename_label)
) {

    override fun call(main: IMainContext, path: String): Boolean {
        val file = File(path)
        Dialogs.global
                .asInput()
                .title(R.string.file_operators_rename_dialog_title)
                .inputTypeFile()
                .allowEmptyInput(false)
                .autoDismiss(false)
                .input("name.suffix",
                        file.name) { dialog, input ->
                    var msg = getNameError(file.parentFile, input.toString())
                    if (msg == null) {
                        val newFile = File(file.parent, input.toString())
                        file.renameTo(newFile)
                        msg = if (newFile.exists() && !file.exists()) {
                            MainAction.onFileRenamed.runObservers(main)
                            main.getString(R.string.file_operators_rename_success)
                        } else
                            main.getString(R.string.file_operators_rename_failed)
                        dialog.dismiss()
                    }
                    main.send(msg)
                }
                .inputRangeFile()
                .negativeText()
                .positiveText()
                .show()
                .addFileNameChecker(file)
        return true
    }

}