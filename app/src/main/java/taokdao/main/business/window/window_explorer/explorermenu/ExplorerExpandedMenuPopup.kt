package taokdao.main.business.window.window_explorer.explorermenu

import android.view.View
import taokdao.api.main.IMainContext
import taokdao.api.ui.explorer.menu.ExplorerMenu
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.UIRecyclerPopup

class ExplorerExpandedMenuPopup(main: IMainContext) : UIRecyclerPopup<ExplorerMenu>(
        main.activity, ExplorerExpandedMenuAdapter(), DIRECTION_BOTTOM, R.style.Animation_Popup_RightTop) {
    init {
        setBoxBackground(R.drawable.ui_drawable_popup_background_righttop)
        setAnimStyle(ANIM_GROW_FROM_RIGHT)
        setPreferredDirection(DIRECTION_BOTTOM)
//        adapter.refresh(listItems)s
        adapter.setOnItemClickListener { itemView, item, _ ->
            if (item.click(itemView)) {
                dismiss()
            } else {
                //show submenu
            }
        }
        adapter.setOnItemLongClickListener { itemView, item, _ ->
            if (item.longClick(itemView)) {
                dismiss()
            } else {
                //show submenu
            }
        }

        create(-2, main.getDimen(R.dimen.explorer_menu_expanded_item_H) * adapter.itemCount)
//        (recyclerView.parent as View).setBackgroundColor(main.getAttrColor(R.attr.main_content_background_color))

    }


    fun showAt(view: View) {
        setPositionOffsetX(-view.width / 2)
        setPositionOffsetYWhenBottom(-view.width / 2)
        show(view)
    }
}
