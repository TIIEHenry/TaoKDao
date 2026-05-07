package taokdao.main.business.window.window_explorer

import android.content.DialogInterface
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.SimpleCallback
import taokdao.api.ui.explorer.IExplorerWindow
import taokdao.api.ui.window.callback.WindowStateObserver
import taokdao.main.business.window.window_explorer.popup.ExplorerWindowPopupView


class ExplorerWindowViewWrapper(val view: ExplorerWindowContract.V) {

    private val lifecycleListenerList = mutableListOf<WindowStateObserver<IExplorerWindow>>()

    private val showListener = DialogInterface.OnShowListener {
        popupView.getCurrentExplorer()?.stateObserver?.onShow()
        lifecycleListenerList.toTypedArray().forEach {
            it.onWindowShow(view.explorerWindow)
        }
    }

    private val dismissListener = DialogInterface.OnDismissListener{
            popupView.getCurrentExplorer()?.stateObserver?.onHide()
            lifecycleListenerList.toTypedArray().forEach {
                it.onWindowHide(view.explorerWindow)
            }
    }

    val popupView = ExplorerWindowPopupView(view)

    private val drawerPopup: BasePopupView by lazy {
        XPopup.Builder(view.context)
//                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                //                        .asCustom(new CustomDrawerPopupView(getContext()))
                //                        .hasShadowBg(false)
                .setPopupCallback(object : SimpleCallback() {
                    override fun onShow() {
                        showListener.onShow(null);
                    }

                    override fun onDismiss() {
                        dismissListener.onDismiss(null);
                    }
                })
                .asCustom(popupView)
        //                        .asCustom(new ListDrawerPopupView(getContext()))
//        AnyLayer.dialog(view.context)
//                .contentView(popupView.layout)
//                .backgroundDimDefault()
//                .asStatusBar(R.id.v_explorer_statusbar)
//                .gravity(Gravity.LEFT)
//                .dragDismiss(DragLayout.DragStyle.Left)
//                .onShowListener(showListener)
//                .onDismissListener(dismissListener)
    }

    fun showWindow() {
        if (!isWindowShown()) {
            view.contentManager.current?.editor?.imeController?.hideSoftInput()
            drawerPopup.show()
            popupView.updateHeight()
        }
    }

    fun hideWindow() {
        if (isWindowShown()) {
            view.launchMain {
                drawerPopup.dismiss()
            }
        }
    }

    fun isWindowShown() = drawerPopup.isShow

    fun addStateObserver(observer: WindowStateObserver<IExplorerWindow>) {
        lifecycleListenerList.add(observer)
    }

    fun removeStateObserver(observer: WindowStateObserver<IExplorerWindow>): Boolean {
        return lifecycleListenerList.remove(observer)
    }

    fun initLayout() {
        popupView.initLayout()
    }


}