package taokdao.window.explorers.projectfiles

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.doAsync
import taokdao.api.data.bean.Properties
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.action.MainAction
import taokdao.api.project.bean.Project
import taokdao.api.ui.explorer.menu.ExplorerMenu
import taokdao.api.ui.explorer.wrapped.ExplorerFragment
import taokdao.main.IMainView
import taokdao.main.business.window.window_explorer.popup.tree.BaseFileViewHolder
import taokdao.main.business.window.window_explorer.popup.tree.DirNode
import taokdao.main.business.window.window_explorer.popup.tree.DirNodeBinder
import taokdao.main.business.window.window_explorer.popup.tree.DirNodeListener
import taokdao.main.business.window.window_explorer.popup.tree.FileNode
import taokdao.main.business.window.window_explorer.popup.tree.FileNodeBinder
import taokdao.main.business.window.window_explorer.popup.tree.addProjectFilesDirNode
import tiiehenry.android.ui.dialogs.mddialogs.runOnUIThread
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ExplorersProjectfilesBinding
import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeBinder
import tiiehenry.taokdao.ui.view.treeview.TreeNode
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener
import tiiehenry.taokdao.ui.view.treeview.TreeViewAdapter
import java.io.File

class ProjectFiles(val main: IMainView) : ExplorerFragment(
    Properties(
        InnerIdentifier.Explorer.PROJECT_FILES,
        main.context,
        R.string.drawerleft_spinnar_projectfiles
    ),
    main.getDrawable(R.drawable.explorers_projectfiles_icon),
    R.layout.explorers_projectfiles
) {

    var currentProject: Project? = null


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
        val binding = ExplorersProjectfilesBinding.bind(view)
        view.apply {
            binding.rvContent.adapter = treeViewAdapter
            binding.rvContent.layoutManager = LinearLayoutManager(main.activity)
        }

        val collapseMenu =
            ExplorerMenu(main.getDrawable(R.drawable.explorers_projectfiles_menu_collapseall3),
                getString(R.string.explorers_projectfiles_menu_collapse),
                {
//                    Log.e(javaClass.simpleName, "initView: " + "collapse")
                    treeViewAdapter.collapseAllExceptRoots()
                },
                {
                    treeViewAdapter.collapseAll()
                }
            )


        val refreshMenu = ExplorerMenu(
            main.getDrawable(R.drawable.explorers_projectfiles_menu_refresh),
            getString(R.string.explorers_projectfiles_menu_refresh)
        ) { v ->
            v.clearAnimation()
            v.rotation = 0F
            v.animate().rotationBy(-3 * 360F).setDuration(900).start()
            refresh()
        }
        menuList.addAll(
            mutableListOf(
                collapseMenu, refreshMenu
            )
        )
    }


    private val fileListener = object : TreeNodeListener<FileNode, FileNodeBinder.ViewHolder> {
        override fun onClick(
            node: TreeNode<FileNode>,
            holder: FileNodeBinder.ViewHolder,
            adapter: TreeViewAdapter<FileNode, FileNodeBinder.ViewHolder>
        ): Boolean {
            val file = node.content.path
            if (main.fileOpenManager.requestOpen(file.absolutePath)) {
                main.explorerWindow.hideWindow()
            }
            return true
        }

        override fun onLongClick(
            node: TreeNode<FileNode>,
            holder: FileNodeBinder.ViewHolder,
            adapter: TreeViewAdapter<FileNode, FileNodeBinder.ViewHolder>
        ): Boolean {
            val file = node.content.path
            if (file != null) {
                showOperateDialogForFile(file)
            }
            return true
        }


    }
    private val dirListener = object : DirNodeListener {


        override fun onLongClick(
            node: TreeNode<DirNode>,
            holder: DirNodeBinder.ViewHolder,
            adapter: TreeViewAdapter<DirNode, DirNodeBinder.ViewHolder>
        ): Boolean {
            val file = node.content.path
            if (file != null) {
                showOperateDialogForFile(file)
            }
            return true
        }


    }

    private val binders = mutableListOf(FileNodeBinder(fileListener), DirNodeBinder(dirListener))
    private val treeViewAdapter =
        TreeViewAdapter<LayoutItemType, BaseFileViewHolder>(binders as MutableList<TreeBinder<LayoutItemType, BaseFileViewHolder>>).apply {

        }

    private fun showOperateDialogForFile(file: File) {
        main.fileOperateManager.showOperateDialog(file)
    }

    fun refresh() {
        if (currentProject != null)
            currentProject?.let { loadProjectFilesTree(it) }
        else
            clear()
    }

    private fun clear() {
        runOnUIThread {
            treeViewAdapter.refresh(mutableListOf())
        }
    }

    private fun getAllProjects(list: MutableSet<Project>): MutableSet<Project> {
        for (project in list) {
            list.add(project)
            if (!list.containsAll(project.projects))
                list.addAll(getAllProjects(project.projects))
        }
        return list
    }

    private fun loadProjectFilesTreeList(projectList: MutableSet<Project>) {
        main.activity.runOnUiThread {
            treeViewAdapter.refresh(getProjectFilesDirNodeList(projectList))
        }
    }

    private fun loadProjectFilesTree(project: Project) {
        doAsync {
            val list = getAllProjects(mutableSetOf(project))
            loadProjectFilesTreeList(list)
        }
    }

    private fun getProjectFilesDirNodeList(projectSet: MutableSet<Project>): List<TreeNode<LayoutItemType>> {
        val rootNodeList = ArrayList<TreeNode<LayoutItemType>>()
        projectSet.forEach {
            val rootNode = TreeNode<LayoutItemType>(
                DirNode(
                    if (it.name.isNullOrBlank())
                        it.projectDir.name
                    else
                        it.name + "(" + it.projectDir.name + ")", it.projectDir
                )
            )
            rootNodeList.add(rootNode)
            addProjectFilesDirNode(rootNode, it.projectDir)
        }
        rootNodeList.first().expand()
        return rootNodeList
    }


}