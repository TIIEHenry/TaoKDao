package taokdao.main.business.project.project_build

import taokdao.api.project.build.ProjectBuilderPool

class ProjectBuildModel : ProjectBuildContract.M {
    override fun initProjectBuilderPool() {
        ProjectBuilderPool.newInstance()
    }


}
