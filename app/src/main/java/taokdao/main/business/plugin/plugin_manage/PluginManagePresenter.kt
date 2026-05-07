package taokdao.main.business.plugin.plugin_manage

import taokdao.api.event.senders.PluginEngineSender
import taokdao.api.event.senders.PluginLoaderSender
import taokdao.api.main.action.MainAction
import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginType
import taokdao.api.plugin.engine.PluginEnginePool


class PluginManagePresenter(private val view: PluginManageContract.V) : PluginManageContract.P {
    private val model = PluginManageModel()
    private val sender = PluginLoaderSender()

    internal val vw: PluginManageContract.VW by lazy {
        PluginManageViewWrapper(view, this)
    }

    override fun init() {
        model.initProjectPluginPool()
        vw.addDefaultProjectPlugin()
        vw.initListener()
    }

    override fun showPluginLauncher() {
        vw.showPluginLauncherPopup()
    }

    override fun reloadPluginList() {
        callPluginOnDestroy(PluginType.TYPE_COMMON)
        reloadPluginList(PluginType.TYPE_COMMON)
        callPluginOnCreate(PluginType.TYPE_COMMON)
        callPluginOnInit(PluginType.TYPE_COMMON)
        vw.refreshPluginList()
    }

    override fun initInternalPluginEngine() {
        model.initPluginEnginePool()
        vw.addInternalPluginEngine()
    }

    override fun onCreatePluginEngine() {
        view.launchMain {
            for (pluginEngine in PluginEnginePool.getInstance().all) {
                try {
                    pluginEngine.onCreateEngine()
                } catch (e: Throwable) {
                    e.printStackTrace()
                    PluginEngineSender(pluginEngine).engineError(e.message).send(view)
                }
            }
        }
    }

    override fun onDestroyPluginEngine() {
        view.launchMain {
            for (pluginEngine in PluginEnginePool.getInstance().all) {
                try {
                    pluginEngine.onDestroyEngine()
                } catch (e: Throwable) {
                    e.printStackTrace()
                    PluginEngineSender(pluginEngine).engineError(e.message).send(view)
                }
            }
        }
    }


    override fun loadPluginForType(type: PluginType) {
        loadPluginList(type)
        callPluginOnCreate(type)
    }

//
//    /**
//     * 获取plugin支持的content列表，方便一键运行
//     *
//     * @param id content id
//     * @return list
//     */
//    fun getPluginListForContent(id: String): List<Plugin> {
//        return pluginList.filter {
//            it.contents.size == 0 || it.contents.contains(id)
//        }
//    }

    override fun reloadPluginManifestList() {
        model.pluginManifestList = mutableListOf()
        val files = view.fileSystem.pluginDir.listFiles() ?: return
        for (it in files) {
            if (it.isDirectory.not())
                continue
            try {
                val manifest = view.pluginLoader.loadPluginManifestFromDir(it)
                model.pluginManifestList.add(manifest)
            } catch (e: Exception) {
                e.printStackTrace()
                sender.loadManifestError(e.message).log(view)
            }
        }
    }

    override fun getPluginList(): MutableList<Plugin> = model.pluginList

    override fun loadPluginList(type: PluginType) {
        val list = mutableListOf<Plugin>()
        for (manifest in model.pluginManifestList) {
            if (manifest.pluginType == type) {
                try {
                    val plugin = view.pluginLoader.loadPlugin(manifest)
                    list.add(plugin)
                } catch (e: Exception) {
                    e.printStackTrace()
                    sender.loadPluginError(e.message).log(view)
                }
            }
        }
        pluginList.addAll(list)
    }

    override fun unloadPluginList(type: PluginType) {
        val oldList = getPluginList(type)
        pluginList.removeAll(oldList)
    }

    override fun reloadPluginList(type: PluginType) {
        unloadPluginList(type)
        reloadPluginManifestList()
        loadPluginList(type)
        MainAction.onPluginListReloaded.runObservers(view)
    }


    override fun callPluginOnCreate(type: PluginType) {
        val list = getPluginList(type)
        for (plugin in list) {
            plugin.onCreate()
        }
    }

    override fun callPluginOnInit(type: PluginType) {
        val list = getPluginList(type)
        for (plugin in list) {
            plugin.onInit()
        }
    }

    override fun callPluginOnDestroy(type: PluginType) {
        val list = getPluginList(type)
        for (plugin in list) {
            plugin.onDestroy()
        }
    }

    override fun callPluginOnPause(type: PluginType) {
        val list = getPluginList(type)
        for (plugin in list) {
            plugin.onPause()
        }
    }

    override fun callPluginOnResume(type: PluginType) {
        val list = getPluginList(type)
        for (plugin in list) {
            plugin.onResume()
        }
    }


    override fun disablePlugin(id: String) {
        model.disablePlugin(id, view.mmkvManager)
    }

    override fun enablePlugin(id: String) {
        model.enablePlugin(id, view.mmkvManager)
    }

    override fun isPluginEnabled(id: String): Boolean {
        return model.isPluginEnabled(id, view.mmkvManager)
    }


}