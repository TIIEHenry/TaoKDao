package taokdao.main.business.progressbar_use

import android.view.View
import taokdao.api.ui.progressbar.ProgressBarSet
import tiiehenry.ideditor.databinding.ActivityMainBinding

interface ProgressUseView : ProgressUseContract.V {
    override fun updateProgress() {
        launchMain {
            ActivityMainBinding.bind(contentView).mainBottomProgressBar.visibility = if (ProgressBarSet.BOTTOM_HORIZONTAL.hasUser()) View.VISIBLE else View.GONE
        }
    }
}