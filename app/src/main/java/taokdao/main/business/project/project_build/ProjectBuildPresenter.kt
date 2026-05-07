package taokdao.main.business.project.project_build


class ProjectBuildPresenter(private val view: ProjectBuildContract.V) : ProjectBuildContract.P {
    private val model = ProjectBuildModel()
    override fun init() {
        model.initProjectBuilderPool()
        view.addDefaultProjectBuilder()
    }
}