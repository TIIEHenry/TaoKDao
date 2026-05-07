package taokdao.main.business.progressbar_use

import taokdao.main.IMainView

interface ProgressUseContract {
    interface V : IMainView {
        val progressUsePresenter: ProgressUsePresenter

        fun updateProgress()
    }

    interface P {
        fun init()
    }

    interface M {
        fun initProgressUsers()
    }
}