package taokdao.main.business.file.file_operate.chooser

import taokdao.api.file.operate.IFileOperator
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class FileOperatorChooserAdapter(dataList: List<IFileOperator>) : BaseIdRecyclerAdapter<IFileOperator>(dataList) {
    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.main_file_operate_chooser_item
    }

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: IFileOperator) {
        holder.getImageView(R.id.iv_icon).setImageDrawable(item.icon)
        holder.getTextView(R.id.tv_label).text = item.label
    }

}