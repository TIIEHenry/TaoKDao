package taokdao.window.toolpages.controlmenu

import android.view.View
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.menu.ControlMenu
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.UIRecyclerPopup

class ToolGroupControlMenuPopup(val main: IMainContext, val defaultMenuList: MutableList<ControlMenu>, val menuList: MutableList<ControlMenu>?)
    : UIRecyclerPopup<ControlMenu>(main.activity, ToolGroupControlMenuAdapter()/*,R.style.Animation_Popup_RightTop*/) {
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
        ToolGroupControlMenuPopup(main, defaultMenuList, subMenuList).show(view)
    }
}