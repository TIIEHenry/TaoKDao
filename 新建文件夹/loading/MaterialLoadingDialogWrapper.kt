package taokdao.main.business.dialog_manage.loading

import android.view.View
import com.lxj.androidktx.core.runOnUIThread
import org.jetbrains.anko.doAsync
import tiiehenry.ideditor.databinding.MaterialDialogLoadingBinding
import taokdao.main.business.dialog_manage.base.MaterialDialogBaseWrapper
import tiiehenry.android.ui.dialogs.api.strategy.loading.ILoadingDialog
import tiiehenry.android.ui.dialogs.api.strategy.loading.OnLoadingException
import tiiehenry.taokdao.ui.dialog.applyTheme
import kotlin.math.max

class MaterialLoadingDialogWrapper(builder: MDLoadingDialogBuilder, val layout: View, val loadTemp: LoadTemp) : MaterialDialogBaseWrapper(builder.builder.applyTheme()), ILoadingDialog {

    private val binding = MaterialDialogLoadingBinding.bind(layout)

    override fun setLoadingText(text: CharSequence?) {
        runOnUIThread {
            binding.tvLoadingText.text = text
        }
    }

    override fun executeLoadingTask() {
        try {
            doAsync {
                val oldTime = System.currentTimeMillis()
                for (taskItem in loadTemp.loadingTaskList) {
                    setLoadingText(taskItem.first)
//                    Thread.sleep(10000)
                    taskItem.second.onLoading(this@MaterialLoadingDialogWrapper)
                }
                val newTime = System.currentTimeMillis()
//                loge((builder._minDisplayTime - (newTime - oldTime)).toString())
                Thread.sleep(max(loadTemp.minDisplayTime - (newTime - oldTime), 0))
                if (loadTemp.autoDismiss) {
                    dialog.dismiss()
                }
            }
        } catch (e: OnLoadingException) {
            setLoadingText(e.message)
            dialog.cancelable(true)
        } catch (e: Exception) {
            e.printStackTrace()
            setLoadingText(e.message)
            dialog.cancelable(true)
        }
    }

    override fun show() {
        super.show()
        dialog.setOnShowListener {
            if (loadTemp.autoExecute) {
                executeLoadingTask()
            }
            loadTemp.showListener?.onShow(this)
        }
    }
}