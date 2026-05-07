package taokdao.main.business.action_process

import taokdao.main.IMainView


interface ActionProcessContract {
    interface V : IMainView {
        val actionProcessPresenter: ActionProcessPresenter

        fun registerMainActionLifecycleObserver()
    }

    interface P {
        fun initMainAction()

    }

    interface M {
        fun initMainAction()

    }
}