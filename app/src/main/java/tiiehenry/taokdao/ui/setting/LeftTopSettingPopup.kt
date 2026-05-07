package tiiehenry.taokdao.ui.setting


import android.view.View
import taokdao.api.main.IMainContext
import taokdao.api.setting.preference.base.IPreference
import tiiehenry.ktx.content.getHeight
import tiiehenry.ktx.content.getWidth
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.UIRecyclerPopup


class LeftTopSettingPopup(val main: IMainContext, val dataList: MutableList<IPreference<*>>, val view: View)
    : UIRecyclerPopup<IPreference<*>>(main.activity,
        PreferenceAdapter(main, dataList) { list ->
            LeftTopSettingPopup(main, list, view).showAt()
        }, DIRECTION_BOTTOM, R.style.Animation_Popup_LeftTop
) {

    init {
        setBoxBackground(R.drawable.ui_drawable_popup_background_lefttop)


        val maxH = context.getHeight() * 9 / 16
        val w = context.getWidth() * 3 / 4

        val itemH = main.getDimen(R.dimen.setting_preference_item_H)

        val actualH = dataList.size * itemH
        val h = if (actualH < maxH) {
            actualH
        } else {
            (maxH / itemH) * itemH
        }

        create(w, h)
    }


    fun showAt() {
        setPositionOffsetX(view.width / 2)
//        setPositionOffsetYWhenBottom(-view.width/2)
        show(view)
    }


}