package taokdao.main.business.layout_toolbar

import taokdao.api.ui.content.menu.QuickMenu


class ToolBarLayoutPresenter(internal val view: ToolBarLayoutContract.V) : ToolBarLayoutContract.P {
    private val quickMenuAdapter = QuickMenuAdapter()
    private val model = ToolBarLayoutModel()
    override fun init() {
        val toolbarMenuAdapter = view.initToolbarMenu()
        view.addDefaultMenuList(toolbarMenuAdapter)
        view.initQuickMenu(quickMenuAdapter)
    }

    override fun refreshQuickMenu(menus: List<QuickMenu>) {
        quickMenuAdapter.refresh(menus)
    }

    override fun refreshQuickMenu() {
        quickMenuAdapter.notifyDataSetChanged()
    }
}