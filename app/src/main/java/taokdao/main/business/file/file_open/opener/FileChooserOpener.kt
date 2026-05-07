package taokdao.main.business.file.file_open.opener

import taokdao.api.data.bean.Properties
import taokdao.api.file.open.FileOpenerPool
import taokdao.api.file.open.wrapped.FileOpener
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.manage.IContentManager
import tiiehenry.ideditor.R


class FileChooserOpener(val main: IMainContext, val path: String)
    : FileOpener(Properties("default", main.context, R.string.business_file_open_opener_filechooseopener_label, R.string.business_file_open_opener_filechooseopener_description), null) {


    override fun click(main: IMainContext, manager: IContentManager, path: String) {
        main.fileOpenManager.showChooseOpenerDialog(path, FileOpenerPool.getInstance().all.toList())
    }


    fun click() {
        click(main, main.contentManager, path)
    }

    fun longClick() {
        longClick(main, main.contentManager, path)
    }
}