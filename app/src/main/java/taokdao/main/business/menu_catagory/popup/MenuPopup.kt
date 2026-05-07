package taokdao.main.business.menu_catagory.popup

import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation
import taokdao.api.main.action.MainAction
import taokdao.main.IMainView
import tiiehenry.ktx.app.getStatusBarHeight
import tiiehenry.ktx.res.dp2px
import tiiehenry.taokdao.ui.view.anim.XpopupAnims

class MenuPopup(val main: IMainView) {
    init {
        MainAction.onOrientationChanged.addObserver {
            updatePosition()
        }
    }

    private val ani = XpopupAnims.ScaleAlphaAnimator(PopupAnimation.ScaleAlphaFromRightTop)

    private var menuPopupBuilder = XPopup.Builder(main.context)
            .offsetX(main.width - main.context.dp2px(180f))
            .offsetY(main.activity.getStatusBarHeight())
            .customAnimator(ani)

    private fun getMenuPopup(): BasePopupView {
        return menuPopupBuilder.asCustom(MenuPopupView(main, this)).apply {
            ani.targetView = this
        }
    }

    private var menuPopup = getMenuPopup()


    private fun updatePosition() {
        hide()
        menuPopupBuilder
                .offsetX(main.width - main.context.dp2px(180f))
                .offsetY(main.activity.getStatusBarHeight())
        menuPopup = getMenuPopup()
    }


    fun show() {
        menuPopup.show()
    }

    fun hide() {
        menuPopup.dismiss()
    }

}