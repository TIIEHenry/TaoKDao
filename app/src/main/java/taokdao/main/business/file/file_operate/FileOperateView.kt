package taokdao.main.business.file.file_operate

import taokdao.api.file.operate.FileOperatorPool
import taokdao.api.file.operate.IFileOperator
import taokdao.main.business.file.file_operate.chooser.FileOperatorChooserAdapter
import taokdao.main.business.file.file_operate.operators.*
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import java.io.File

interface FileOperateView : FileOperateContract.V {


    override fun addDefaultFileOperator() {
        FileOperatorPool.getInstance().addAll(arrayOf(
                DeleteFileOrDir(this),
                RenameFileOrDir(this),
                NewFile(this),
                NewFolder(this),
//                CreateFromTemplate(this),
                OpenAsTxt(this),
                OpenProject(this))
        )
    }

    override fun showOperateDialog(file: File, operatorList: List<IFileOperator>): IDialog {
        val menuList = operatorList.sortedBy { it.label }
        val adapter = FileOperatorChooserAdapter(menuList)


        return Dialogs.global
                .asList()
                .typeCustom()
                .title(file.name)
                .adapter(adapter)
//                .negativeText()
                .show().apply {
                    adapter.setOnItemClickListener { _, item, _ ->
                        val isSuccess = item.call(main, file.absolutePath)
                        if (!isSuccess)
                            main.notify(R.string.business_file_operate_operate_failed)
                        dismiss()
                    }
                }
    }
}