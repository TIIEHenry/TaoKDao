package taokdao.main.business.exit_control

import taokdao.main.IMainView


interface ExitControlContract {
    interface V : IMainView {
        val exitControlPresenter: ExitControlPresenter
        fun closeWindowIfShown(): Boolean
        fun showExitConfirmDialog()
    }

    interface P {
        fun observeOnBackPressed()

    }

    interface M {
        fun catchDoubleBack(): Boolean
    }
}