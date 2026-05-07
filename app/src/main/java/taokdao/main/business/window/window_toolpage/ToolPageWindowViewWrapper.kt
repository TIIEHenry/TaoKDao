package taokdao.main.business.window.window_toolpage

import android.content.DialogInterface
import taokdao.api.ui.toolpage.IToolPageWindow
import taokdao.api.ui.window.callback.WindowStateObserver
import taokdao.main.business.window.window_toolpage.popup.ToolPageWindowPopupView

class ToolPageWindowViewWrapper(val view: ToolPageWindowContract.V) : ToolPageWindowContract.VW {
    private val lifecycleListenerList = mutableListOf<WindowStateObserver<IToolPageWindow>>()

    val popupView = ToolPageWindowPopupView(view, this)

    private val showListener = object : DialogInterface.OnShowListener {
//        override fun onShown(layer: Layer) {
//            popupView.getCurrentTabTool()?.stateObserver?.onShow()
//            lifecycleListenerList.toTypedArray().forEach {
//                it.onWindowShow(view.toolPageWindow)
//            }
//        }
//
//        override fun onShowing(layer: Layer) {
//            popupView.updateMax()
//        }

        override fun onShow(dialog: DialogInterface?) {
            popupView.getCurrentTabTool()?.stateObserver?.onShow()
            lifecycleListenerList.toTypedArray().forEach {
                it.onWindowShow(view.toolPageWindow)
            }
        }
    }

    private val dismissListener = object : DialogInterface.OnDismissListener {
        override fun onDismiss(dialog: DialogInterface?) {
            popupView.getCurrentTabTool()?.stateObserver?.onHide()
            lifecycleListenerList.toTypedArray().forEach {
                it.onWindowHide(view.toolPageWindow)
            }
        }
    }

    fun setPeekHeightDefault() {
        popupView.setPeekHeightDefault()

    }

    fun setPeekHeightMax() {
        popupView.setPeekHeightMax()
    }

    override fun showWindow() {
        if (!isWindowShown()) {
            view.launchMain {
                popupView.show(showListener)
            }
        }
    }

    override fun hideWindow() {
        if (isWindowShown()) {
            view.launchMain {
                popupView.hide(dismissListener)
            }
        }
    }

    override fun isWindowShown() = popupView.isShow()


    override fun addStateObserver(observer: WindowStateObserver<IToolPageWindow>) {
        lifecycleListenerList.add(observer)
    }

    override fun removeStateObserver(observer: WindowStateObserver<IToolPageWindow>): Boolean {
        return lifecycleListenerList.remove(observer)
    }

    fun initLayout() {
        popupView.initLayout()

    }
}