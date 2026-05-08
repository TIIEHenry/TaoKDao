package taokdao.window.toolpages.event

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taokdao.api.data.bean.Properties
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.IMainContext
import taokdao.api.ui.base.IPanelProp
import taokdao.api.ui.base.PanelProp
import taokdao.api.ui.toolpage.fragment.ToolPageBindingFragment
import taokdao.api.ui.toolpage.menu.ToolPageMenu
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ktx.content.getWidth
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ToolpagesEventBinding


class EventToolPageFragment(val main: IMainContext) : ToolPageBindingFragment<ToolpagesEventBinding>(
    PanelProp(main.context, R.string.toolpages_event_label, R.drawable.toolpages_event_icon)) {

    override fun id() = InnerIdentifier.ToolGroup.EVENT
    init {
        menuList.add(ToolPageMenu(main.getDrawable(R.drawable.toolpage_public_menu_clear), main.getString(R.string.toolpages_event_menu_clear), View.OnClickListener {
            clearEvent()
        }).apply {
            //                        this.showLabel=false
        })

        menuList.add(ToolPageMenu(main.getDrawable(R.drawable.toolpage_public_menu_to_bottom), main.getString(R.string.toolpages_event_menu_tobottom), View.OnClickListener {
            toBottom()
        }).apply {
            this.showLabel = false
        })
    }

    private val eventAdapter: EventAdapter = EventAdapter(main)
    private lateinit var rv: RecyclerView

    override fun initView(binding: ToolpagesEventBinding) {
        rv=binding.rvToolpagesEvent.apply {
            minimumWidth = main.activity.getWidth()
            adapter = eventAdapter
            itemAnimator = DefaultItemAnimator().apply { addDuration = 100 }
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
        binding.toolpagePublicZoomControls.ivZoomIn.setOnClickListener { eventAdapter.zoomIn() }
        binding.toolpagePublicZoomControls.ivZoomOut.setOnClickListener { eventAdapter.zoomOut() }
        eventAdapter.setOnItemClickListener { _, item, _ ->
            Dialogs.global
                    .asConfirm()
                    .title(item.tag)
                    .content(item.message)
                    .negativeText(R.string.copy_text)
                    .onNegative { _ ->
                        copyItem(item)
                    }
                    .positiveText()
                    .show()
        }
        eventAdapter.setOnItemLongClickListener { _, item, _ ->
            copyItem(item)
        }
    }

    private fun copyItem(item: EventItem) {
        val cm = main.activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("EventTabToolFragment", item.toString())
        cm.setPrimaryClip(clip)
        main.notify(R.string.copy_success)
    }

    private fun clearEvent() {
        eventAdapter.clear()
    }

    private fun toBottom() {
        rv.stopScroll()
        (rv.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(eventAdapter.size() - 1, 0)
    }

    fun addEvent(event: EventItem) {
        eventAdapter.add(event)
    }
}