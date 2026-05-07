package taokdao.main.business.build_manage

import taokdao.api.builder.IBuildOption
import taokdao.api.file.build.FileBuilderPool
import taokdao.api.file.build.IFileBuilder
import taokdao.api.project.bean.Project
import taokdao.api.ui.progressbar.ProgressBarSet
import taokdao.main.business.content_manage.ContentManagePresenter
import java.io.File


class BuildManagePresenter(internal val view: BuildManageContract.V) : BuildManageContract.P {
    private val model = BuildManageModel()

    override fun init() {
        model.init(view.mmkvManager)
        view.addInternalFileBuilder()
    }

    override fun buildFile(buildDefault: Boolean) {
        val path = view.contentManager.current?.path ?: return
        view.launchIO {
            ProgressBarSet.BOTTOM_HORIZONTAL.addUser(ContentManagePresenter.SAVE_CURRENT)
            view.contentManager.saveCurrent()
            ProgressBarSet.BOTTOM_HORIZONTAL.removeUser(ContentManagePresenter.SAVE_CURRENT)
            view.launchMain {
                buildFile(File(path), buildDefault)
            }
        }
    }

    override fun buildFile(file: File, buildDefault: Boolean) {
        val builderList = FileBuilderPool.getInstance().getList(file)

        if (builderList.isEmpty()) {
            view.alertNoBuilder()
            return
        }
        val id = if (buildDefault) {
            model.getDefaultBuilderId(file.extension)
        } else {
            null
        }
        val builder = builderList.find { it.id() == id }
        if (id == null || builder == null) {
            view.showFileBuilderChooseDialog(builderList, file, id, buildDefault)
            return
        }
        onFileBuilderChosen(builder, file, buildDefault)
    }

    override fun onFileBuilderChosen(builder: IFileBuilder, file: File, buildDefault: Boolean) {
        model.setDefaultBuilderId(file.extension, builder.id())

        val list = builder.buildOptionList
        if (list.isEmpty()) {
            view.alertNoBuildOption()
            return
        }
        val id = if (buildDefault) {
            model.getDefaultBuilderOptionId(file.extension, builder.id())
        } else {
            null
        }
        val option = list.find { it.id() == id }
        if (id == null || option == null) {
            view.showBuildOptionDialogForFile(builder, list, file)
            return
        }
        onFileBuilderOptionChosen(builder, option, file)
    }

    override fun onFileBuilderOptionChosen(builder: IFileBuilder, option: IBuildOption<File>, file: File) {
        model.setDefaultBuilderOptionId(file.extension, builder.id(), option.id())
        option.onBuild(view, file)
    }

    private fun checkProject(): Boolean {
        if (!view.projectManager.isOpenedProject) {
            view.alertNoProjectOpened()
            return false
        }
        return true
    }


    override fun buildProject(buildDefault: Boolean) {
        if (!checkProject()) {
            return
        }
        val project = view.projectManager.project ?: return
        view.launchIO {
            ProgressBarSet.BOTTOM_HORIZONTAL.addUser(ContentManagePresenter.SAVE_ALL)
            view.contentManager.saveAll()
            ProgressBarSet.BOTTOM_HORIZONTAL.removeUser(ContentManagePresenter.SAVE_ALL)
            view.launchMain {
                buildProject(project, buildDefault)
            }
        }
    }

    override fun buildProject(project: Project, buildDefault: Boolean) {
        val list = project.builder.buildOptionList
        if (list.isEmpty()) {
            view.alertNoBuildOption()
            return
        }
        val id = if (buildDefault) {
            model.getDefaultProjectBuilderOptionId(project)
        } else {
            null
        }
        val option = list.find { it.id() == id }
        if (id == null || option == null) {
            view.showBuildOptionDialogForProject(list, project)
            return
        }
        onProjectBuilderOptionChosen(option, project)
    }

    override fun onProjectBuilderOptionChosen(option: IBuildOption<Project>, project: Project) {
        model.setDefaultProjectBuilderOptionId(project, option.id())
        option.onBuild(view, project)
    }

}