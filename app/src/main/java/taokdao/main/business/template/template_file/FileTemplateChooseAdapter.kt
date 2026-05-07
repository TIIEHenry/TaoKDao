package taokdao.main.business.template.template_file

import taokdao.api.data.drawable.IDrawableManager
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class FileTemplateChooseAdapter(private val drawableManager: IDrawableManager, dataList: MutableList<FileTemplateItem>) : BaseIdRecyclerAdapter<FileTemplateItem>(dataList) {
    override fun getItemLayoutId(viewType: Int) = R.layout.file_template_choose_dialog_rv_item

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: FileTemplateItem) {
        holder.getImageView(R.id.iv_icon).setImageDrawable(
                drawableManager.getDrawableForSuffix(item.templateList.first().extension)
                        ?: drawableManager.defaultDrawableForSuffix)
        holder.getTextView(R.id.tv_label).text = item.name
//        holder.getTextView(R.id.descript_tv).text = item.templateList.first().description
    }
}