package taokdao.main.business.plugin.plugin_manage.launcher.flowtag

import android.view.View
import taokdao.main.IMainView
import taokdao.main.business.plugin.plugin_manage.launcher.flowtag.adapter.BaseTagAdapter
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.PluginManageLauncherFlowtagItemBinding

class PluginLauncherTagAdapter(main: IMainView) :
    BaseTagAdapter<String, TagViewHolder>(main.context) {
    override fun getLayoutId(): Int = R.layout.plugin_manage_launcher_flowtag_item

    override fun newViewHolder(convertView: View): TagViewHolder {
        val binding = PluginManageLauncherFlowtagItemBinding.bind(convertView)
        return TagViewHolder(binding.tvLabel)
    }

    override fun convert(holder: TagViewHolder, item: String, position: Int) {
        holder.textView.text = item
    }
}