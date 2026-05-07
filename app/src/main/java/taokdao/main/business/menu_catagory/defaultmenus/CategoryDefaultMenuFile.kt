package taokdao.main.business.menu_catagory.defaultmenus

import taokdao.api.main.IMainContext
import taokdao.api.main.menu.MainMenu
import tiiehenry.ideditor.R

class CategoryDefaultMenuFile(val main: IMainContext) {
    val openFile = MainMenu(main.getString(R.string.main_menu_file_openfile)) { main, _ ->
        main.explorerWindow.showWindow()
    }
    val saveAll = MainMenu(main.getString(R.string.main_menu_file_saveall)) { main, _ ->
        main.contentManager.saveAllAsync()
    }
    val saveCurrent = MainMenu(main.getString(R.string.main_menu_file_savecurrent)) { main, _ ->
        main.contentManager.saveCurrentAsync()
    }
    val closeCurrent = MainMenu(main.getString(R.string.main_menu_file_closecurrent)) { main, _ ->
        main.contentManager.closeCurrent()
    }
    val closeOther = MainMenu(main.getString(R.string.main_menu_file_closeother)) { main, _ ->
        main.contentManager.closeOther()
    }
    val closeAll = MainMenu(main.getString(R.string.main_menu_file_closeall)) { main, _ ->
        main.contentManager.closeAll()
    }
}