package taokdao.main.business.action_process

import taokdao.api.main.action.MainAction

class ActionProcessModel : ActionProcessContract.M {
    override fun initMainAction() {
        MainAction.clearAll()
    }
}
