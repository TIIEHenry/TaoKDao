package taokdao.main.business.template.template_project

import taokdao.api.project.template.IProjectTemplate
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class ProjectTemplateChooserAdapter(dataList: List<IProjectTemplate>) : BaseIdRecyclerAdapter<IProjectTemplate>(dataList) {
    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.project_template_choose_dialog_rv_item
    }

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: IProjectTemplate) {
        holder.getImageView(R.id.iv_icon).setImageDrawable(item.icon)
        holder.getTextView(R.id.tv_label).text = item.label
        holder.getTextView(R.id.tv_description).text = item.description
    }

}