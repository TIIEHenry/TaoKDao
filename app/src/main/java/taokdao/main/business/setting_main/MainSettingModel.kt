package taokdao.main.business.setting_main

import taokdao.api.data.mmkv.IMMKV
import taokdao.api.data.mmkv.IMMKVManager

class MainSettingModel : MainSettingContract.M {
    override fun getMMKV(mmkvManager: IMMKVManager): IMMKV {
        return mmkvManager.getMMKV("main")
    }
}
