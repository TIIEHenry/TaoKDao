package taokdao.content.guider

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import taokdao.content.guider.tree.CategoryNode
import taokdao.content.guider.tree.EntryNode
import taokdao.main.MainActivity
import taokdao.main.business.file.file_operate.operators.CreateFromTemplate
import taokdao.main.business.file.file_operate.operators.NewFile
import taokdao.main.business.menu_catagory.defaultmenus.CategoryDefaultMenuFile
import taokdao.main.business.menu_catagory.defaultmenus.CategoryDefaultMenuProject
import taokdao.main.business.menu_catagory.defaultmenus.CategoryDefaultMenuSetting
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ContentsGuiderBinding
import tiiehenry.taokdao.ui.view.treeview.*

class GuiderContent(val main: MainActivity, val layout: ContentsGuiderBinding = ContentsGuiderBinding.inflate(main.layoutInflater)) {

    private val categoryListener = object : TreeNodeListener<CategoryNode, TreeBinder.ViewHolder> {
        override fun onClick(node: TreeNode<CategoryNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<CategoryNode, TreeBinder.ViewHolder>): Boolean {
            return false
        }

        override fun onLongClick(node: TreeNode<CategoryNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<CategoryNode, TreeBinder.ViewHolder>): Boolean {
            return false
        }
    }

    private val entryListener = object : TreeNodeListener<EntryNode, TreeBinder.ViewHolder> {
        override fun onClick(node: TreeNode<EntryNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<EntryNode, TreeBinder.ViewHolder>): Boolean {
            node.content.click.run()
            return false
        }

        override fun onLongClick(node: TreeNode<EntryNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<EntryNode, TreeBinder.ViewHolder>): Boolean {
            return false
        }
    }

    private val binders = mutableListOf<TreeBinder<out LayoutItemType, out TreeBinder.ViewHolder>>(CategoryNode.Binder(categoryListener), EntryNode.Binder(entryListener))
    private val adapter = TreeViewAdapter(binders as MutableList<TreeBinder<LayoutItemType, TreeBinder.ViewHolder>>)

    fun init() {
        layout.recyclerv.adapter = adapter
        layout.recyclerv.layoutManager = LinearLayoutManager(main.context)
        val rootNodeList = ArrayList<TreeNode<out LayoutItemType>>()
        rootNodeList.add(TreeNode<LayoutItemType>(CategoryNode(main.getString(R.string.contents_guider_file))).apply {
            val newFile = NewFile(main)
            addChild(TreeNode(EntryNode(newFile.label, Runnable {
                val path = main.fileSystem.projectDir.absolutePath
                newFile.call(main, path)
            })))
            val categoryDefaultMenuFile = CategoryDefaultMenuFile(main)
            addChild(TreeNode(EntryNode(categoryDefaultMenuFile.openFile.label, Runnable {
                categoryDefaultMenuFile.openFile.callback.onAction(main, null)
            })))
            val createFromTemplate = CreateFromTemplate(main)
            addChild(TreeNode(EntryNode(createFromTemplate.label, Runnable {
                val path = main.fileSystem.projectDir.absolutePath
                createFromTemplate.call(main, path)
            })))
        })
        val categoryDefaultMenuProject = CategoryDefaultMenuProject(main)
        rootNodeList.add(TreeNode<LayoutItemType>(CategoryNode(main.getString(R.string.contents_guider_project))).apply {
            addChild(TreeNode(EntryNode(categoryDefaultMenuProject.newProject.label, Runnable {
                categoryDefaultMenuProject.newProject.callback.onAction(main, null)
            })))
            addChild(TreeNode(EntryNode(main.getString(R.string.contents_guider_project_openproject), Runnable {
                main.explorerWindow.showWindow()
            })))
        })
        rootNodeList.add(TreeNode<LayoutItemType>(CategoryNode(main.getString(R.string.contents_guider_help))).apply {
            val categoryDefaultMenuSetting = CategoryDefaultMenuSetting(main)
            addChild(TreeNode(EntryNode(categoryDefaultMenuSetting.appSetting.label, Runnable {
                categoryDefaultMenuSetting.appSetting.callback.onAction(main, null)
            })))
            addChild(TreeNode(EntryNode(categoryDefaultMenuSetting.appIntroduce.label, Runnable {
                categoryDefaultMenuSetting.appIntroduce.callback.onAction(main, null)
            })))
            addChild(TreeNode(EntryNode(categoryDefaultMenuSetting.law.label, Runnable {
                categoryDefaultMenuSetting.law.callback.onAction(main, null)
            })))
        })
        adapter.refresh(rootNodeList as List<TreeNode<LayoutItemType>>)
        adapter.expandAll()
//        layout.apply {
//
//            btn_open_exists.setOnClickListener {
//                main.explorerWindow.showWindow()
//            }
//            btn_create_from_template.setOnClickListener {
//                main.projectTemplateGenerator.showChooseDialog(main.fileSystem.projectDir)
//            }
//        }
    }
}