package taokdao.builder.projectbuilders

import taokdao.api.builder.wrapped.BuildOption
import taokdao.api.data.bean.Properties
import taokdao.api.project.bean.Project
import taokdao.api.project.build.IProjectBuilder

object FileProjectBuilder : IProjectBuilder {

    private val buildList = mutableListOf(BuildOption<Project>(Properties("file", "build")) { main, config, _ ->
        val path = main.contentManager.current?.path ?: return@BuildOption false
        main.buildManager.buildFile(true)
        true
    })

    override fun id(): String {
        return "file"
    }

    override fun getBuildOptionList(): MutableList<BuildOption<Project>> {
        return buildList
    }

    override fun loadParameters(parameters: MutableList<String>) {

    }

}