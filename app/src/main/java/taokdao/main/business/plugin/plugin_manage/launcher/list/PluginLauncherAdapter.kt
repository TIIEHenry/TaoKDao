package taokdao.main.business.plugin.plugin_manage.launcher.list

import androidx.core.content.ContextCompat
import taokdao.api.plugin.load.IPluginLoader
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.PluginManageLauncherListItemListBinding

class PluginLauncherAdapter(private val pluginLoader: IPluginLoader) :
    BaseIdRecyclerAdapter<PluginLauncherItem>() {
    override fun getItemLayoutId(viewType: Int) = R.layout.plugin_manage_launcher_list_item_gird
    override fun bindData(holder: RecyclerViewHolder, position: Int, item: PluginLauncherItem) {
        val binding = holder.bindingOf<PluginManageLauncherListItemListBinding>()
        val icon = item.information.icon ?: item.plugin?.let { pluginLoader.loadPluginIcon(it) }
        binding.ivIcon.setImageDrawable(
            icon
                ?: ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.plugin_manage_plugin_default_icon
                )
        )
        binding.tvLabel.text = item.information.label
    }
}