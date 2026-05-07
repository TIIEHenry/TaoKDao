package taokdao.main.business.mmkv_manage

import com.tencent.mmkv.MMKV
import taokdao.api.data.mmkv.IMMKV


class MMKVManagePresenter(internal val view: MMKVManageContract.V) : MMKVManageContract.P {
    private val model = MMKVManageModel()

    override fun getGlobalMMKV(): IMMKV {
        return IMMKVWrapper(MMKV.defaultMMKV())
    }

    override fun getMMKV(name: String): IMMKV {
        return IMMKVWrapper(MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE))
    }
}