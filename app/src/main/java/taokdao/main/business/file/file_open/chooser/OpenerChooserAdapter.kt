package taokdao.main.business.file.file_open.chooser

import taokdao.api.file.open.IFileOpener
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class OpenerChooserAdapter(dataList: List<IFileOpener>) : BaseIdRecyclerAdapter<IFileOpener>(dataList) {
    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.project_template_choose_dialog_rv_item
    }

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: IFileOpener) {
        holder.getImageView(R.id.iv_icon).setImageDrawable(item.icon)
        holder.getTextView(R.id.tv_label).text = item.label
        holder.getTextView(R.id.tv_description).text = item.description
    }

}