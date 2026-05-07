package taokdao.main.business.menu_catagory.popup

import android.widget.TextView
import org.jetbrains.anko.find
import taokdao.api.main.menu.MainMenu
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class MainMenuAdapter : BaseIdRecyclerAdapter<MainMenu>() {

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: MainMenu) {
        holder.itemView.find<TextView>(R.id.tv_label).apply {
            text = item.label
        }
    }

    override fun getItemLayoutId(viewType: Int) = R.layout.toolbar_mainmenu_item

}