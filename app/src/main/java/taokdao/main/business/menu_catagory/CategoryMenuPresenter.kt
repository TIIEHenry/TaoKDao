package taokdao.main.business.menu_catagory

class CategoryMenuPresenter(private val view: CategoryMenuContract.V) : CategoryMenuContract.P {
    private val model = CategoryMenuModel()
    override fun init() {
        model.initCategoryMenu()
        view.addDefaultCategoryMenu()
    }

}