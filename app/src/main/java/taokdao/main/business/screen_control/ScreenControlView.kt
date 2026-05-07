package taokdao.main.business.screen_control

import taokdao.api.main.action.MainAction


interface ScreenControlView : ScreenControlContract.V {
    override fun onScreenPortrait() {
        MainAction.onPortrait.runObservers(this)
        MainAction.onOrientationChanged.runObservers(this)
    }

    override fun onScreenLandscape() {
        MainAction.onLandscape.runObservers(this)
        MainAction.onOrientationChanged.runObservers(this)
    }
}