package taokdao.main.business.file.file_open

import taokdao.api.file.open.FileOpenerPool
import taokdao.api.file.open.IFileOpener

class FileOpenModel : FileOpenContract.M {
    override fun initFileOpenerPool() {
        FileOpenerPool.newInstance()
//        FileOpenerPool.getInstance().clear()
    }

    override fun getOpenerById(openerId: String): IFileOpener? {
        return FileOpenerPool.getInstance().get(openerId)
    }

    override fun getOpenerListForPath(path: String): List<IFileOpener> {
        return FileOpenerPool.getInstance().getForPath(path).toList()
    }

}