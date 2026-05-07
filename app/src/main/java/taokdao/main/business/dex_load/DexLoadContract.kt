package taokdao.main.business.dex_load

import taokdao.api.data.dex.load.IDexLoader
import taokdao.main.IMainView


interface DexLoadContract {
    interface V : IMainView {
        val dexLoadPresenter: DexLoadPresenter

    }

    interface P : IDexLoader

    interface M
}