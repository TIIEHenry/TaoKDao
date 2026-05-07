package taokdao.main.business.window.window_toolpage

import taokdao.api.ui.toolpage.IToolPage
import taokdao.api.ui.toolpage.IToolPageWindow
import taokdao.api.ui.window.callback.WindowStateObserver

interface IToolPageWindowViewWrapper {
    fun showWindow()
    fun hideWindow()
    fun isWindowShown(): Boolean
    fun addStateObserver(observer: WindowStateObserver<IToolPageWindow>)
    fun removeStateObserver(observer: WindowStateObserver<IToolPageWindow>): Boolean
    fun add(iToolPage: IToolPage, select: Boolean)
    fun remove(toolPage: IToolPage)
    fun show(toolPage: IToolPage)
    fun get(id: String): IToolPage?
    fun getList(): MutableList<IToolPage>
    fun getCurrent(): IToolPage?
    fun refreshMenu()
}