package taokdao.main.business.build_manage.buildoptionpopup

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import taokdao.api.builder.IBuildOption
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class BuildOptionMenuAdapter<I> : BaseIdRecyclerAdapter<Pair<IBuildOption<I>, I>>() {
    override fun getItemLayoutId(viewType: Int) = R.layout.toolbar_buildoption_item
    override fun bindData(holder: RecyclerViewHolder, position: Int, item: Pair<IBuildOption<I>, I>) {
        holder.findViewById<ImageView>(R.id.iv_icon).apply {
            setImageDrawable(item.first.icon)
            visibility = if (item.first.icon != null) View.VISIBLE else View.GONE
        }
        holder.findViewById<TextView>(R.id.tv_name).text = item.first.label
    }
}