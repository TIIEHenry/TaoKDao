package taokdao.main.business.session_control

import taokdao.api.data.mmkv.IMMKVManager
import taokdao.main.IMainView

interface SessionControlContract {
    interface V : IMainView {
        val sessionControlPresenter: SessionControlPresenter
        fun tryOpenByOpener(path: String, opener: String)
    }

    interface P {
        fun saveSession()
        fun restoreSession()
    }

    interface M {
        fun preserveProject(mmkvManager: IMMKVManager, projectDir: String)
        fun preserveFiles(mmkvManager: IMMKVManager, map: Map<String, String>)
        fun recoverProject(mmkvManager: IMMKVManager): String?
        fun recoverFiles(mmkvManager: IMMKVManager): MutableMap<String, String>
    }
}