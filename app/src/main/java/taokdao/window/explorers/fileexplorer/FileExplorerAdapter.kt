package taokdao.window.explorers.fileexplorer

import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R

class FileExplorerAdapter : BaseIdRecyclerAdapter<FileExplorerItem>() {

    override fun getItemLayoutId(viewType: Int): Int = R.layout.explorers_fileexplorer_item

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: FileExplorerItem) {
        holder.text(R.id.tv_label, item.label)
        holder.image(R.id.iv_icon, item.icon)
    }

}