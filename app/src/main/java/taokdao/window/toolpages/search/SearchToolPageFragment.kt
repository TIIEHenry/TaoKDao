package taokdao.window.toolpages.search

import android.view.View
import taokdao.api.internal.InnerIdentifier
import taokdao.api.ui.base.PanelProp
import taokdao.api.ui.toolpage.groups.search.ISearchToolGroup
import taokdao.main.IMainView
import taokdao.window.toolpages.treepage.BaseTreeToolGroupFragment
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ToolpagePublicTreeLayoutBinding

class SearchToolPageFragment(main: IMainView) : BaseTreeToolGroupFragment<ToolpagePublicTreeLayoutBinding>(
        main, PanelProp(main.context, R.string.tabfragment_search,
        R.drawable.toolpages_search_icon)), ISearchToolGroup {

    override fun id() = InnerIdentifier.ToolGroup.SEARCH

//
//        searchAdapter.setOnItemClickListener { _, data, _ ->
//            if (!main.fileOpenManager.isFileOpened(data.file)) {
//                main.fileOpenManager.requestOpen(data.file)
//            }
//            main.contentManager.show(data.file)
//            if (main.contentManager.current?.path == data.file) {
//                if (data.line == null) {
//                    //文件
////						main.veditor.requestFocus()
////						main.airPanelOnStateChangedListener.onSoftKeyboardStateChanged(true)
//                    Windows.TABTOOL.window.showWindow()
//                } else {
////                    main.tabContentManager.currentEditor?.lineEditor?.gotoLine(data.line)
//                    main.contentManager.current?.editor?.selector?.selection = data.selection
//                }
//            }
//        }
//        searchAdapter.setOnItemLongClickListener { _, data, _ ->
//            if (main.contentManager.current?.path == data.file) {
//                if (data.line == null) {
//                    main.contentManager.show(data.file)
//                } else {
////						main.veditor.startActionMode()
//                }
//            }
//        }

//        searchAdapter.dataList = arrayListOf(
//				SearchData(DrawableManager.getFromSuffix(main, "lua"), "new.lua", null, "/storage/emulated/0/AndroLIDE/new.lua"),
//				SearchData(ColorDrawable(1), "ublic voi", 2, "/storage/emulated/0/AndroLIDE/new.lua", 2, 9)
//        )

}