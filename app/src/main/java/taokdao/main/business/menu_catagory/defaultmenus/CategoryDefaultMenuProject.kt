package taokdao.main.business.menu_catagory.defaultmenus

import taokdao.api.main.IMainContext
import taokdao.api.main.menu.MainMenu
import tiiehenry.ideditor.R

class CategoryDefaultMenuProject(val main: IMainContext) {

    val newProject = MainMenu(main.getString(R.string.main_menu_project_newproject)) { main, _ ->
        main.projectTemplateGenerator.showChooseDialog(main.fileSystem.projectDir)
    }
    val closeProject = MainMenu(main.getString(R.string.main_menu_project_closeproject)) { main, _ ->
        main.projectManager.closeProject()
    }
    val reopenProject = MainMenu(main.getString(R.string.main_menu_project_reopenproject)) { main, _ ->
        main.projectManager.reopenProject()
    }
}