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

class NewFolder(val main: IMainContext) : SimpleIFileOperator(
        Properties("newFolder", main.context, R.string.file_operators_newfolder_label)
) {
    override fun call(main: IMainContext, path: String): Boolean {
        val file = File(path)
        Dialogs.global
                .asInput()
                .title(R.string.file_operators_newfolder_dialog_title)
                .inputTypeFile()
                .inputRangeFile()
                .allowEmptyInput(false)
                .autoDismiss(false)
                .input("",
                        "") { dialog, input ->
                    var parentFile = file
                    if (file.isFile) {
                        parentFile = file.parentFile ?: return@input
                    }
                    var msg = getNameError(parentFile, input.toString())
                    if (msg == null) {
                        val newFile = File(parentFile, input.toString())
                        newFile.mkdirs()
                        msg = if (newFile.exists()) {
                            MainAction.onFileCreated.runObservers(main)
                            dialog.dismiss()
                            main.getString(R.string.file_operators_newfolder_success)
                        } else
                            main.getString(R.string.file_operators_newfolder_failed)
                    }
                    main.send(msg)
                }
                .negativeText()
                .positiveText()
                .show()
                .addFileNameChecker(file)
        return true
    }
}