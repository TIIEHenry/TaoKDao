package taokdao.main.business.plugin.plugin_load

import android.graphics.drawable.Drawable
import tiiehenry.ideditor.R

class PluginLoadViewWrapper(val view: PluginLoadContract.V, override val presenter: PluginLoadPresenter) : PluginLoadContract.VW {

    override fun getDefaultPluginIcon(): Drawable {
        return view.getDrawable(R.drawable.plugin_manage_plugin_default_icon)
    }

}
