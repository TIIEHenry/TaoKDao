package taokdao.main.business.menu_catagory

import taokdao.api.main.menu.MainMenuCategory

class CategoryMenuModel : CategoryMenuContract.M {
    override fun initCategoryMenu() {
        MainMenuCategory.clearAll()
    }

}
