package taokdao.main.toolbar

import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class ToolbarMenuAdapter : BaseIdRecyclerAdapter<ToolbarMenu>() {
    init {
        setOnItemClickListener { itemView, item, _ ->
            item.click.onClick(itemView)
        }
        setOnItemLongClickListener { itemView, item, _ ->
            item.longClick?.onClick(itemView)
        }
    }

    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.toolbar_menu_item
    }

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: ToolbarMenu) {
        holder.getImageView(R.id.ibtn_toolbar_menu_item).apply {
            setImageDrawable(item.icon)
        }
    }

}