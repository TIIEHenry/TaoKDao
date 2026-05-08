package taokdao.window.toolpages

import taokdao.main.IMainView
import taokdao.window.toolpages.builder.BuildToolGroupFragment
import taokdao.window.toolpages.event.EventToolPageFragment
import taokdao.window.toolpages.logcat.LogcatToolPageFragment
import taokdao.window.toolpages.search.SearchToolPageFragment
import taokdao.window.toolpages.tips.TipsToolGroupFragment

class TabToolInternal(val main: IMainView) {

    lateinit var eventTabToolFragment: EventToolPageFragment
    lateinit var buildTabFragment: BuildToolGroupFragment
    lateinit var errorTabFragment: TipsToolGroupFragment
    lateinit var searchTabFragment: SearchToolPageFragment
    lateinit var logcatTabFragment: LogcatToolPageFragment

    fun init() {
        eventTabToolFragment = EventToolPageFragment(main)
        buildTabFragment = BuildToolGroupFragment(main)
        errorTabFragment = TipsToolGroupFragment(main)
        searchTabFragment = SearchToolPageFragment(main)
        logcatTabFragment = LogcatToolPageFragment(main)
    }
}