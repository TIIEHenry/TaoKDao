package taokdao.codeeditor.layout.selectoperate

import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.code.editor.client.R

class SelectOperateAdapter : BaseIdRecyclerAdapter<SelectOperate>() {
    override fun getItemLayoutId(viewType: Int) = R.layout.contents_codeeditor_selectoperate_item
    override fun bindData(holder: RecyclerViewHolder, position: Int, item: SelectOperate) {
        holder.getImageButton(R.id.ibtn_button).apply {
            setImageDrawable(item.icon)
            setOnClickListener(item.click)
            setOnLongClickListener(item.longClick)
        }
    }
}