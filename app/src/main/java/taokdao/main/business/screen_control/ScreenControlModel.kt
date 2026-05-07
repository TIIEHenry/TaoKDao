package taokdao.main.business.screen_control

import android.content.res.Configuration

class ScreenControlModel : ScreenControlContract.M {
    private var lastOrientation = Configuration.ORIENTATION_PORTRAIT
    override fun observeOrientation(newConfig: Configuration): Int {
        val o = newConfig.orientation
        if (o != lastOrientation) {
            if (o == Configuration.ORIENTATION_PORTRAIT || o == Configuration.ORIENTATION_LANDSCAPE) {
                lastOrientation = o
            }
            return o
        }
        return -1
    }
}
