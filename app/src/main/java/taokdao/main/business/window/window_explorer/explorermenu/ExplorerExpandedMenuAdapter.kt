package taokdao.main.business.window.window_explorer.explorermenu

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import taokdao.api.ui.explorer.menu.ExplorerMenu
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class ExplorerExpandedMenuAdapter : BaseIdRecyclerAdapter<ExplorerMenu>() {
    override fun getItemLayoutId(viewType: Int) = R.layout.explorer_menu_expanded_item

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: ExplorerMenu) {
        holder.findViewById<ImageView>(R.id.iv_icon).apply {
            setImageDrawable(item.icon)
            visibility = if (item.icon != null) View.VISIBLE else View.GONE
        }
        holder.findViewById<TextView>(R.id.tv_name).text = item.label
    }

}