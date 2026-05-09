package taokdao.main.business.build_manage

import taokdao.api.builder.IBuildManager
import taokdao.api.builder.IBuildOption
import taokdao.api.data.mmkv.IMMKVManager
import taokdao.api.file.build.IFileBuilder
import taokdao.api.project.bean.Project
import taokdao.main.IMainView
import java.io.File
import java.util.*


interface BuildManageContract {
    interface V : IMainView {
        fun addInternalFileBuilder()
        fun showFileBuilderChooseDialog(builderList: List<IFileBuilder>, file: File, id: String?, buildDefault: Boolean)
        fun alertNoBuilder()
        fun alertNoBuildOption()
        fun alertNoProjectOpened()

        fun showBuildOptionDialogForFile(builder: IFileBuilder, optionList: List<IBuildOption<File>>, file: File)

        fun showBuildOptionDialogForProject(optionList: List<IBuildOption<Project>>, project: Project)

        val buildManagePresenter: BuildManagePresenter

    }

    interface P : IBuildManager {
        fun onFileBuilderChosen(builder: IFileBuilder, file: File, buildDefault: Boolean)
        fun onFileBuilderOptionChosen(builder: IFileBuilder, option: IBuildOption<File>, file: File)
        fun onProjectBuilderOptionChosen(option: IBuildOption<Project>, project: Project)
    }

    interface M {
        fun init(mmkvManager: IMMKVManager)
        fun setDefaultBuilderId(suffix: String, id: String)
        fun getDefaultBuilderId(suffix: String): String?
        fun setDefaultBuilderOptionId(suffix: String, builderId: String, optionId: String)
        fun getDefaultBuilderOptionId(suffix: String, builderId: String): String?
        fun setDefaultProjectBuilderOptionId(project: Project, optionId: String)
        fun getDefaultProjectBuilderOptionId(project: Project): String?
    }
}