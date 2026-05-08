package taokdao.main.business.window.window_toolpage

import taokdao.api.internal.InnerIdentifier
import taokdao.api.ui.toolpage.groups.build.IBuildToolGroup
import taokdao.api.ui.toolpage.groups.search.ISearchToolGroup
import taokdao.api.ui.toolpage.groups.tip.ITipToolGroup
import taokdao.api.ui.toolpage.internal.IInternalToolPageManager
import taokdao.window.toolpages.builder.BuildToolGroupFragment
import taokdao.window.toolpages.search.SearchToolPageFragment
import taokdao.window.toolpages.tips.TipsToolGroupFragment

class InternalToolPageManager(private val presenter: ToolPageWindowPresenter) :
    IInternalToolPageManager {
    override fun getOrCreateTipGroup(): ITipToolGroup {
        val toolPage = presenter.get(InnerIdentifier.ToolGroup.TIPS)
        if (toolPage != null) {
            return toolPage as ITipToolGroup
        }
        val toolGroup = TipsToolGroupFragment(presenter.view)
        presenter.add(toolGroup)
        return toolGroup
    }

    override fun getOrCreateBuildGroup(): IBuildToolGroup {
        val toolPage = presenter.get(InnerIdentifier.ToolGroup.BUILD)
        if (toolPage != null) {
            return toolPage as IBuildToolGroup
        }
        val toolGroup = BuildToolGroupFragment(presenter.view)
        presenter.add(toolGroup)
        return toolGroup
    }

    override fun getOrCreateSearchGroup(): ISearchToolGroup {
        val toolPage = presenter.get(InnerIdentifier.ToolGroup.SEARCH)
        if (toolPage != null) {
            return toolPage as ISearchToolGroup
        }
        val toolGroup = SearchToolPageFragment(presenter.view)
        presenter.add(toolGroup)
        return toolGroup
    }
}