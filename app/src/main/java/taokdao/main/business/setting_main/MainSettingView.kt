package taokdao.main.business.setting_main

import android.content.Context
import taokdao.api.data.mmkv.IMMKV
import taokdao.main.business.setting_main.page.PageDisplay
import taokdao.main.business.setting_main.page.PageSession


interface MainSettingView : MainSettingContract.V {
    override fun loadBaseMainSetting(mmkv: IMMKV, baseContext: Context): Context {
        val pageDisplay = PageDisplay(mmkv, baseContext).apply {
            getGroupLanguage().numberList.forEach { it.load() }
            getGroupTheme().numberList.forEach { it.load() }
        }
        return pageDisplay.context
    }

    override fun loadMainSettingList(mmkv: IMMKV) {
        settingList.apply {
            clear()
            add(PageDisplay(mmkv, context).getPage())
            add(PageSession(mmkv, context).getPage())
            forEach {
                it.load()
            }
        }
    }
}