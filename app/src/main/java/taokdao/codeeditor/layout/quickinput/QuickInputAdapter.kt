package taokdao.codeeditor.layout.quickinput

import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R


class QuickInputAdapter : BaseIdRecyclerAdapter<String>() {
    override fun getItemLayoutId(viewType: Int) = R.layout.contents_codeeditor_quickinput_item
    override fun bindData(holder: RecyclerViewHolder, position: Int, item: String) {
        holder.text(R.id.tv_quickinput_text, item)
    }
}