package taokdao.main.business.screen_control

import android.content.res.Configuration


class ScreenControlPresenter(private val view: ScreenControlContract.V) : ScreenControlContract.P {
    private val model = ScreenControlModel()

    override fun observeOrientation(newConfig: Configuration) {
        val o = model.observeOrientation(newConfig)
        when (o) {
            -1 -> {
            }
            Configuration.ORIENTATION_UNDEFINED -> {
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                view.onScreenPortrait()
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                view.onScreenLandscape()
            }
        }
    }
}