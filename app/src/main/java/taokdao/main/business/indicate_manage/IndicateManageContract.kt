package taokdao.main.business.indicate_manage

import taokdao.api.ui.indicate.IIndicator
import taokdao.api.ui.indicate.IIndicatorManager
import taokdao.main.IMainView


interface IndicateManageContract {
    interface V : IMainView {
        val indicateManagePresenter: IndicateManagePresenter

    }

    interface P : IIndicatorManager {

    }

    interface M {

    }

    interface VW {
        val indicateManagePresenter: IndicateManagePresenter

        val startIndicator: IIndicator
        val endIndicator: IIndicator
    }
}