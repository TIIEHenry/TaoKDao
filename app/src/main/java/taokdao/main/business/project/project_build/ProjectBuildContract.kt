package taokdao.main.business.project.project_build


interface ProjectBuildContract {
    interface V {
        val projectBuildPresenter: ProjectBuildPresenter
        fun addDefaultProjectBuilder()
    }

    interface P {
        fun init()

    }

    interface M {
        fun initProjectBuilderPool()

    }
}