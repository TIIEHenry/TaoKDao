package taokdao.main.business.window.window_explorer.popup

import android.view.ViewGroup
import taokdao.api.ui.explorer.IExplorer
import tiiehenry.android.view.spinner.adapter.AbstractSpinnerAdapter
import tiiehenry.android.view.spinner.holder.SpinnerViewHolder
import tiiehenry.ktx.inflate
import tiiehenry.ideditor.R

class ExplorerSpinnerAdapter : AbstractSpinnerAdapter<ExplorerSpinnerAdapter, IExplorer>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): SpinnerViewHolder {
        return SpinnerViewHolder(parent.inflate(R.layout.explorer_spinnar_item, null))
    }

    override fun onCreateDropDownViewHolder(parent: ViewGroup, pos: Int): SpinnerViewHolder {
        return onCreateViewHolder(parent, pos)
    }

    override fun bindData(holder: SpinnerViewHolder, item: IExplorer, position: Int) {
        holder.image(R.id.iv_icon, item.icon)
//			setBackgroundResource(R.drawable.main_drawerleft_spinner_icon_bg)
        holder.text(R.id.tv_label, item.label)
//			setTextColor(c.resources.getColor(R.color.toolbar_text))
    }

    override fun bindDropDownData(holder: SpinnerViewHolder, item: IExplorer, position: Int) {
        bindData(holder, item, position)
//			setTextColor(c.getAttrColor(R.attr.symbolBarColor))
    }

    override fun getInstance(): ExplorerSpinnerAdapter {
        return this
    }

}