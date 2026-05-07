package taokdao.main.business.menu_catagory.defaultmenus

import taokdao.api.main.IMainContext
import taokdao.api.main.menu.MainMenu
import taokdao.main.MainActivity
import tiiehenry.ideditor.R
import org.jetbrains.anko.startActivity
import tiiehenry.taokdao.activity.AboutActivity
import tiiehenry.taokdao.activity.LawActivity
import tiiehenry.taokdao.ui.setting.LeftTopSettingPopup
import tiiehenry.taokdao.ui.setting.RightTopSettingPopup

class CategoryDefaultMenuSetting(val main: IMainContext) {
    val appSetting = MainMenu(main.getString(R.string.main_menu_setting_appsetting)) { main, _ ->
        LeftTopSettingPopup(main, (main as MainActivity).settingList, main.binding.mainToolbarIndicatorStartTv).showAt()
    }

    val showContentSetting = MainMenu(main.getString(R.string.main_menu_display_showcontentsetting)) { main, _ ->
        main.contentManager.showSettingWindow()
    }
    val appIntroduce = MainMenu(main.getString(R.string.main_menu_project_appintroduce)) { main, _ ->
        main.activity.startActivity<AboutActivity>()
    }
    val law = MainMenu(main.getString(R.string.main_menu_project_law)) { main, _ ->
        main.activity.startActivity<LawActivity>()
    }

}