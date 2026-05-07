package taokdao.main.business.theme_manage

import android.content.res.Configuration
import taokdao.api.setting.theme.IThemeManager
import taokdao.api.setting.theme.ThemeParts
import taokdao.api.setting.theme.resource.ThemeColors
import taokdao.api.setting.theme.resource.ThemeDrawables
import taokdao.main.IMainView


interface ThemeManageContract {
    interface V : IMainView {
        fun showChangeThemeDialogToDark()
        fun showChangeThemeDialogToLight()

        val themeManagePresenter: ThemeManagePresenter
        fun applyThemeColors()
        fun getThemeColors(themeParts: ThemeParts): ThemeColors
        fun getThemeDrawables(themeParts: ThemeParts): ThemeDrawables
    }

    interface P : IThemeManager {
        fun observeUiMode(newConfig: Configuration)

    }

    interface M {
        /**
         *
         * -1为未改变
         * @return Configuration.uiMode
         */
        fun observeUiMode(newConfig: Configuration, isDarkNow: Boolean): Int
    }
}