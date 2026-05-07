package taokdao.main.business.window.window_explorer.popup.tree

import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeNode
import java.io.File
import java.util.*


fun addProjectFilesDirNode(parent: TreeNode<LayoutItemType>, dir: File) {
    val dirFiles = dir.listFiles()
    if (dirFiles != null) {
        var dirFileList = dirFiles.toList()
        if (dirFiles.size > 1)
            dirFileList = dirFileList.sortedBy {
                it.name.toLowerCase(Locale.getDefault())
            }.sortedBy {
                it.isFile
            }
        for (f in dirFileList) {
            if (f.isDirectory) {
                val node = TreeNode<LayoutItemType>(DirNode(f)).apply {
                    addProjectFilesDirNode(this, f)
                }
                parent.addChild(node)
            } else if (f.isFile) {
                val node = TreeNode<LayoutItemType>(FileNode(f))
                parent.addChild(node)
            }
        }
    }
}