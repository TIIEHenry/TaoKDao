package taokdao.main.business.screen_control

import android.content.res.Configuration
import taokdao.main.IMainView


interface ScreenControlContract {
    interface V : IMainView {
        fun onScreenPortrait()
        fun onScreenLandscape()

        val screenControlPresenter: ScreenControlPresenter
    }

    interface P {
        fun observeOrientation(newConfig: Configuration)

    }

    interface M {
        /**
         * -1为未改变
         * @return orientation
         */
        fun observeOrientation(newConfig: Configuration): Int

    }
}