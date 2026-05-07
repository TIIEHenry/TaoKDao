package taokdao.main.business.window.window_explorer.popup.tree

import tiiehenry.taokdao.ui.view.treeview.TreeNode
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener
import tiiehenry.taokdao.ui.view.treeview.TreeViewAdapter

interface DirNodeListener : TreeNodeListener<DirNode, DirNodeBinder.ViewHolder> {
    override fun onClick(node: TreeNode<DirNode>, holder: DirNodeBinder.ViewHolder, adapter: TreeViewAdapter<DirNode, DirNodeBinder.ViewHolder>): Boolean {
//        Log.e("DirNodeListener", "onClick: " + node.isExpand)
        holder.ivArrow.apply {
            clearAnimation()
            if (!node.isExpand) {
                rotation = 0F
                animate().rotationBy(90F).start()
            } else {
                rotation = 90F
                animate().rotationBy(-90F).start()
            }
        }
        return false
    }
}