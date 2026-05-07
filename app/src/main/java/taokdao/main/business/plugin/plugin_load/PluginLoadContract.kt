package taokdao.main.business.plugin.plugin_load

import android.graphics.drawable.Drawable
import taokdao.api.plugin.load.IPluginLoader
import taokdao.main.IMainView


interface PluginLoadContract {
    interface V : IMainView {
        val pluginLoadPresenter: PluginLoadPresenter

    }

    interface VW {
        val presenter: PluginLoadPresenter
        fun getDefaultPluginIcon(): Drawable

    }

    interface P : IPluginLoader

    interface M

}