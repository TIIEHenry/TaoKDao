package taokdao.main.business.theme_manage

import android.content.res.Configuration
import taokdao.api.setting.theme.ThemeMode
import taokdao.api.setting.theme.ThemeParts
import taokdao.api.setting.theme.resource.ThemeColors
import taokdao.api.setting.theme.resource.ThemeDrawables
import taokdao.main.business.setting_main.bean.DarkTheme
import taokdao.main.business.setting_main.bean.LightTheme


class ThemeManagePresenter(internal val view: ThemeManageContract.V) : ThemeManageContract.P {
    private val model = ThemeManageModel()

    override fun observeUiMode(newConfig: Configuration) {
        if (!ThemeMode.current.isSystem) {
            return
        }
        val isDarkNow = view.themeManager.currentUIMode.isDark
        when (model.observeUiMode(newConfig, isDarkNow)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                view.showChangeThemeDialogToDark()
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                view.showChangeThemeDialogToLight()
            }
            -1 -> {
            }
        }
    }

    override fun isSystemDark(): Boolean {
        val mode = view.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return mode == Configuration.UI_MODE_NIGHT_YES
    }

    private var uiMode = ThemeMode.SYSTEM
    override fun setCurrentUIMode(mode: ThemeMode) {
        uiMode = mode
    }

    override fun getCurrentUIMode(): ThemeMode {
        return uiMode
    }

    override fun shouldDark(): Boolean {
        return ThemeMode.current == ThemeMode.DARK ||
                ThemeMode.current == ThemeMode.SYSTEM && isSystemDark
    }

    override fun getThemeId(dark: Boolean): Int {
        if (dark)
            return DarkTheme.current.id
        return LightTheme.current.id
    }

    override fun getThemeColors(themeParts: ThemeParts): ThemeColors {
        return view.getThemeColors(themeParts)
    }

    override fun getThemeDrawables(themeParts: ThemeParts): ThemeDrawables {
        return view.getThemeDrawables(themeParts)
    }
}