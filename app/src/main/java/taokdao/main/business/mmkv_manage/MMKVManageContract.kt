package taokdao.main.business.mmkv_manage

import taokdao.api.data.mmkv.IMMKVManager
import taokdao.main.IMainView


interface MMKVManageContract {
    interface V : IMainView {
        val mmkvManagePresenter: MMKVManagePresenter

    }

    interface P : IMMKVManager, IMMKVManagerWrapper

    interface M
}