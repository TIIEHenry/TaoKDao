package taokdao.window.toolpages.treepage

import android.view.View
import android.widget.ImageView
import taokdao.api.ui.toolpage.content.tree.TreeItem
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeNode
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener

class TreeLeafNode(val treeItem: TreeItem) : LayoutItemType {
    override fun getLayoutId(): Int {
        return layout
    }

    companion object {
         val layout: Int = R.layout.toolpage_public_tree_item_leaf
    }

    class Binder(listener: TreeNodeListener<TreeLeafNode, ViewHolder>) : AbstractTreeItemTreeBinder<TreeLeafNode>(listener) {

        override fun getLayoutId(): Int {
            return layout
        }

        override fun provideViewHolder(itemView: View): ViewHolder {
            return ViewHolder(itemView)
        }

        override fun bindView(holder: ViewHolder, position: Int, node: TreeNode<TreeLeafNode>) {
            val tipItem = node.content.treeItem
            holder.getView<ImageView>(R.id.iv_icon).setImageDrawable(tipItem.icon)
            setTitleHint(holder, tipItem)
        }

    }

}