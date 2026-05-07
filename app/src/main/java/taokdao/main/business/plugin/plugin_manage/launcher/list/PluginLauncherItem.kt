package taokdao.main.business.plugin.plugin_manage.launcher.list

import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginVisibility
import taokdao.main.IMainView

data class PluginLauncherItem(val information: Plugin.Information, val visibility: PluginVisibility, val plugin: Plugin? = null) {
    companion object {
        fun from(plugin: Plugin, main: IMainView): PluginLauncherItem {
            return PluginLauncherItem(plugin.getInformation(main), plugin.visibility, plugin)
        }
    }
}