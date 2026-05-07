package taokdao.main.business.file.file_open

import android.content.Intent
import taokdao.api.event.senders.ContentSender
import taokdao.api.file.open.IFileOpener
import tiiehenry.ideditor.R
import java.io.File

class FileOpenPresenter(val view: FileOpenContract.V) : FileOpenContract.P {
    private val model = FileOpenModel()


    override fun init() {
        model.initFileOpenerPool()
        view.addDefaultFileOpener()
    }

    override fun requestOpen(path: String): Boolean {
        val file = File(path)
        if (!file.isFile) {
            return false
        }

        if (isFileOpened(path)) {
            ContentSender.messageGlobal(view.getString(R.string.file_opened)).send(view)
            view.contentManager.show(path)
            return true
        }
        val openerList = model.getOpenerListForPath(path)
        return when (openerList.size) {
            0 -> {
                ContentSender.messageGlobal(view.getString(R.string.business_file_open_file_unsupported)).notify(view)
                false
            }
            1 -> {
                openerList.first().click(view, view.contentManager, path)
                true
            }
            else -> {
                showChooseOpenerDialog(path, openerList)
                true
            }
        }
    }

    override fun showChooseOpenerDialog(path: String, openerList: List<IFileOpener>) {
        view.showChooseOpenDialog(path, openerList)
    }

    override fun requestOpenByOpenerId(path: String, openerId: String): Boolean {
        if (isFileOpened(path)) {
            ContentSender.fileOpened(view.getString(R.string.file_opened), path).notify(view)
            return true
        }
        val opener = model.getOpenerById(openerId) ?: return false
        opener.click(view, view.contentManager, path)
        return true
    }

    override fun isFileOpened(path: String): Boolean {
        return view.contentManager.list.map { it.path }.contains(path)
    }

    override fun handleIntent(intent: Intent) {
        intent.data?.path?.let { path ->
            requestOpen(path)
        }
    }
}