package taokdao.main.business.project.project_build

import taokdao.api.project.build.ProjectBuilderPool
import taokdao.builder.projectbuilders.FileProjectBuilder


interface ProjectBuildView : ProjectBuildContract.V {
    override fun addDefaultProjectBuilder() {
        ProjectBuilderPool.getInstance().let {
//            it.add(ScriptProjectTaskBuilder)
            it.add(FileProjectBuilder)
        }
    }
}