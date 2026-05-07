package taokdao.main.business.progressbar_use

import taokdao.api.ui.progressbar.ProgressBarSet

class ProgressUseModel : ProgressUseContract.M {
    override fun initProgressUsers() {
        ProgressBarSet.clearAllUser()
    }
}
