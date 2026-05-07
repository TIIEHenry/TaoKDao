package taokdao.window.toolpages.event

import taokdao.api.main.IMainContext
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class EventAdapter(val main: IMainContext) : BaseIdRecyclerAdapter<EventItem>() {
    private var scale = 1.0f
    private var scaleStep = 0.1f
    override fun getItemLayoutId(viewType: Int) = R.layout.toolpages_event_item

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: EventItem) {
        holder.getTextView(R.id.tv_toolpages_event_item_label).apply {
            text = item.toSpannableString(main)
            textSize = 13f * scale
        }
    }

    fun size() = mData.size

    fun zoomIn() {
        scale += scaleStep
        notifyDataSetChanged()
    }

    fun zoomOut() {
        if (scale > scaleStep) {
            scale -= scaleStep
            notifyDataSetChanged()
        }
    }
}