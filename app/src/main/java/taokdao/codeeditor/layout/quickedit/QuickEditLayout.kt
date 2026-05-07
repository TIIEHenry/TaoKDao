package taokdao.codeeditor.layout.quickedit

import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import com.lxj.xpopup.enums.PopupPosition
import taokdao.api.main.IMainContext
import taokdao.codeeditor.CodeIEditor
import tiiehenry.ktx.gone
import tiiehenry.ktx.visible
import tiiehenry.taokdao.ui.view.anim.XpopupAnims

class QuickEditLayout(val main: IMainContext, val editor: CodeIEditor, private val layout: View) {
    fun init(): QuickEditLayout {
        layout.setOnClickListener {
            showEditPopup()
        }
        return this
    }

    fun show() {
        layout.visible()
    }

    fun hide() {
        layout.gone()
    }

    fun showEditPopup() {
        val labelList = arrayListOf<String>()
        val iconList = arrayListOf<Int>()

        val list = QuickEditMenuSet.list
        for (quickEditMenu in list) {
            labelList.add(quickEditMenu.label)
//            iconList.add(it.icon)
        }
        val ani = XpopupAnims.ScaleAlphaAnimator(PopupAnimation.ScaleAlphaFromLeftBottom)
        XPopup.Builder(layout.context)
                .hasShadowBg(false)
                .customAnimator(ani)
                .popupAnimation(PopupAnimation.ScaleAlphaFromLeftBottom)
                .offsetX(layout.measuredWidth)
                .offsetY(-layout.measuredHeight)
                .popupPosition(PopupPosition.Top)
                .atView(layout)
                .isRequestFocus(false)
                .asAttachList(labelList.toTypedArray(), iconList.toIntArray()) { pos, _ ->
                    if (!editor.isEditable)
                        return@asAttachList
                    list[pos]?.callback?.onMenuAction(main, editor)
                }.apply {
                    ani.targetView = this
                }.show()
    }

}