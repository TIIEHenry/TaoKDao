package taokdao.window.toolpages.builder

import taokdao.api.internal.InnerIdentifier
import taokdao.api.ui.base.PanelProp
import taokdao.api.ui.toolpage.groups.build.IBuildToolGroup
import taokdao.main.IMainView
import taokdao.window.toolpages.treepage.BaseTreeToolGroupFragment
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ToolpagePublicTreeLayoutBinding

class BuildToolGroupFragment(main: IMainView) : BaseTreeToolGroupFragment<ToolpagePublicTreeLayoutBinding>(
    main, PanelProp(main.context, R.string.tabfragment_build, R.drawable.toolpages_build_icon)
), IBuildToolGroup {

    override fun id() = InnerIdentifier.ToolGroup.BUILD
}
