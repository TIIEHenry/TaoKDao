package taokdao.main.business.content_manage.list

import android.graphics.Point
import android.view.View
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.IContent
import tiiehenry.ideditor.R
import tiiehenry.ktx.content.getWidth
import tiiehenry.taokdao.ui.view.UIRecyclerPopup

class ContentListPopup(val main: IMainContext, val dataList: MutableList<IContent>)
    : UIRecyclerPopup<IContent>(main.activity, ContentListAdapter(main)/*, R.style.Animation_Popup_RightTop*/) {

    init {
        setBoxBackground(R.drawable.ui_drawable_popup_background_from_top)
//        setAnimStyle(ANIM_GROW_FROM_RIGHT)
//        setPreferredDirection(DIRECTION_BOTTOM)
        adapter.apply {
            refresh(dataList)
            setOnItemClickListener { _, item, _ ->
                dismiss()
                main.contentManager.show(item)
            }
            setOnItemLongClickListener { _, item, _ ->
                main.notify(item.path)
            }
        }


        var h = context.getWidth() * 9 / 16
        val w = context.getWidth() * 3 / 4

        val itemH = main.getDimen(R.dimen.toolbar_contentlist_item_H)

        val actualH = dataList.size * itemH
        h = if (actualH < h) {
            actualH
        } else {
            (h / itemH) * itemH
        }

        create(w, h)
    }


    fun showAt(view: View) {
        setPositionOffsetX(-view.width / 2)
//        setPositionOffsetYWhenBottom(-view.width/2)
        show(view)
    }

    override fun onShow(attachedView: View?): Point {
        val s = super.onShow(attachedView)
//        popupWindow.animationStyle = R.style.Animation_Popup_RightTop
        return s
    }
}