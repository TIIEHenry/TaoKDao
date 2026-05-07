package taokdao.main.business.indicate_manage

import taokdao.api.ui.indicate.IIndicator


class IndicateManagePresenter(internal val view: IndicateManageContract.V) : IndicateManageContract.P {
    private val model = IndicateManageModel()

    internal val vw: IndicateManageContract.VW by lazy {
        IndicateManageViewWrapper(view, this)
    }

    override fun getStartIndicator(): IIndicator {
        return vw.startIndicator
    }

    override fun getEndIndicator(): IIndicator {
        return vw.endIndicator
    }
}