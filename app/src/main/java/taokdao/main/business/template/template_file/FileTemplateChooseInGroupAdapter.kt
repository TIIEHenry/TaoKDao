package taokdao.main.business.template.template_file

import taokdao.api.data.drawable.IDrawableManager
import taokdao.api.file.template.FileTemplate
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R


class FileTemplateChooseInGroupAdapter(private val drawableManager: IDrawableManager, dataList: MutableList<FileTemplate>) : BaseIdRecyclerAdapter<FileTemplate>(dataList) {
    override fun getItemLayoutId(viewType: Int) = R.layout.file_template_choose_in_group_dialog_rv_item

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: FileTemplate) {
        holder.getImageView(R.id.iv_icon).setImageDrawable(
                drawableManager.getDrawableForSuffix(item.extension)
                        ?: drawableManager.defaultDrawableForSuffix)
        holder.getTextView(R.id.tv_label).text = item.name
        holder.getTextView(R.id.tv_description).text = item.description
    }
}