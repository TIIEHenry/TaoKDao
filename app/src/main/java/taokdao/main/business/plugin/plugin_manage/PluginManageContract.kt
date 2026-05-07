package taokdao.main.business.plugin.plugin_manage

import taokdao.api.data.mmkv.IMMKV
import taokdao.api.data.mmkv.IMMKVManager
import taokdao.api.plugin.bean.PluginType
import taokdao.api.plugin.manage.IPluginManager
import taokdao.main.IMainView


interface PluginManageContract {
    interface V : IMainView {
        val pluginManagePresenter: PluginManagePresenter
    }

    interface VW {
        val pluginManagePresenter: PluginManagePresenter
        fun initListener()
        fun showPluginLauncherPopup()
        fun hidePluginLauncherPopup()
        fun addDefaultProjectPlugin()
        fun refreshPluginList()
        fun addInternalPluginEngine()
    }

    interface P : IPluginManager {
        fun init()
        fun reloadPluginList()
        fun onCreatePluginEngine()
        fun initInternalPluginEngine()
        fun onDestroyPluginEngine()
        fun loadPluginForType(type: PluginType)
    }

    interface M {
        fun initProjectPluginPool()
        fun initPluginEnginePool()
        fun getMMKV(mmkvManager: IMMKVManager): IMMKV
        fun isPluginEnabled(id: String, mmkvManager: IMMKVManager): Boolean
        fun enablePlugin(id: String, mmkvManager: IMMKVManager)
        fun disablePlugin(id: String, mmkvManager: IMMKVManager)

    }
}