package taokdao.main.business.dialog_manage

import taokdao.main.IMainView


interface DialogManagerContract {
    interface V : IMainView {
        val dialogManagerPresenter: DialogManagerPresenter

    }

    interface P {
        fun init()

    }

    interface M
}