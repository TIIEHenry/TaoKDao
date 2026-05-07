package taokdao.main.business.content_manage.menu

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import taokdao.api.ui.content.menu.ControlMenu
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class ContentMenuAdapter : BaseIdRecyclerAdapter<ControlMenu>() {
    override fun getItemLayoutId(viewType: Int) = R.layout.content_menu_item
    override fun bindData(holder: RecyclerViewHolder, position: Int, item: ControlMenu) {
        holder.findViewById<ImageView>(R.id.iv_icon).apply {
            setImageDrawable(item.icon)
            visibility = if (item.icon != null) View.VISIBLE else View.GONE
        }
        holder.findViewById<TextView>(R.id.tv_name).text = item.label
    }
}