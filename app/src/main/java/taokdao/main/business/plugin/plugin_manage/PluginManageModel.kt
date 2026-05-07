package taokdao.main.business.plugin.plugin_manage

import taokdao.api.data.mmkv.IMMKV
import taokdao.api.data.mmkv.IMMKVManager
import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginManifest
import taokdao.api.plugin.engine.PluginEnginePool
import taokdao.api.project.plugin.ProjectPluginPool

class PluginManageModel : PluginManageContract.M {
    override fun initProjectPluginPool() {
        ProjectPluginPool.newInstance()
    }

    override fun initPluginEnginePool() {
        PluginEnginePool.newInstance()

    }

    override fun getMMKV(mmkvManager: IMMKVManager): IMMKV {
        return mmkvManager.getMMKV("plugin_manager")
    }

    override fun enablePlugin(id: String, mmkvManager: IMMKVManager) {
        val mmkv = getMMKV(mmkvManager)
        val disabled = mmkv.decodeStringSet(disabledPluginSetKey, HashSet()).toMutableSet()
        disabled.remove(id)
        mmkv.encode(disabledPluginSetKey, disabled)
    }

    override fun disablePlugin(id: String, mmkvManager: IMMKVManager) {
        val mmkv = getMMKV(mmkvManager)
        val disabled = mmkv.decodeStringSet(disabledPluginSetKey, HashSet()).toMutableSet()
        disabled.add(id)
        mmkv.encode(disabledPluginSetKey, disabled)
    }

    override fun isPluginEnabled(id: String, mmkvManager: IMMKVManager): Boolean {
        val mmkv = getMMKV(mmkvManager)
        val disabled = mmkv.decodeStringSet(disabledPluginSetKey, HashSet()).toMutableSet()
        return !disabled.contains(id)
    }

    var pluginList = mutableListOf<Plugin>()
    var pluginManifestList = mutableListOf<PluginManifest>()


    companion object {
        const val disabledPluginSetKey = "disabledPlugins"
    }
}
