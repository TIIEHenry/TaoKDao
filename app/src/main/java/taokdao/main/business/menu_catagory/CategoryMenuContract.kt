package taokdao.main.business.menu_catagory


import taokdao.main.IMainView

interface CategoryMenuContract {

    interface V : IMainView {
        val categoryMenuPresenter: CategoryMenuPresenter
        fun addDefaultCategoryMenu()
        fun showCategoryMenu()
    }

    interface P {
        fun init()
    }

    interface M {
        fun initCategoryMenu()

    }
}