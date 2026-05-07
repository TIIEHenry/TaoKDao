package taokdao.main.business.session_control

import android.view.View
import taokdao.content.openfiled.OpenFailedContent
import taokdao.main.business.file.file_open.opener.FileChooserOpener
import tiiehenry.ideditor.R

interface SessionControlView : SessionControlContract.V {
    override fun tryOpenByOpener(path: String, openId: String) {
        launchMain {
            val handled = fileOpenManager.requestOpenByOpenerId(path, openId)
            if (handled) {
                return@launchMain
            }
            val defaultFileOpener = FileChooserOpener(this@SessionControlView, path)
            contentManager.add(
                    OpenFailedContent(this@SessionControlView, context.getString(R.string.business_session_control_filenotexists), path, View.OnClickListener {
                        contentManager.closeCurrent()
                        defaultFileOpener.click()
                    }).apply {
                        opener = defaultFileOpener.id()
                    }, true
            )
        }
    }
}