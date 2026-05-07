package taokdao.main.business.window.window_explorer

import taokdao.api.ui.explorer.IExplorer
import taokdao.api.ui.explorer.IExplorerWindow
import taokdao.api.ui.window.Windows
import taokdao.api.ui.window.callback.WindowStateObserver
import taokdao.window.explorers.fileexplorer.FileExplorerFragment


class ExplorerWindowPresenter(view: ExplorerWindowContract.V) : ExplorerWindowContract.P {
    internal val vw: ExplorerWindowViewWrapper by lazy {
        ExplorerWindowViewWrapper(view)
    }
    val fileExplorer by lazy {
        FileExplorerFragment(view)
    }

    private val model = ExplorerWindowModel()
    override fun init() {
        vw.initLayout()
        Windows.EXPLORER.window = this
        addInternalExplorer()
    }

    private fun addInternalExplorer() {
        add(fileExplorer, true)
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

    override fun add(explorer: IExplorer, select: Boolean) {
        vw.popupView.addExplorer(explorer, select)
    }

    override fun remove(explorer: IExplorer): Boolean {
        return vw.popupView.removeExplorer(explorer)
    }

    override fun getAll(): MutableList<IExplorer> {
        return vw.popupView.getExplorerList()
    }

    override fun clear() {
        vw.popupView.clearExplorer()
    }

    override fun show(explorer: IExplorer) {
        if (!isWindowShown)
            showWindow()
        vw.popupView.selectExplorer(explorer)
    }

    override fun getCurrent(): IExplorer? {
        return vw.popupView.getCurrentExplorer()
    }

    override fun addStateObserver(observer: WindowStateObserver<IExplorerWindow>) {
        vw.addStateObserver(observer)

    }

    override fun removeStateObserver(observer: WindowStateObserver<IExplorerWindow>): Boolean {
        return vw.removeStateObserver(observer)
    }
}