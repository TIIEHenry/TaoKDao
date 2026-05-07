package taokdao.main.business.build_manage

import taokdao.api.builder.IBuildOption
import taokdao.api.file.build.FileBuilderPool
import taokdao.api.file.build.IFileBuilder
import taokdao.api.project.bean.Project
import taokdao.builder.filebuilders.HtmlViewer
import taokdao.builder.filebuilders.MarkdownBuilder
import taokdao.main.business.build_manage.buildoptionpopup.FileBuilderOptionPopup
import taokdao.main.business.build_manage.buildoptionpopup.ProjectBuildOptionPopup
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ActivityMainBinding
import java.io.File
import java.util.*


interface BuildManageView : BuildManageContract.V {
    override fun addInternalFileBuilder() {
        FileBuilderPool.getInstance().add(HtmlViewer(this))
        FileBuilderPool.getInstance().add(MarkdownBuilder(this))
//        FileBuilderPool.addFileBuilder(JsBuilder)
//        FileBuilderPool.addFileBuilder(MarkdownBuilder)
    }

    override fun showFileBuilderChooseDialog(builderList: ArrayList<IFileBuilder>, file: File, id: String?, buildDefault: Boolean) {
        val labelList = builderList.map { it.label }
        var index = builderList.indexOfFirst { it.id() == id }
        index = if (index == -1) {
            0
        } else {
            index
        }
        Dialogs.global
                .asList()
                .typeSingleChoice()
                .title(R.string.business_build_manage_chooser_title)
                .items(labelList)
                .itemsCallbackSingleChoice(index) { dialog, which, text ->
                    buildManagePresenter.onFileBuilderChosen(builderList[which], file, buildDefault)
                    true
                }
                .positiveText()
                .negativeText()
                .show()
    }

    override fun alertNoBuilder() {
        Dialogs.global
                .asConfirm()
                .title(R.string.business_build_manage_nobuilder_title)
                .content(R.string.business_build_manage_nobuilder_content)
                .positiveText()
                .show()
    }

    override fun alertNoBuildOption() {
        Dialogs.global
                .asConfirm()
                .title(R.string.business_build_manage_nobuildeoption_title)
                .content(R.string.business_build_manage_nobuildeoption_content)
                .positiveText()
                .show()
    }

    override fun alertNoProjectOpened() {
        Dialogs.global
                .asConfirm()
                .title(R.string.business_build_manage_noprojectopened_title)
                .content(R.string.business_build_manage_noprojectopened_content)
                .positiveText()
                .show()
    }

    override fun showBuildOptionDialogForFile(builder: IFileBuilder, optionList: MutableList<IBuildOption<File>>, file: File) {
        FileBuilderOptionPopup(this, optionList.map { Pair(it, file) }, buildManagePresenter, builder).show(ActivityMainBinding.bind(contentView).mainToolbarMenuRv)
    }

    override fun showBuildOptionDialogForProject(optionList: MutableList<IBuildOption<Project>>, project: Project) {
        ProjectBuildOptionPopup(this, optionList.map { Pair(it, project) }, buildManagePresenter).show(ActivityMainBinding.bind(contentView).mainToolbarMenuRv)
    }
}