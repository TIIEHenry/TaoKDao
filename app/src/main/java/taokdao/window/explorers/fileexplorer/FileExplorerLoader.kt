package taokdao.window.explorers.fileexplorer

import androidx.core.content.ContextCompat
import taokdao.main.IMainView
import taokdao.main.business.drawable_manage.DrawableManagePresenter
import taokdao.main.business.drawable_manage.DrawableManager
import tiiehenry.ktx.lang.ifTrue
import tiiehenry.ideditor.R
import java.io.File
import java.util.*

object FileExplorerLoader {
    fun getExplorerItemList(c: IMainView, dir: File, filter: String): ArrayList<FileExplorerItem> {
        val list = ArrayList<FileExplorerItem>()

        val files = dir.listFiles { _, name -> name?.contains(filter) ?: false }
        files?.forEach {
            if (it.isFile) {
                val manager = c.drawableManager
                val icon = if (DrawableManager.isImageSuffix(it.extension) && files.size <= 5)
                    manager.getDrawableForSuffix(it.extension) ?: manager.defaultDrawableForSuffix
                else
                    DrawableManagePresenter.getForFile(c, it)
                list.add(FileExplorerItem(icon, it.name, it))
            } else if (it.isDirectory) {
                val icon = c.main.drawableManagePresenter.let { p ->
                    p.getDrawableForDirName(it.name) ?: p.defaultDrawableForDirName
                }
                list.add(FileExplorerItem(icon, it.name, it))
            }

        }
        list.sortBy {
            it.label.uppercase(Locale.getDefault())
        }
        list.sortBy {
            it.file.isFile
        }
//		list.add(0, ExplorerItem(ContextCompat.getDrawable(c, R.drawable.ic_folder_extractedfolder), "新建", dir, ExplorerItem.Type.OP))
        dir.parentFile?.list()?.isNotEmpty()?.ifTrue {
            list.add(0, FileExplorerItem(ContextCompat.getDrawable(c.context, R.drawable.ic_folder_upfoldex), "上一级", dir, FileExplorerItem.Type.UPLEVEL))
        }
        return list
    }


}
