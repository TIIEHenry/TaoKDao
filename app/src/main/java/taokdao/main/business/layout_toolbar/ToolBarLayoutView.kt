package taokdao.main.business.layout_toolbar

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taokdao.main.toolbar.ToolbarMenu
import taokdao.main.toolbar.ToolbarMenuAdapter
import tiiehenry.ideditor.R


interface ToolBarLayoutView : ToolBarLayoutContract.V {
    override fun initQuickMenu(quickMenuAdapter: QuickMenuAdapter) {
        main.binding.mainToolbarQuickmenuRv.apply {
            adapter = quickMenuAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//            GravitySnapHelper(Gravity.END).attachToRecyclerView(this)
        }
    }

    override fun initToolbarMenu(): ToolbarMenuAdapter {
        val menuAdapter = ToolbarMenuAdapter()

        main.binding.mainToolbarNavigatorIv.setOnClickListener {
            explorerWindow.showWindow()
        }

        main.binding.mainToolbarMenuRv.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//            GravitySnapHelper(Gravity.END).attachToRecyclerView(this)
        }
        return menuAdapter
    }

    override fun addDefaultMenuList(adapter: ToolbarMenuAdapter) {
        val menuList = mutableListOf(
                ToolbarMenu(main, R.drawable.toolbar_menus_save, View.OnClickListener {
                    contentManager.saveCurrentAsync()
                }, View.OnClickListener {
                    contentManager.saveAllAsync()
                }),
                ToolbarMenu(main, R.drawable.toolbar_menus_play, View.OnClickListener {
                    if (projectManager.project != null) {
                        buildManager.buildProject(true)
                    } else {
                        buildManager.buildFile(true)
                    }
                }, View.OnClickListener {
                    if (projectManager.project != null) {
                        buildManager.buildProject(false)
                    } else {
                        buildManager.buildFile(false)
                    }
                }),
//                ToolbarMenu(main, R.drawable.toolbar_menu_undo, View.OnClickListener {
//                    contentManager.current?.editor?.undoManager?.undo()
//                }, View.OnClickListener {
//                    contentManager.current?.editor?.undoManager?.undoMore()
//                }),
//                ToolbarMenu(main, R.drawable.toolbar_menu_redo, View.OnClickListener {
//                    contentManager.current?.editor?.undoManager?.redo()
//                }, View.OnClickListener {
//                    contentManager.current?.editor?.undoManager?.redoMore()
//                }),
                ToolbarMenu(main, R.drawable.toolbar_menu_menu, View.OnClickListener {
                    main.showCategoryMenu()
                }, View.OnClickListener {
                })
        )
        adapter.refresh(menuList)
    }
}