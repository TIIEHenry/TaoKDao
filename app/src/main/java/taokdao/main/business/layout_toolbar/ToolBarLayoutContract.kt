package taokdao.main.business.layout_toolbar

import taokdao.api.ui.content.menu.QuickMenu
import taokdao.main.IMainView
import taokdao.main.toolbar.ToolbarMenuAdapter


interface ToolBarLayoutContract {
    interface V : IMainView {
        fun initQuickMenu(quickMenuAdapter: QuickMenuAdapter)
        fun initToolbarMenu(): ToolbarMenuAdapter
        fun addDefaultMenuList(adapter: ToolbarMenuAdapter)

        val toolBarLayoutPresenter: ToolBarLayoutPresenter

    }

    interface P {
        fun init()
        fun refreshQuickMenu(menus: List<QuickMenu>)
        fun refreshQuickMenu()
    }

    interface M
}