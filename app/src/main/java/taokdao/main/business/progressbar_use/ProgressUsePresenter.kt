package taokdao.main.business.progressbar_use

import taokdao.api.event.senders.ProgressBarSender
import taokdao.api.ui.progressbar.ProgressBarSet

class ProgressUsePresenter(private val view: ProgressUseContract.V) : ProgressUseContract.P {
    private val model = ProgressUseModel()
    override fun init() {
        model.initProgressUsers()
        ProgressBarSet.BOTTOM_HORIZONTAL.addObserver(object : ProgressBarSet.Observer {
            val sender = ProgressBarSender(ProgressBarSet.BOTTOM_HORIZONTAL)
            override fun onRemoved(bar: ProgressBarSet, id: String) {
                sender.removeUser(id).log(view)
                view.updateProgress()
            }

            override fun onAdded(bar: ProgressBarSet, id: String) {
                sender.addUser(id).log(view)
                view.updateProgress()
            }
        })
    }
}