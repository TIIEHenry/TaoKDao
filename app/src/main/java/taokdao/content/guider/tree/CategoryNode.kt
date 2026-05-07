package taokdao.content.guider.tree

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeBinder
import tiiehenry.taokdao.ui.view.treeview.TreeNode
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener

class CategoryNode(val label: String, val icon: Drawable? = null) : LayoutItemType {
    override fun getLayoutId(): Int {
        return layout
    }

    companion object {
         val layout: Int = R.layout.contents_guilder_category
    }

    class Binder(listener: TreeNodeListener<CategoryNode, ViewHolder>) : TreeBinder<CategoryNode, TreeBinder.ViewHolder>(listener) {
        override fun provideViewHolder(itemView: View): ViewHolder {
            return ViewHolder(itemView)
        }

        override fun bindView(holder: ViewHolder, position: Int, node: TreeNode<CategoryNode>) {
            val category = node.content
            holder.getView<ImageView>(R.id.iv_icon).setImageDrawable(category.icon)
            holder.getView<TextView>(R.id.tv_name).text = category.label
        }

        override fun getLayoutId(): Int {
            return layout
        }

    }

}