package taokdao.main.business.menu_catagory.defaultmenus

import android.view.WindowManager
import taokdao.api.main.IMainContext
import taokdao.api.main.menu.MainMenu
import taokdao.api.ui.window.Windows
import taokdao.main.MainActivity
import tiiehenry.ideditor.R

class CategoryDefaultMenuDisplay(val main: IMainContext) {
    var mIsFullScreen = false
    val changeFullscreen = MainMenu(main.getString(R.string.main_menu_display_changefullscreen)) { main, _ ->
        if (mIsFullScreen) {//设置为非全屏
            val lp = (main as MainActivity).window.attributes
            lp.flags = lp.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
            main.window.attributes = lp
            main.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        } else {//设置为全屏
            val lp = (main as MainActivity).window.attributes
            lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
            main.window.attributes = lp
            main.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        mIsFullScreen = !mIsFullScreen
    }
    val showToolWindow = MainMenu(main.getString(R.string.main_menu_display_showtoolwindow)) { _, _ ->
        Windows.TOOL_PAGES.window.apply {
            if (isWindowShown)
                hideWindow()
            else
                showWindow()
        }
    }
    val showPluginWindow = MainMenu(main.getString(R.string.main_menu_display_showpluginpopup)) { main, _ ->
        main.pluginManager.showPluginLauncher()
    }

}