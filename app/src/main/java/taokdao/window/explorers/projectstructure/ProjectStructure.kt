package taokdao.window.explorers.projectstructure

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Handler
import android.os.Looper
import org.jetbrains.anko.doAsync
import tiiehenry.ideditor.databinding.ExplorersProjectstructureBinding
import taokdao.api.data.bean.Properties
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.action.MainAction
import taokdao.api.project.bean.Project
import taokdao.api.project.bean.ProjectConfigJson
import taokdao.api.project.bean.RelativeAliasPaths
import taokdao.api.project.bean.RelativePath
import taokdao.api.ui.explorer.menu.ExplorerMenu
import taokdao.api.ui.explorer.wrapped.ExplorerFragment
import taokdao.main.IMainView
import taokdao.main.business.window.window_explorer.popup.tree.*
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.treeview.*
import java.io.File

class ProjectStructure(val main: IMainView) : ExplorerFragment(
        Properties(InnerIdentifier.Explorer.PROJECT_STRUCTURE, main.context, R.string.drawerleft_spinnar_projectstructure),
        main.getDrawable(R.drawable.explorers_projectstructure_icon), R.layout.explorers_projectstructure) {

    private var _binding: ExplorersProjectstructureBinding? = null
    private val binding get() = _binding!!

    var currentProject: Project? = null
    var currentParameters: MutableList<*>? = null


    init {
        MainAction.onFileCreated.addObserver {
            refresh()
        }
        MainAction.onFileRenamed.addObserver {
            refresh()
        }
        MainAction.onFileDeleted.addObserver {
            refresh()
        }
    }

    override fun initView(view: View) {
        _binding = ExplorersProjectstructureBinding.bind(view)
        binding.apply {
            rvContent.adapter = treeViewAdapter
            rvContent.layoutManager = LinearLayoutManager(main.activity)
        }

        val collapseMenu = ExplorerMenu(main.getDrawable(R.drawable.explorers_projectstructure_menu_collapseall2), getString(R.string.explorers_projectstructure_menu_collapse),
                {
                    treeViewAdapter.collapseAllExceptRoots()
                }, {
            treeViewAdapter.collapseAll()
        })


        val refreshMenu = ExplorerMenu(main.getDrawable(R.drawable.explorers_projectstructure_menu_refresh), getString(R.string.explorers_projectstructure_menu_refresh)
        ) { v ->
            v.clearAnimation()
            v.rotation = 0F
            v.animate().rotationBy(-3 * 360F).setDuration(900).start()
            refresh()
        }


        menuList.addAll(mutableListOf(
                collapseMenu, refreshMenu
        ))

    }

    val fileListener = object : TreeNodeListener<FileNode, FileNodeBinder.ViewHolder> {
        override fun onClick(node: TreeNode<FileNode>, holder: FileNodeBinder.ViewHolder, adapter: TreeViewAdapter<FileNode, FileNodeBinder.ViewHolder>): Boolean {
            val file = node.content.path
            if (main.fileOpenManager.requestOpen(file.absolutePath)) {
                main.explorerWindow.hideWindow()
            }
            return true
        }

        override fun onLongClick(node: TreeNode<FileNode>, holder: FileNodeBinder.ViewHolder, adapter: TreeViewAdapter<FileNode, FileNodeBinder.ViewHolder>): Boolean {
            val file = node.content.path
            if (file != null) {
                showOperateDialogForFile(file)
            }
            return true
        }


    }
    val dirListener = object : DirNodeListener {

        override fun onLongClick(node: TreeNode<DirNode>, holder: DirNodeBinder.ViewHolder, adapter: TreeViewAdapter<DirNode, DirNodeBinder.ViewHolder>): Boolean {
            val file = node.content.path
            if (file != null) {
                showOperateDialogForFile(file)
            }
            return true
        }
    }
    private val binders = mutableListOf<TreeBinder<out LayoutItemType, out BaseFileViewHolder>>(FileNodeBinder(fileListener), DirNodeBinder(dirListener))
    private val treeViewAdapter = TreeViewAdapter(binders as MutableList<TreeBinder<LayoutItemType, BaseFileViewHolder>>)


    private fun showOperateDialogForFile(file: File) {
        main.fileOperateManager.showOperateDialog(file)
    }

    fun refresh() {
        if (currentProject != null)
            currentProject?.let { loadProjectStructureTree(it) }
        else
            clear()
    }


    private fun loadProjectStructureTreeList(projectList: MutableSet<Project>) {
        val list = getProjectStructureNodeList(projectList)
        main.launchMain {
            treeViewAdapter.refresh(list)
        }
    }

    private fun clear() {
        Handler(Looper.getMainLooper()).post {
            treeViewAdapter.refresh(mutableListOf())
        }
    }

    private fun getSubProjects(list: MutableSet<Project>): MutableSet<Project> {
        for (project in list) {
            list.add(project)
            if (!list.containsAll(project.projects))
                list.addAll(getSubProjects(project.projects))
        }
        return list
    }

    private fun loadProjectStructureTree(project: Project) {
        doAsync {
            val list = getSubProjects(mutableSetOf(project))
            loadProjectStructureTreeList(list)
        }
    }

    private fun FileNode.node(): TreeNode<LayoutItemType> {
        return TreeNode(this)
    }

    private fun DirNode.node(): TreeNode<LayoutItemType> {
        return TreeNode(this)
    }

    private fun getProjectStructureNodeList(projectList: MutableSet<Project>): List<TreeNode<LayoutItemType>> {
        val rootNodeList = ArrayList<TreeNode<LayoutItemType>>()

        val scriptNode = DirNode(main.getString(R.string.project_structure_projectscript), ContextCompat.getDrawable(main.context, R.drawable.explorers_project_structure_projectconfig)).node()
        for (project in projectList) {
            for (plugin in project.plugins) {
                if (plugin.plugin.id() == InnerIdentifier.ProjectPlugin.PROJECT_STRUCTURE) {
                    val structure = getStructureFromParameters(plugin.parameters)
                    scriptNode.addChild(FileNode(
                            ProjectConfigJson.configFileName + "(" + (project.name
                                    ?: project.projectDir.name) + ")", project.configFile).node())
                    val rootNode = DirNode(
                            if (project.name.isNullOrBlank())
                                project.projectDir.name
                            else
                                project.name + "(" + project.projectDir.name + ")", project.projectDir).node()
                    for (folder in structure.folders) {
                        val realFileList = folder.getRealPathFileList(project.projectDir).filter { it.isDirectory }
                        for (realFile in realFileList) {
                            val node = DirNode(
                                    if (folder.alias.isNullOrBlank())
                                        realFile.name
                                    else
                                        folder.alias + "(" + realFile.name + ")", realFile).node()
                            rootNode.addChild(node)
                            addProjectFilesDirNode(node, realFile)
                        }
                    }
                    for (file in structure.files) {
                        val realFile = file.getRealFile(project.projectDir)
                        scriptNode.addChild(FileNode(
                                if (file.alias.isNullOrBlank())
                                    realFile.name
                                else {
                                    file.alias
                                }, project.configFile).node())
                    }
                    rootNodeList.add(rootNode)
                }
            }
        }
        rootNodeList.add(scriptNode)
        rootNodeList.first().expand()

        return rootNodeList
    }

    private fun parseRelativeAliasPaths(file: Any?): RelativeAliasPaths? {
        val folderMap = file as? Map<*, *> ?: return null
        val alias = folderMap["alias"] as? String?
        val pathList = mutableListOf<String>()
        val paths = folderMap["paths"]
        if (paths is String) {
            pathList.add(paths)
        } else if (paths is List<*>) {
            for (path in paths) {
                if (path is String) {
                    pathList.add(path)
                } else {
//                    return null
                }
            }
        }

        return RelativeAliasPaths(alias, pathList.map { RelativePath(alias, it) })
    }

    private fun parseRelativePath(file: Any?): RelativePath? {
        val folderMap = file as? Map<*, *> ?: return null
        val alias = folderMap["alias"] as? String?
        val path = folderMap["path"]
        if (path is String) {
            return RelativePath(alias, path)
        }
        return null
    }

    private fun getStructureFromParameters(parameters: List<*>?): Structure {
        val structure = Structure()
        if (parameters == null) {
            return structure
        }
        if (parameters.isEmpty())
            return structure
        val map = parameters.first() as? Map<*, *> ?: return structure
        (map["files"] as? List<*>)?.let { files ->
            for (file in files) {
                val rp = parseRelativePath(file) ?: continue
                structure.files.add(rp)
            }
        }
        (map["folders"] as? List<*>)?.let { folders ->
            for (folder in folders) {
                val rp = parseRelativeAliasPaths(folder) ?: continue
                structure.folders.add(rp)
            }
        }
        return structure
    }

    class Structure {
        var folders: MutableList<RelativeAliasPaths> = mutableListOf()
        var files: MutableList<RelativePath> = mutableListOf()
    }
}