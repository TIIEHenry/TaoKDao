package taokdao.window.toolpages.event

import taokdao.api.event.tag.IEventTag
import taokdao.api.event.tags.EventTag
import taokdao.api.main.IMainContext
import taokdao.api.ui.toolpage.IToolPage
import tiiehenry.ideditor.R

class EventTypes {
    companion object {
        val DEFAULT = EventTag("default")
        val MAIN = EventTag("main")
        val INFO = EventTag("info")
        val WARN = EventTag("warn")
        val ERROR = EventTag("error")
        val CONTENT = EventTag("content")
        val EXPLORER = EventTag("explorer")
        val PLUGIN = EventTag("plugin")
        val PROJECT_PLUGIN = EventTag("project_plugin")

        val PROGRESSBAR_ADD = EventTag("progressbar_add")
        val PROGRESSBAR_REMOVE = EventTag("progressbar_remove")

        class TABTOOL(val tabtool: IToolPage) : IEventTag {
            override fun getTag(main: IMainContext): String {
                return main.getString(R.string.toolpages_event_label) + "(" + tabtool.id() + ")"
            }
        }
    }

}