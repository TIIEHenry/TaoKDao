package taokdao.main.business.file.file_open

import taokdao.api.file.open.FileOpenerPool
import taokdao.api.file.open.IFileOpener
import taokdao.codeeditor.CodeEditorOpener
import taokdao.main.business.file.file_open.chooser.OpenerChooserDialog
import taokdao.main.business.file.file_open.opener.FileTemplateOpener
import taokdao.main.business.file.file_open.opener.ImageViewerOpener
import tiiehenry.android.ui.dialogs.api.IDialog

interface FileOpenView : FileOpenContract.V {
    override fun addDefaultFileOpener() {
        val codeEditorOpener = CodeEditorOpener(this)
        val imageViewerOpener = ImageViewerOpener(this)
        val fileTemplateOpener = FileTemplateOpener(this)
        FileOpenerPool.getInstance().let {
            it.add(codeEditorOpener)
            it.add(imageViewerOpener)
            it.add(fileTemplateOpener)
        }
    }

    override fun showChooseOpenDialog(path: String, openerList: List<IFileOpener>): IDialog {
        return OpenerChooserDialog.show(this, path, openerList)
    }
}