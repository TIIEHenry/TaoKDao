package taokdao.content.guider.tree

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeBinder
import tiiehenry.taokdao.ui.view.treeview.TreeNode
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener

class EntryNode(val label: String, val click: Runnable, val icon: Drawable? = null) : LayoutItemType {
    override fun getLayoutId(): Int {
        return layout
    }

    companion object {
         val layout: Int = R.layout.contents_guilder_entry
    }


    class Binder(listener: TreeNodeListener<EntryNode, ViewHolder>) : TreeBinder<EntryNode, TreeBinder.ViewHolder>(listener) {
        override fun provideViewHolder(itemView: View): ViewHolder {
            return ViewHolder(itemView)
        }

        override fun bindView(holder: ViewHolder, position: Int, node: TreeNode<EntryNode>) {
            val entry = node.content
            holder.getView<ImageView>(R.id.iv_icon).setImageDrawable(entry.icon)
            holder.getView<TextView>(R.id.tv_name).text = HtmlCompat.fromHtml("<u>" + entry.label + "</u>", FROM_HTML_MODE_LEGACY)
        }

        override fun getLayoutId(): Int {
            return layout
        }

    }
}