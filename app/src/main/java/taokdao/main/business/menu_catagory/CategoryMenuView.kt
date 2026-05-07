package taokdao.main.business.menu_catagory

import taokdao.api.main.menu.MainMenuCategory
import taokdao.main.business.menu_catagory.defaultmenus.*
import taokdao.main.business.menu_catagory.popup.MenuPopup

interface CategoryMenuView : CategoryMenuContract.V {
    override fun addDefaultCategoryMenu() {
        val categoryDefaultMenuFile = CategoryDefaultMenuFile(this)
        MainMenuCategory.FILE.apply {
            addMenu(categoryDefaultMenuFile.openFile)
            addMenu(categoryDefaultMenuFile.saveAll)
            addMenu(categoryDefaultMenuFile.saveCurrent)
            addMenu(categoryDefaultMenuFile.closeCurrent)
            addMenu(categoryDefaultMenuFile.closeOther)
            addMenu(categoryDefaultMenuFile.closeAll)
        }
        val categoryDefaultMenuEdit = CategoryDefaultMenuEdit(this)
        MainMenuCategory.EDIT.apply {
            addMenu(categoryDefaultMenuEdit.searchCurrent)
            addMenu(categoryDefaultMenuEdit.searchReplace)
            addMenu(categoryDefaultMenuEdit.noteBlock)
            addMenu(categoryDefaultMenuEdit.docBlock)
        }
        val categoryDefaultMenuBuild = CategoryDefaultMenuBuild(this)
        MainMenuCategory.BUILD.apply {
            addMenu(categoryDefaultMenuBuild.buildFile)
            addMenu(categoryDefaultMenuBuild.buildProject)
            addMenu(categoryDefaultMenuBuild.buildOption)
        }
        val categoryDefaultMenuProject = CategoryDefaultMenuProject(this)
        MainMenuCategory.PROJECT.apply {
            addMenu(categoryDefaultMenuProject.newProject)
            addMenu(categoryDefaultMenuProject.reopenProject)
            addMenu(categoryDefaultMenuProject.closeProject)
        }
        val categoryDefaultMenuDisplay = CategoryDefaultMenuDisplay(this)
        MainMenuCategory.DISPLAY.apply {
            addMenu(categoryDefaultMenuDisplay.changeFullscreen)
            addMenu(categoryDefaultMenuDisplay.showToolWindow)
            addMenu(categoryDefaultMenuDisplay.showPluginWindow)
        }
        val categoryDefaultMenuSetting = CategoryDefaultMenuSetting(this)
        MainMenuCategory.SETTING.apply {
            addMenu(categoryDefaultMenuSetting.appSetting)
            addMenu(categoryDefaultMenuSetting.showContentSetting)
            addMenu(categoryDefaultMenuSetting.appIntroduce)
            addMenu(categoryDefaultMenuSetting.law)
        }

    }

    val menuPopup: MenuPopup

    override fun showCategoryMenu() {
        menuPopup.show()
    }
}