package taokdao.main.business.window.window_toolpage

import taokdao.api.ui.toolpage.IToolPageWindow
import taokdao.api.ui.window.callback.WindowStateObserver
import taokdao.main.IMainView


interface ToolPageWindowContract {
    interface V : IMainView {
        val tabToolWindowPresenter: ToolPageWindowPresenter

    }

    interface VW {
        fun showWindow()
        fun hideWindow()
        fun isWindowShown(): Boolean
        fun addStateObserver(observer: WindowStateObserver<IToolPageWindow>)
        fun removeStateObserver(observer: WindowStateObserver<IToolPageWindow>): Boolean
    }

    interface P : IToolPageWindow

    interface M
}