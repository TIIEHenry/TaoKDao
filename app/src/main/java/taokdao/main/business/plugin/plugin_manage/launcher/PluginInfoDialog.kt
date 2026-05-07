package taokdao.main.business.plugin.plugin_manage.launcher

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import tiiehenry.ideditor.R
import taokdao.api.plugin.bean.Plugin
import taokdao.main.IMainView
import tiiehenry.ideditor.databinding.PluginManageDialogModuleinfoBinding
import tiiehenry.taokdao.activity.MarkedActivity
import java.io.File


@SuppressLint("ViewConstructor")
class PluginInfoDialog(val main: IMainView, val plugin: Plugin) : View.OnClickListener {
    val binding = PluginManageDialogModuleinfoBinding.inflate(main.layoutInflater)
    val layout = binding.apply {
        val information = plugin.getInformation(main)
        val icon = information.icon ?: main.pluginLoader.loadPluginIcon(plugin)
        binding.ivInfoIcon.setImageDrawable(icon)
        binding.tvDescription.text = information.description
        binding.tvDescription.setOnClickListener(this@PluginInfoDialog)
        binding.tvAuthor.text = information.author
        binding.tvAuthor.setOnClickListener(this@PluginInfoDialog)
        binding.tvPluginId.text = plugin.id
        binding.tvPluginId.setOnClickListener(this@PluginInfoDialog)
        binding.tvVersion.text = "${plugin.version.code} (${plugin.version.name})"
        binding.tvVersion.setOnClickListener(this@PluginInfoDialog)
        binding.btnReadme.setOnClickListener(this@PluginInfoDialog)
    }


    private fun getREADME(plugin: Plugin): File? {
        val information = plugin.getInformation(main)
        val file = File(plugin.pluginDir, information.readme)
        if (file.exists())
            return file
        return null
    }

    override fun onClick(v: View) {
        when (v) {
            binding.tvDescription, binding.tvAuthor, binding.tvPluginId, binding.tvVersion, binding.tvVersionOld -> {
                val cb = v.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("", (v as TextView).text)
                cb.setPrimaryClip(clip)
                main.notify(R.string.copy_success)
            }

            binding.btnReadme -> {
                val readme = getREADME(plugin)
                if (readme != null)
                    main.startActivity(Intent(main.activity, MarkedActivity::class.java).apply {
                        putExtra(MarkedActivity.EXTRA_KEY_PATH, readme.absolutePath)
                    })
                else
                    main.notify(R.string.business_plugin_manage_nomdfile)
            }
        }
    }
}