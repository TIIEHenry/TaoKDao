package taokdao.main.business.dialog_manage

import tiiehenry.android.ui.dialogs.mddialogs.MDDialogs


class DialogManagerPresenter(internal val view: DialogManagerContract.V) : DialogManagerContract.P {
    private val model = DialogManagerModel()

    override fun init() {
        MDDialogs(view.activity).initGlobal()
    }
}