package taokdao.main.business.setting_main.page

import android.content.Context
import android.content.Intent
import taokdao.api.data.bean.Properties
import taokdao.api.data.mmkv.IMMKV
import taokdao.api.main.menu.MainMenuCategory
import taokdao.api.setting.language.ILanguageManager
import taokdao.api.setting.preference.base.IPreference
import taokdao.api.setting.preference.wrapped.CategoryPreference
import taokdao.api.setting.preference.wrapped.SingleChoicePreference
import taokdao.api.setting.preference.wrapped.TitlePreference
import taokdao.api.setting.theme.ThemeMode
import taokdao.main.MainActivity
import taokdao.main.business.setting_main.bean.DarkTheme
import taokdao.main.business.setting_main.bean.DisplayLanguage
import taokdao.main.business.setting_main.bean.LightTheme
import tiiehenry.ideditor.R
import java.util.Locale


class PageDisplay(val mmkv: IMMKV, var context: Context) {

    class GroupLanguage(context: Context) :
        TitlePreference(Properties("language", context, R.string.main_setting_language)) {
        val displayLanguage =
            Properties("displayLanguage", context, R.string.main_setting_displaylanguage)
    }

    class GroupTheme(main: Context) :
        TitlePreference(Properties("theme", main, R.string.main_setting_theme)) {
        val darkMode = Properties("darkMode", main, R.string.main_setting_darkmode)
        val lightTheme = Properties("lightTheme", main, R.string.main_setting_lighttheme)
        val darkTheme = Properties("darkTheme", main, R.string.main_setting_darktheme)
    }

    fun getPage(): CategoryPreference {
        val display =
            CategoryPreference(Properties("display", context, R.string.main_setting_display))
        val language = getGroupLanguage()
        val theme = getGroupTheme()

        val list = mutableListOf<IPreference<*>>().apply {
            add(language)
            addAll(language.numberList)
            add(theme)
            addAll(theme.numberList)
        }
        display.numberList = list
        return display
    }


    fun getGroupLanguage(): TitlePreference {
        val language = GroupLanguage(context)

        val displayLanguage = SingleChoicePreference(mmkv, 0, language.displayLanguage) { pos, _ ->
            val newLocale = DisplayLanguage.list[pos].locale
            Locale.setDefault(newLocale)
            val config = context.resources.configuration
            ILanguageManager.setLocale(config, newLocale)
            context = context.createConfigurationContext(config)
        }.apply {
            isIdUseGroup = false
            setItemList(DisplayLanguage.list.map { context.getString(it.label) })
        }
        language.numberList = listOf(displayLanguage)
        return language
    }


    fun getGroupTheme(): TitlePreference {
        val theme = GroupTheme(context)

        val darkMode = SingleChoicePreference(
            mmkv,
            ThemeMode.list.indexOf(ThemeMode.current),
            theme.darkMode
        ) { pos, _ ->
            ThemeMode.current = ThemeMode.list[pos]
        }.apply {
            isIdUseGroup = false
            setItemList(ThemeMode.list.map { context.getString(it.id) })
        }
        MainMenuCategory.DISPLAY.addMenu(context.getString(R.string.business_setting_main_display_changedark)) { main, _ ->
            try {
                val v = darkMode.loadValue()
                darkMode.saveValue(ThemeMode.list.indexOf(if (ThemeMode.list[v] == ThemeMode.DARK) ThemeMode.LIGHT else ThemeMode.DARK))
                main.activity.startActivity(Intent(main.activity, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val lightTheme = SingleChoicePreference(mmkv, 0, theme.lightTheme) { pos, _ ->
            LightTheme.current = LightTheme.list[pos]
        }.apply {
            isIdUseGroup = false
            setItemList(LightTheme.list.map { context.getString(it.label) })
        }

        val darkTheme = SingleChoicePreference(mmkv, 0, theme.darkTheme) { pos, _ ->
            DarkTheme.current = DarkTheme.list[pos]
        }.apply {
            isIdUseGroup = false
            setItemList(DarkTheme.list.map { context.getString(it.label) })
        }
        theme.numberList = listOf(darkMode, lightTheme, darkTheme)
        return theme
    }
}