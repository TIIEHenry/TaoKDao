package taokdao.main.business.build_manage.buildoptionpopup

import taokdao.api.builder.IBuildOption
import taokdao.api.file.build.IFileBuilder
import taokdao.api.main.IMainContext
import taokdao.main.business.build_manage.BuildManagePresenter
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.UIRecyclerPopup
import java.io.File

class FileBuilderOptionPopup(val main: IMainContext, private val menuList: List<Pair<IBuildOption<File>, File>>, val buildManagePresenter: BuildManagePresenter, var builder: IFileBuilder)
    : UIRecyclerPopup<Pair<IBuildOption<File>, File>>(main.activity,

        BuildOptionMenuAdapter()) {
    init {

        adapter.apply {
            refresh(menuList)
            setOnItemClickListener { _, item, _ ->
                dismiss()
                buildManagePresenter.onFileBuilderOptionChosen(builder, item.first, item.second)
            }
        }


        create(-2, main.getDimen(R.dimen.toolbar_controlmenu_item_H) * adapter.itemCount)
    }
}