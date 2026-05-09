package taokdao.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.qw.soul.permission.bean.Permission
import taokdao.api.plugin.bean.PluginType
import taokdao.api.setting.preference.base.IPreference
import taokdao.content.guider.GuiderContent
import taokdao.main.business.action_process.ActionProcessPresenter
import taokdao.main.business.action_process.ActionProcessView
import taokdao.main.business.build_manage.BuildManagePresenter
import taokdao.main.business.build_manage.BuildManageView
import taokdao.main.business.content_manage.ContentManagePresenter
import taokdao.main.business.content_manage.ContentManageView
import taokdao.main.business.dex_load.DexLoadPresenter
import taokdao.main.business.dex_load.DexLoadView
import taokdao.main.business.dialog_manage.DialogManagerPresenter
import taokdao.main.business.dialog_manage.DialogManagerView
import taokdao.main.business.drawable_manage.DrawableManagePresenter
import taokdao.main.business.drawable_manage.DrawableManageView
import taokdao.main.business.exit_control.ExitControlPresenter
import taokdao.main.business.exit_control.ExitControlView
import taokdao.main.business.file.file_open.FileOpenPresenter
import taokdao.main.business.file.file_open.FileOpenView
import taokdao.main.business.file.file_operate.FileOperatePresenter
import taokdao.main.business.file.file_operate.FileOperateView
import taokdao.main.business.file.file_provider.FileProviderPresenter
import taokdao.main.business.file.file_provider.FileProviderView
import taokdao.main.business.indicate_manage.IndicateManagePresenter
import taokdao.main.business.indicate_manage.IndicateManageView
import taokdao.main.business.language_manage.LanguageManagePresenter
import taokdao.main.business.language_manage.LanguageManageView
import taokdao.main.business.layout_toolbar.ToolBarLayoutPresenter
import taokdao.main.business.layout_toolbar.ToolBarLayoutView
import taokdao.main.business.menu_catagory.CategoryMenuPresenter
import taokdao.main.business.menu_catagory.CategoryMenuView
import taokdao.main.business.menu_catagory.popup.MenuPopup
import taokdao.main.business.mmkv_manage.MMKVManagePresenter
import taokdao.main.business.mmkv_manage.MMKVManageView
import taokdao.main.business.permission_request.PermissionRequestPresenter
import taokdao.main.business.permission_request.PermissionRequestView
import taokdao.main.business.plugin.plugin_install.PluginInstallPresenter
import taokdao.main.business.plugin.plugin_install.PluginInstallView
import taokdao.main.business.plugin.plugin_load.PluginLoadPresenter
import taokdao.main.business.plugin.plugin_load.PluginLoadView
import taokdao.main.business.plugin.plugin_manage.PluginManagePresenter
import taokdao.main.business.plugin.plugin_manage.PluginManageView
import taokdao.main.business.progressbar_use.ProgressUsePresenter
import taokdao.main.business.progressbar_use.ProgressUseView
import taokdao.main.business.project.project_build.ProjectBuildPresenter
import taokdao.main.business.project.project_build.ProjectBuildView
import taokdao.main.business.project.project_manage.ProjectManagePresenter
import taokdao.main.business.project.project_manage.ProjectManageView
import taokdao.main.business.screen_control.ScreenControlPresenter
import taokdao.main.business.screen_control.ScreenControlView
import taokdao.main.business.session_control.SessionControlPresenter
import taokdao.main.business.session_control.SessionControlView
import taokdao.main.business.setting_main.MainSettingPresenter
import taokdao.main.business.setting_main.MainSettingView
import taokdao.main.business.template.template_file.FileTemplateGeneratePresenter
import taokdao.main.business.template.template_file.FileTemplateGenerateView
import taokdao.main.business.template.template_project.ProjectTemplatePresenter
import taokdao.main.business.template.template_project.ProjectTemplateView
import taokdao.main.business.theme_manage.ThemeManagePresenter
import taokdao.main.business.theme_manage.ThemeManageView
import taokdao.main.business.window.window_explorer.ExplorerDisplayView
import taokdao.main.business.window.window_explorer.ExplorerWindowPresenter
import taokdao.main.business.window.window_toolpage.ToolPageDisplayView
import taokdao.main.business.window.window_toolpage.ToolPageWindowPresenter
import taokdao.main.handler.MainHandler
import taokdao.window.toolpages.TabToolInternal
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.databinding.ActivityMainBinding

class MainActivity : BaseMainActivity(), FileOpenView, FileOperateView, CategoryMenuView,
    ProgressUseView, SessionControlView, PermissionRequestView, ProjectBuildView,
    PluginManageView, ProjectManageView, ScreenControlView, ThemeManageView, ExitControlView,
    ActionProcessView, MainSettingView, PluginInstallView, FileTemplateGenerateView,
    DrawableManageView, ProjectTemplateView, LanguageManageView, MMKVManageView, DexLoadView,
    BuildManageView, PluginLoadView, ContentManageView, ExplorerDisplayView,
    ToolPageDisplayView, ToolBarLayoutView, DialogManagerView, FileProviderView,
    IndicateManageView {
    override val main: MainActivity
        get() = this
    override val menuPopup by lazy { MenuPopup(this) }
    override val settingList = mutableListOf<IPreference<*>>()


    override val projectTemplatePresenter = ProjectTemplatePresenter(this)
    override val drawableManagePresenter = DrawableManagePresenter(this)
    override val fileTemplateGeneratePresenter = FileTemplateGeneratePresenter(this)
    override val projectManagePresenter = ProjectManagePresenter(this)
    override val pluginInstallPresenter = PluginInstallPresenter(this)
    override val mainSettingPresenter = MainSettingPresenter(this)
    override val actionProcessPresenter = ActionProcessPresenter(this)
    override val exitControlPresenter = ExitControlPresenter(this)
    override val themeManagePresenter = ThemeManagePresenter(this)
    override val screenControlPresenter = ScreenControlPresenter(this)
    override val pluginManagePresenter = PluginManagePresenter(this)
    override val projectBuildPresenter = ProjectBuildPresenter(this)
    override val permissionRequestPresenter = PermissionRequestPresenter(this)
    override val progressUsePresenter = ProgressUsePresenter(this)
    override val sessionControlPresenter = SessionControlPresenter(this)
    override val categoryMenuPresenter = CategoryMenuPresenter(this)
    override val fileOpenPresenter = FileOpenPresenter(this)
    override val fileOperatePresenter = FileOperatePresenter(this)
    override val languageManagePresenter = LanguageManagePresenter(this)
    override val mmkvManagePresenter = MMKVManagePresenter(this)
    override val dexLoadPresenter = DexLoadPresenter(this)
    override val buildManagePresenter = BuildManagePresenter(this)
    override val pluginLoadPresenter = PluginLoadPresenter(this)
    override val contentManagePresenter = ContentManagePresenter(this)
    override val explorerWindowPresenter = ExplorerWindowPresenter(this)
    override val tabToolWindowPresenter = ToolPageWindowPresenter(this)
    override val toolBarLayoutPresenter = ToolBarLayoutPresenter(this)
    override val dialogManagerPresenter = DialogManagerPresenter(this)
    override val fileProviderPresenter = FileProviderPresenter(this)
    override val indicateManagePresenter = IndicateManagePresenter(this)

    override fun getMMKVManager() = mmkvManagePresenter
    override fun getThemeManager() = themeManagePresenter
    override fun getLanguageManager() = languageManagePresenter
    override fun getProjectManager() = projectManagePresenter
    override fun getDrawableManager() = drawableManagePresenter
    override fun getFileTemplateGenerator() = fileTemplateGeneratePresenter
    override fun getProjectTemplateGenerator() = projectTemplatePresenter
    override fun getPluginInstaller() = pluginInstallPresenter
    override fun getFileOperateManager() = fileOperatePresenter
    override fun getFileOpenManager() = fileOpenPresenter
    override fun getDexLoader() = dexLoadPresenter
    override fun getBuildManager() = buildManagePresenter
    override fun getPluginLoader() = pluginLoadPresenter
    override fun getPluginManager() = pluginManagePresenter
    override fun getContentManager() = contentManagePresenter
    override fun getExplorerWindow() = explorerWindowPresenter
    override fun getToolPageWindow() = tabToolWindowPresenter
    override fun getDialogs(): Dialogs = Dialogs.global
    override fun getFileProvider() = fileProviderPresenter
    override fun getIndicatorManager() = indicateManagePresenter
    override fun getActivity(): MainActivity = this

    override val mainHandler = MainHandler(this)


    override val tabToolInternal = TabToolInternal(this)

    lateinit var binding: ActivityMainBinding
    override fun attachBaseContext(newBase: Context) {
        val createContext = mainSettingPresenter.attachBaseContext(newBase)
        super.attachBaseContext(createContext)
    }

    override fun getContentView(): ViewGroup {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(themeManager.themeId)
        themeManager.saveCurrentUIMode()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionProcessPresenter.initMainAction()
        tabToolInternal.init()

//
//        val typedValue = TypedValue();
//        getTheme().resolveAttribute(R.attr.md_button_height, typedValue, true);
//        val id = typedValue.data
//        loge(id.toString())
//        loge(typedValue.resourceId.toString())

//        loge(getDimen(id).toString())
    }


    override fun onNecessaryPermissionOK(allPermissions: Array<out Permission>?) {
        onPrepareStartupStage()
    }

    fun onPrepareStartupStage() {
        themeManagePresenter.view.applyThemeColors()

        toolBarLayoutPresenter.init()

        contentManagePresenter.init()
        GuiderContent(this).init()
        explorerWindowPresenter.init()
        tabToolWindowPresenter.init()
        dialogManagerPresenter.init()

        permissionRequestPresenter.checkNecessaryPermission()

        initListener()


        progressUsePresenter.init()
        categoryMenuPresenter.init()
        fileOpenPresenter.init()
        fileOperatePresenter.init()

        pluginManagePresenter.init()
        projectBuildPresenter.init()

        mainSettingPresenter.loadMainSettingList()

        pluginManagePresenter.reloadPluginManifestList()
        pluginManagePresenter.initInternalPluginEngine()
        pluginManagePresenter.loadPluginForType(PluginType.TYPE_ENGINE)
        pluginManagePresenter.onCreatePluginEngine()
        pluginManagePresenter.loadPluginForType(PluginType.TYPE_COMMON)
        pluginManagePresenter.vw.refreshPluginList()
        drawableManagePresenter.init()
        fileTemplateGenerator.init()
        projectTemplatePresenter.init()
        buildManagePresenter.init()

        sessionControlPresenter.restoreSession()


        pluginManagePresenter.callPluginOnInit(PluginType.TYPE_ENGINE)
        pluginManagePresenter.callPluginOnInit(PluginType.TYPE_COMMON)

        handleIntent(intent)
    }


    override fun onPause() {
        sessionControlPresenter.observeOnPause()
        super.onPause()
    }


    override fun onStop() {
        sessionControlPresenter.saveSession()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        pluginManagePresenter.onDestroyPluginEngine()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exitControlPresenter.observeOnBackPressed()
//        super.onBackPressed()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.let {
            pluginInstallPresenter.handleIntent(it)
            fileOpenPresenter.handleIntent(it)
        }
    }


    private fun initListener() = launchMain {
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        screenControlPresenter.observeOrientation(newConfig)
        themeManager.observeUiMode(newConfig)
        super.onConfigurationChanged(newConfig)
    }

}


