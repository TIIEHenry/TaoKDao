package taokdao.main.business.window.window_explorer.explorermenu

import taokdao.api.ui.explorer.menu.ExplorerMenu
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ExplorerMenuItemBinding

class ExplorerDisplayMenuAdapter : BaseIdRecyclerAdapter<ExplorerMenu>() {
    override fun getItemLayoutId(viewType: Int): Int = R.layout.explorer_menu_item

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: ExplorerMenu) {
        val binding = ExplorerMenuItemBinding.bind(holder.itemView)
        binding.ivIcon.apply {
            setImageDrawable(item.icon)
            contentDescription = item.label
        }
    }

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
}