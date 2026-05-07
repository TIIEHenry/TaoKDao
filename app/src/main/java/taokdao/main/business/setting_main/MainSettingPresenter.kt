package taokdao.main.business.setting_main

import android.content.Context


class MainSettingPresenter(private val view: MainSettingContract.V) : MainSettingContract.P {
    private val model = MainSettingModel()

    override fun attachBaseContext(baseContext: Context): Context {
        return view.loadBaseMainSetting(model.getMMKV(view.mmkvManager), baseContext)
    }

    override fun loadMainSettingList() {
        val mmkv = model.getMMKV(view.mmkvManager)
        view.loadMainSettingList(mmkv)
    }
}