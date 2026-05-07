package taokdao.window.toolpages.treepage

import android.view.View
import android.widget.ImageView
import taokdao.api.ui.toolpage.content.tree.TreeItem
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeNode
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener

class TreeBranchNode(val treeItem: TreeItem) : LayoutItemType {
    override fun getLayoutId(): Int {
        return layout
    }

    companion object {
         val layout: Int = R.layout.toolpage_public_tree_item_branch
    }

    class Binder(listener: TreeNodeListener<TreeBranchNode, ViewHolder>) : AbstractTreeItemTreeBinder<TreeBranchNode>(listener) {
        override fun getLayoutId(): Int {
            return layout
        }

        override fun provideViewHolder(itemView: View): ViewHolder {
            return ViewHolder(itemView)
        }

        override fun bindView(holder: ViewHolder, position: Int, node: TreeNode<TreeBranchNode>) {
            val tipItem = node.content.treeItem

            holder.getView<ImageView>(R.id.iv_arrow).rotation = if (node.isExpand) 90F else 0.toFloat()
            holder.getView<ImageView>(R.id.iv_icon).setImageDrawable(tipItem.icon)
            setTitleHint(holder, tipItem)
        }

    }

}