package taokdao.window.toolpages.treepage

import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import org.jetbrains.anko.find
import taokdao.api.ui.base.PanelProp
import taokdao.api.ui.toolpage.container.tabchoose.TabChooserStyle
import taokdao.api.ui.toolpage.content.tree.TreeItem
import taokdao.api.ui.toolpage.fragment.BaseToolGroupBindingFragment
import taokdao.api.ui.toolpage.internal.IBaseTreeToolGroup
import taokdao.main.IMainView
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.view.treeview.LayoutItemType
import tiiehenry.taokdao.ui.view.treeview.TreeBinder
import tiiehenry.taokdao.ui.view.treeview.TreeNode
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener
import tiiehenry.taokdao.ui.view.treeview.TreeViewAdapter

abstract class BaseTreeToolGroupFragment<VB : ViewBinding>(val main: IMainView, prop: PanelProp)
    : BaseToolGroupBindingFragment<VB,List<TreeItem>>(prop), IBaseTreeToolGroup {

    internal lateinit var recyclerView: RecyclerView

    private val branchListener = object : TreeNodeListener<TreeBranchNode, TreeBinder.ViewHolder> {
        override fun onLongClick(node: TreeNode<TreeBranchNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<TreeBranchNode, TreeBinder.ViewHolder>): Boolean {
//            val item = node.content.treeItem
//            Dialogs.global
//                    .asConfirm()
//                    .title(item.title)
//                    .content(item.hint)
//                    .positiveText()
//                    .show()
            val tipItem = node.content.treeItem
            tipItem.callback?.onAction(tipItem)
            return false
        }

        override fun onClick(node: TreeNode<TreeBranchNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<TreeBranchNode, TreeBinder.ViewHolder>): Boolean {
            holder.getView<ImageView>(R.id.iv_arrow).apply {
                clearAnimation()
                if (!node.isExpand) {
                    rotation = 0F
                    animate().rotationBy(90F).start()
                } else {
                    rotation = 90F
                    animate().rotationBy(-90F).start()
                }
            }
            return false
        }
    }

    private val leafListener = object : TreeNodeListener<TreeLeafNode, TreeBinder.ViewHolder> {
        override fun onClick(node: TreeNode<TreeLeafNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<TreeLeafNode, TreeBinder.ViewHolder>): Boolean {
            val tipItem = node.content.treeItem
            tipItem.callback?.onAction(tipItem)
            return true
        }

        override fun onLongClick(node: TreeNode<TreeLeafNode>, holder: TreeBinder.ViewHolder, adapter: TreeViewAdapter<TreeLeafNode, TreeBinder.ViewHolder>): Boolean {
            val item = node.content.treeItem
            Dialogs.global
                    .asConfirm()
                    .title(item.title)
                    .content(item.message)
                    .positiveText()
                    .show()
            return true
        }
    }

    private val binders = mutableListOf<TreeBinder<out LayoutItemType, out TreeBinder.ViewHolder>>(TreeBranchNode.Binder(branchListener), TreeLeafNode.Binder(leafListener))
    private val recyclerAdapter = TreeViewAdapter(binders as MutableList<TreeBinder<LayoutItemType, TreeBinder.ViewHolder>>)

    internal var dataList: List<TreeItem>? = null
    override fun attachContent(content: List<TreeItem>) {
        dataList = content
        recyclerAdapter.refresh(content2Node(content))
    }

    override fun isContentAttached(content: List<TreeItem>): Boolean {
        return dataList == content
    }

    override fun detachContent(content: List<TreeItem>) {
        dataList = null
        recyclerAdapter.refresh(listOf())
    }

    override fun refreshContent() {
        dataList?.let {
            recyclerAdapter.refresh(content2Node(it))
        }
    }

    private fun content2Node(content: List<TreeItem>): MutableList<TreeNode<LayoutItemType>> {
        val list = mutableListOf<TreeNode<LayoutItemType>>()
        for (treeItem in content) {
            if (treeItem.hasChild()) {
                val node = TreeNode<LayoutItemType>(TreeBranchNode(treeItem)).apply {
                    childList = content2Node(treeItem.children)
                    expand()
                }
                list.add(node)
            } else {
                list.add(TreeNode(TreeLeafNode(treeItem)))
            }
        }
        return list
    }

    override fun initView(binding: VB) {
        recyclerView = binding.root.find<RecyclerView>(R.id.rv_toolpage_public_tree_container).apply {
            adapter = recyclerAdapter
            itemAnimator = DefaultItemAnimator().apply { addDuration = 100 }
            layoutManager = LinearLayoutManager(main.activity, RecyclerView.VERTICAL, false)
        }
    }


    override fun getTabChooserStyle(): TabChooserStyle = TabChooserStyle.SPINNER
}