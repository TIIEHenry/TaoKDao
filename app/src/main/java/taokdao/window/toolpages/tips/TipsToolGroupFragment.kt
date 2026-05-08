package taokdao.window.toolpages.tips

import taokdao.api.internal.InnerIdentifier
import taokdao.api.ui.base.PanelProp
import taokdao.api.ui.toolpage.groups.tip.ITipToolGroup
import taokdao.main.IMainView
import taokdao.window.toolpages.treepage.BaseTreeToolGroupFragment
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ToolpagePublicTreeLayoutBinding

class TipsToolGroupFragment(main: IMainView) :
    BaseTreeToolGroupFragment<ToolpagePublicTreeLayoutBinding>(
        main, PanelProp(
            main.context, R.string.tabfragment_tips,
            R.drawable.toolpages_tips_icon
        )
    ), ITipToolGroup {

    override fun id() = InnerIdentifier.ToolGroup.TIPS


//        tipsAdapter.setOnItemClickListener { _, data, _ ->
//            if (!main.fileOpenManager.isFileOpened(data.file)) {
//                main.fileOpenManager.requestOpen(data.file)
//            }
//            main.contentManager.show(data.file)
//            if (main.contentManager.current?.path == data.file) {
//                if (data.line == null) {
//                    //文件
////						main.veditor.requestFocus()
////						main.airPanelOnStateChangedListener.onSoftKeyboardStateChanged(true)
//                    Windows.TABTOOL.window.hideWindow()
//                } else {
////                    main.tabContentManager.currentEditor?.lineEditor?.gotoLine(data.line)
//                    main.contentManager.current?.editor?.selector?.selection = data.selection
//                }
//            }
//        }
//        tipsAdapter.setOnItemLongClickListener { _, data, _ ->
//            if (main.contentManager.current?.path == data.file) {
//                if (data.line == null) {
//                    main.contentManager.show(data.file)
//                } else {
////						main.veditor.startActionMode()
//                }
//            }
//        }
//		errorAdapter.dataList = arrayListOf(
//				ErrorData(DrawableManager.getFromSuffix(main, "lua"), "new.lua", null, "/storage/emulated/0/AndroLIDE/new.lua"),
//				ErrorData(ColorDrawable(1), "aapt: No resource identifier found for attribute 'appComponentFactory' in package 'android'", 2, "/storage/emulated/0/AndroLIDE/new.lua", 2, 9)
//		)

}