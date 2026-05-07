package taokdao.main.business.file.file_open

import android.content.Intent
import taokdao.api.file.open.IFileOpenManager
import taokdao.api.file.open.IFileOpener
import taokdao.main.IMainView
import tiiehenry.android.ui.dialogs.api.IDialog

interface FileOpenContract {
    interface V : IMainView {
        val fileOpenPresenter: FileOpenPresenter
        fun addDefaultFileOpener()
        fun showChooseOpenDialog(path: String, openerList: List<IFileOpener>): IDialog
    }

    interface P : IFileOpenManager {
        fun handleIntent(intent: Intent)
    }

    interface M {
        fun initFileOpenerPool()
        fun getOpenerById(openerId: String): IFileOpener?
        fun getOpenerListForPath(path: String): List<IFileOpener>

    }
}