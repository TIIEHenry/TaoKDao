package taokdao.main.business.build_manage.buildoptionpopup

import taokdao.api.builder.IBuildOption
import taokdao.api.main.IMainContext
import taokdao.api.project.bean.Project
import taokdao.main.business.build_manage.BuildManagePresenter
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.UIRecyclerPopup

class ProjectBuildOptionPopup(val main: IMainContext, private val menuList: List<Pair<IBuildOption<Project>, Project>>, val buildManagePresenter: BuildManagePresenter)
    : UIRecyclerPopup<Pair<IBuildOption<Project>, Project>>(main.activity, BuildOptionMenuAdapter()) {
    init {

        adapter.apply {
            refresh(menuList)
            setOnItemClickListener { _, item, _ ->
                dismiss()
                buildManagePresenter.onProjectBuilderOptionChosen(item.first, item.second)
            }
        }


        create(-2, main.getDimen(R.dimen.toolbar_controlmenu_item_H) * adapter.itemCount)
    }

}