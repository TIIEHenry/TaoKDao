package taokdao.window.toolpages.treepage

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import taokdao.api.ui.toolpage.content.tree.TreeItem
import tiiehenry.ideditor.R
import tiiehenry.ktx.res.getAttrColor
import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeBinder
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener

abstract class AbstractTreeItemTreeBinder<T : LayoutItemType>(listener: TreeNodeListener<T, ViewHolder>) : TreeBinder<T, TreeBinder.ViewHolder>(listener) {
    protected fun setTitleHint(holder: ViewHolder, tipItem: TreeItem) {
        val title = tipItem.title ?: ""
        val hint = tipItem.hint ?: ""
        val textView = holder.getView<TextView>(R.id.tv_name)
        val s = "$title  $hint"
        val foregroundColorSpan = ForegroundColorSpan(textView.context.getAttrColor(ideditor.api.skin.R.attr.main_content_foreground_color_hint))
        textView.text = SpannableString(s).apply {
            setSpan(foregroundColorSpan, title.length + 2, title.length + 2 + hint.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
    }
}