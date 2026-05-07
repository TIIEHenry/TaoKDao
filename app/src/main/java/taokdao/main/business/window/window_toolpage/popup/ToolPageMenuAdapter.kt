package taokdao.main.business.window.window_toolpage.popup

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import taokdao.api.ui.toolpage.menu.ToolPageMenu
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class ToolPageMenuAdapter : BaseIdRecyclerAdapter<ToolPageMenu>() {

    init {
        setOnItemClickListener { itemView, item, _ ->
            if (item.click(itemView)) {
//                dismiss()
            } else {
//                showSubMenu(itemView, item.subMenuList)
            }
        }
        setOnItemLongClickListener { itemView, item, _ ->
            if (item.longClick(itemView)) {
//                dismiss()
            } else {
//                showSubMenu(itemView, item.subMenuList)
            }
        }
    }

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: ToolPageMenu) {
        holder.itemView.apply {
            find<ImageView>(R.id.iv_tabtool_bar_menu_item_icon).let {
                if (item.showIcon) {
                    it.setImageDrawable(item.icon)
                    it.visibility = View.VISIBLE
                } else {
                    it.visibility = View.GONE
                }
            }
            find<TextView>(R.id.tv_tabtool_bar_menu_item_label).let {
                if (item.showLabel) {
                    it.text = item.label
                    it.visibility = View.VISIBLE
                } else {
                    it.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemLayoutId(viewType: Int) = R.layout.toolpage_bar_menu_item


}