package taokdao.main.business.plugin.plugin_manage

import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import tiiehenry.ideditor.R
import taokdao.api.main.action.MainAction
import taokdao.api.plugin.engine.PluginEnginePool
import taokdao.api.project.plugin.ProjectPluginPool
import taokdao.main.business.plugin.plugin_manage.launcher.PluginLauncherLayout
import taokdao.plugin.engines.apk.APKPluginEngine
import taokdao.window.explorers.projectfiles.ProjectFilesPlugin
import taokdao.window.explorers.projectstructure.ProjectStructurePlugin
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.databinding.ActivityMainBinding


class PluginManageViewWrapper(val view: PluginManageContract.V, override val pluginManagePresenter: PluginManagePresenter) : PluginManageContract.VW {
    private val pluginLauncherLayout: PluginLauncherLayout by lazy {
        PluginLauncherLayout(view, this).apply {
            initLayout()
        }
    }

    init {
        MainAction.onPluginListReloaded.addObserver {
            refreshPluginList()
        }
    }

    private val pluginLauncherPopup: BasePopupView by lazy {
        XPopup.Builder(view.context)
                .atView(ActivityMainBinding.bind(view.contentView).mainToolbarCl)
                .hasShadowBg(true)
//                .popupPosition(PopupPosition.Bottom)
                .dismissOnTouchOutside(true)
                .dismissOnBackPressed(true)
                .isRequestFocus(true)
                .asCustom(pluginLauncherLayout)
    }

    override fun initListener() {
//        Log.e("PluginLoadException", "PluginLoadException: ${view.languageManager.languageCountry}")
        view.indicatorManager.endIndicator.setOnClickListener {
            showPluginLauncherPopup()
        }
    }

    override fun showPluginLauncherPopup() {
//        pluginLauncherLayout.layout.widthAndHeight(-1, (view.height - view.activity.main_viewpager.y).toInt())
        pluginLauncherPopup.show()
    }

    override fun hidePluginLauncherPopup() {
        pluginLauncherPopup.dismiss()
    }

    override fun addDefaultProjectPlugin() {
        ProjectPluginPool.getInstance().let {
            it.add(ProjectStructurePlugin(view))
            it.add(ProjectFilesPlugin(view))
        }
    }

    override fun refreshPluginList() {
        view.launchMain {
            pluginLauncherLayout.refreshAll()
        }
    }

    override fun addInternalPluginEngine() {
        val dexPlugin = APKPluginEngine(view)
        PluginEnginePool.getInstance().let {
            it.add(dexPlugin)
        }
    }


    fun startReloadPluginTask() {
        Dialogs.global
                .asLoading()
//                .title(R.string.plugin_manage_launcher_refresh_dialog_title)
//                .titleGravity(GravityEnum.CENTER)
                .addLoadingTask(view.getString(R.string.plugin_manage_launcher_refresh_dialog_task)) {
                    pluginManagePresenter.reloadPluginList()
                }
                .cancelable(false)
                .minDisplayTime(1000)
                .show()
    }
}