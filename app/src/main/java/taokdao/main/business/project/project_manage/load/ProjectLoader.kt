package taokdao.main.business.project.project_manage.load

import taokdao.api.project.bean.Project
import taokdao.api.project.bean.ProjectConfig
import taokdao.api.project.build.IProjectBuilder
import taokdao.api.project.build.ProjectBuilderPool
import taokdao.api.project.load.IProjectLoader
import taokdao.api.project.load.ProjectLoadException
import taokdao.api.project.plugin.ProjectPluginPool
import java.io.File

open class ProjectLoader : IProjectLoader {

    @Throws(ProjectLoadException::class)
    private fun getSubProjectList(loadedProject: MutableSet<Project>, config: ProjectConfig): MutableSet<Project> {
        val projects = mutableSetOf<Project>()
        for (path in config.projects) {
            val dir = path.getRealFile(config.projectDir)
//            for (dir in dirList) {
            if (!dir.isDirectory)
                throw ProjectLoadException(config, "cannot load project(dir:$dir)")
            try {
                val childConfig = loadProjectConfigFromFile(getProjectConfigFile(dir))
                for (p in loadedProject) {
                    if (p.configFile.absolutePath == childConfig.configFile.absolutePath) {
                        projects.add(p)
                    } else {
                        val child = loadAllProject(childConfig, loadedProject)
                        child.name = path.alias
                        projects.add(child)
                    }
                }
            } catch (e: ProjectLoadException) {
                throw ProjectLoadException(config, e.message)
            } catch (e: Exception) {
                throw ProjectLoadException(config, "cannot load config(dir:${e.message})")
            }
//            }
        }
        return projects
    }

    @Throws(ProjectLoadException::class)
    private fun getBuilder(config: ProjectConfig): IProjectBuilder {
        val builder = ProjectBuilderPool.getInstance().get(config.builder.id)
                ?: throw ProjectLoadException(config, "cannot load builder(id:${config.builder})")
        builder.loadParameters(config.builder.parameters)
        return builder
    }

    @Throws(ProjectLoadException::class)
    private fun getPluginList(config: ProjectConfig): MutableSet<Project.Plugin> {
        val plugins = mutableSetOf<Project.Plugin>()
        for (plugin in config.plugins) {
            val p = ProjectPluginPool.getInstance().get(plugin.id)
                    ?: throw ProjectLoadException(config, "cannot load plugin(id:$plugin)")
            plugins.add(Project.Plugin(p, plugin.parameters))
        }
        return plugins
    }

    @Throws(ProjectLoadException::class)
    override fun loadProject(config: ProjectConfig): Project {
        return loadAllProject(config)
    }

    @Throws(ProjectLoadException::class)
    fun loadAllProject(config: ProjectConfig, loadedProject: MutableSet<Project> = mutableSetOf()): Project {
        val project = Project(config)
        project.builder = getBuilder(config)
        project.plugins = getPluginList(config)
        loadedProject.add(project)
        project.projects = getSubProjectList(loadedProject, config)
        return project
    }

    @Throws
    override fun loadProjectConfigFromFile(configFile: File): ProjectConfig {
        return ProjectConfig.from(configFile, configFile.readText()).apply {
            projectDir = configFile.parentFile
            this.configFile = configFile
        }
    }
//
//    override fun loadProjectConfigFromFile(configFile: File): ProjectConfigs? {
//        val config = ProjectConfigs(configFile).apply {
//            projectDirFile = configFile.parentFile
//        }
//        val engine = main.newEngineForConfig(configFile).apply {
//            fileEvaler.evalFile(configFile, loadProjectConfigListener)
//            requirer.require("apply")
//            varBridge.apply {
//                jsToJava("dependencies", Map::class.java) { map ->
//                    val projectMap = HashMap<String, String>()
//                    config.dependencies["project"] = projectMap
//                    when (val pMap = (map as? Map<String, Map<String, String>>?)?.get("project")) {
//                        //<String, Map<String, String>>
//                        is Map -> projectMap.putAll(pMap)
//                    }
//                }
//                jsToJava("structure", Map::class.java) { map ->
//                    when (val sMap = map as? Map<String, String>?) {
//                        //<String, String>
//                        is Map -> config.structure.putAll(sMap)
//                    }
//                }
//                jsToJava("sources", Map::class.java) { map ->
//                    when (val sMap = map as? Map<String, String>?) {
//                        //<String, String>
//                        is Map -> config.sources.putAll(sMap)
//                    }
//                }
//                jsToJava("applied", Map::class.java) { map ->
//                    when (val sMap = map as? Map<String, Any?>?) {
//                        //<String, String>
//                        is Map -> {
//                            sMap["builder"]?.asString {
//                                config.builder = it
//                            }
//                            val pluginList = sMap["plugin"] as? List<String>
//                            pluginList?.forEach {
//                                config.pluginList.add(it)
//                                val info = main.pluginManager.pluginLoader.getPluginInfo(it)
//                                if (info == null) {
//                                    main.toast("plugin[$it] not exists")
//                                } else {
//                                    val toreq = File(info.dir, "applied.js")
//                                    if (toreq.exists()) {
//                                        requirer.require(toreq)
//                                    } else {
//                                        main.toast("plugin[$it] don't have entrance applied.js")
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                config.structure.putAll(config.sources)
//            }
//        }
//        return config
//    }

//    override fun getProjectStructureMapFromConfigFile(configFile: File): HashMap<String, File> {
//        val map = HashMap<String, File>()
//        val config = loadProjectConfigFromFile(configFile)
//        config?.let {
//            for ((name, v) in it.structure) {
//                map[name] = when {
//                    v.startsWith("../") -> File(configFile.parentFile.parentFile, v.substring(3))
//                    v.startsWith("/") -> File(v)
//                    else -> File(configFile.parentFile, v)
//                }
//            }
//        }
//        return map
//    }
//
//    override fun getProjectDirListWithDependencies(projectDirFile: File): List<File> {
//        return getProjectDirPathSetWithDependencies(projectDirFile).map { File(it) }
//    }
//
//    private fun getProjectDirPathSetWithDependencies(projectDirFile: File): HashSet<String> {
//        val list = HashSet<String>()
//        if (list.contains(projectDirFile.absolutePath))
//            return list
//        list.add(projectDirFile.absolutePath)
//        val configFile = getProjectConfigFile(projectDirFile)
//        if (configFile.isFile) {
//            loadProjectConfigFromFile(configFile)?.dependencies?.get("project")?.let {
//                for ((dependenttype, v) in it) {
//                    val file = when {
//                        v.startsWith("../") -> File(projectDirFile.parentFile, v.substring(3))
//                        v.startsWith("/") -> File(v)
//                        else -> File(projectDirFile, v)
//                    }
//                    if (file.isDirectory) {
//                        list.addAll(getProjectDirPathSetWithDependencies(file))
//                    }
//                }
//            }
//        }
//        return list
//    }
//
}
