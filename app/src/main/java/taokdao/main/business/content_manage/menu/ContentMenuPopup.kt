package taokdao.main.business.content_manage.menu

import android.view.View
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.menu.ControlMenu
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.UIRecyclerPopup

class ContentMenuPopup(val main: IMainContext, val defaultMenuList: MutableList<ControlMenu>, val menuList: MutableList<ControlMenu>?)
    : UIRecyclerPopup<ControlMenu>(main.activity, ContentMenuAdapter()) {
    init {

        adapter.apply {
            refresh(defaultMenuList)
            setOnItemClickListener { itemView, item, _ ->
                if (item.click(itemView)) {
                    dismiss()
                } else {
                    showSubMenu(itemView, item.subMenuList)
                }
            }
            setOnItemLongClickListener { itemView, item, _ ->
                if (item.longClick(itemView)) {
                    dismiss()
                } else {
                    showSubMenu(itemView, item.subMenuList)
                }
            }
        }


        create(-2, main.getDimen(R.dimen.toolbar_controlmenu_item_H) * adapter.itemCount)
    }

    private fun showSubMenu(view: View, subMenuList: MutableList<ControlMenu>) {
        ContentMenuPopup(main, defaultMenuList, subMenuList).show(view)
    }
}