package taokdao.main.business.setting_main

import android.content.Context
import taokdao.api.data.mmkv.IMMKV
import taokdao.api.data.mmkv.IMMKVManager
import taokdao.api.setting.preference.base.IPreference
import taokdao.main.IMainView


interface MainSettingContract {
    interface V : IMainView {
        val mainSettingPresenter: MainSettingPresenter

        val settingList: MutableList<IPreference<*>>

        fun loadBaseMainSetting(mmkv: IMMKV, baseContext: Context): Context
        fun loadMainSettingList(mmkv: IMMKV)

    }

    interface P {
        fun attachBaseContext(baseContext: Context): Context
        fun loadMainSettingList()
    }

    interface M {
        fun getMMKV(mmkvManager: IMMKVManager): IMMKV

    }
}