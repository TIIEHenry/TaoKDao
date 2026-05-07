package tiiehenry.taokdao.ui.setting

import android.view.View
import taokdao.api.main.IMainContext
import taokdao.api.setting.preference.base.IPreference
import tiiehenry.ktx.content.getWidth
import tiiehenry.ideditor.R
import tiiehenry.ktx.res.dp2px
import tiiehenry.taokdao.ui.view.UIRecyclerPopup

class RightBottomSettingPopup(val main: IMainContext, val dataList: MutableList<IPreference<*>>, val view: View)
    : UIRecyclerPopup<IPreference<*>>(main.activity,
        PreferenceAdapter(main, dataList) { list ->
            RightBottomSettingPopup(main, list, view).showAt()
        }, DIRECTION_TOP, R.style.Animation_Popup_RightBottom
) {

    init {

        setBoxBackground(R.drawable.ui_drawable_popup_background_rightbottom)


        var h = context.getWidth() * 9 / 16
        val w = context.dp2px(300f - 50)

        val itemH = main.getDimen(R.dimen.setting_preference_item_H)

        val actualH = dataList.size * itemH
        h = if (actualH < h) {
            actualH
        } else {
            (h / itemH) * itemH
        }

        create(w, h)
    }


    fun showAt() {
        setPositionOffsetX(-view.width / 2)
        setPositionOffsetYWhenBottom(-view.width / 2)
        show(view)
    }


}