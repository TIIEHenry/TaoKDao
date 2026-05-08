package taokdao.window.toolpages.builder

import android.widget.Spinner
import taokdao.api.main.IMainContext
import taokdao.api.ui.toolpage.IToolPage
import taokdao.api.ui.toolpage.container.IToolTabContainer
import taokdao.api.ui.toolpage.container.tabchoose.ITabChooserAdapter
import taokdao.api.ui.toolpage.group.tooltab.IToolTab

class TabChooserAdapter(private val main: IMainContext) : ITabChooserAdapter {
    private var toolGroup: IToolPage? = null
    private var spinner: Spinner? = null

    fun attachToolGroup(toolGroup: IToolPage) {
        this.toolGroup = toolGroup
    }

    fun attachSpinner(spinner: Spinner) {
        this.spinner = spinner
    }

    @Suppress("UNCHECKED_CAST")
    private val container: IToolTabContainer<Any>?
        get() = toolGroup as? IToolTabContainer<Any>

    override fun add(toolTab: IToolTab<*>, select: Boolean) {
        container?.add(toolTab as IToolTab<Any>, select)
    }

    override fun remove(toolTab: IToolTab<*>) {
        container?.remove(toolTab as IToolTab<Any>)
    }

    override fun contains(toolTab: IToolTab<*>): Boolean {
        return container?.getAll()?.contains(toolTab) ?: false
    }

    override fun clear() {
        container?.clear()
    }

    override fun show(toolTab: IToolTab<*>?) {
        toolTab?.let { container?.show(it as IToolTab<Any>) }
    }

    override fun getAll(): List<IToolTab<*>> {
        @Suppress("UNCHECKED_CAST")
        return (container?.getAll() ?: emptyList()) as List<IToolTab<*>>
    }
}
