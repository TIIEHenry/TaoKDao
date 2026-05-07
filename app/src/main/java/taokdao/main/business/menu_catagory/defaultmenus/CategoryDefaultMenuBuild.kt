package taokdao.main.business.menu_catagory.defaultmenus

import taokdao.api.main.IMainContext
import taokdao.api.main.menu.MainMenu
import tiiehenry.ideditor.R

class CategoryDefaultMenuBuild(val main: IMainContext) {
    val buildFile = MainMenu(main.getString(R.string.main_menu_build_buildfile)) { main, _ ->
        main.buildManager.buildFile(true)
    }
    val buildProject = MainMenu(main.getString(R.string.main_menu_build_buildproject)) { main, _ ->
        main.buildManager.buildProject(true)
    }
    val buildOption = MainMenu(main.getString(R.string.main_menu_build_buildoption)) { main, _ ->
        main.buildManager.buildProject(false)
    }
}