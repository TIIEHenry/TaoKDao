package taokdao.main.business.exit_control

import tiiehenry.ideditor.R


class ExitControlPresenter(private val view: ExitControlContract.V) : ExitControlContract.P {
    private val model = ExitControlModel()
    override fun observeOnBackPressed() {
        if (view.closeWindowIfShown()) {

        } else {
            if (model.catchDoubleBack()) {
                view.showExitConfirmDialog()
            } else {
                view.notify(R.string.press_again_to_exit)
            }
        }
    }
}