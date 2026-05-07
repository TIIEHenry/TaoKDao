package taokdao.main.business.window.window_toolpage

import taokdao.api.ui.toolpage.IToolPage
import taokdao.api.ui.toolpage.IToolPageWindow
import taokdao.api.ui.toolpage.internal.IInternalToolPageManager
import taokdao.api.ui.window.Windows
import taokdao.api.ui.window.callback.WindowStateObserver


class ToolPageWindowPresenter(val view: ToolPageWindowContract.V) : ToolPageWindowContract.P {
    internal val vw by lazy {
        ToolPageWindowViewWrapper(view)
    }
    private val model = ToolPageWindowModel()
    internal fun init() {
        vw.initLayout()
        Windows.TOOL_PAGES.window = this
    }

    private val internalToolGroupManager = InternalToolPageManager(this)
    override fun getInternalToolGroupManager(): IInternalToolPageManager {
        return internalToolGroupManager
    }

    override fun showWindow() {
        vw.showWindow()
    }

    override fun hideWindow() {
        vw.hideWindow()
    }

    override fun isWindowShown(): Boolean {
        return vw.isWindowShown()
    }

    override fun add(toolPage: IToolPage, select: Boolean) {
        vw.popupView.addTabTool(toolPage, select)
    }

    override fun remove(toolPage: IToolPage): Boolean {
        return vw.popupView.removeTabTool(toolPage)
    }

    override fun show(toolPage: IToolPage) {
        if (!isWindowShown)
            showWindow()
        vw.popupView.selectTabTool(toolPage)
    }

    override fun getAll(): MutableList<IToolPage> {
        return vw.popupView.getTabToolList()
    }

    override fun getCurrent(): IToolPage? {
        return vw.popupView.getCurrentTabTool()
    }

    override fun clear() {
        vw.popupView.clearTabTool()
    }

    override fun addStateObserver(observer: WindowStateObserver<IToolPageWindow>) {
        vw.addStateObserver(observer)
    }

    override fun removeStateObserver(observer: WindowStateObserver<IToolPageWindow>): Boolean {
        return vw.removeStateObserver(observer)
    }

    override fun refreshMenu() {
        vw.popupView.refreshMenu()
    }

}