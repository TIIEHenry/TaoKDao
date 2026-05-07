package taokdao.main.business.window.window_explorer

import taokdao.api.ui.explorer.IExplorerWindow
import taokdao.main.IMainView


interface ExplorerWindowContract {

    interface V : IMainView {
        val explorerWindowPresenter: ExplorerWindowPresenter
    }

    interface P : IExplorerWindow {
        fun init()

    }

    interface M
}