package taokdao.main.business.action_process


class ActionProcessPresenter(private val view: ActionProcessContract.V) : ActionProcessContract.P {
    private val model = ActionProcessModel()

    override fun initMainAction() {
        model.initMainAction()
        view.registerMainActionLifecycleObserver()
    }
}