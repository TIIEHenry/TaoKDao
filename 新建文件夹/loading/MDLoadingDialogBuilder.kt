package taokdao.main.business.dialog_manage.loading

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import taokdao.main.business.dialog_manage.base.MaterialBaseDialogBuilder
import taokdao.main.business.dialog_manage.base.NegativeButtonTemp
import taokdao.main.business.dialog_manage.base.NeutralButtonTemp
import taokdao.main.business.dialog_manage.base.PositiveButtonTemp
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.android.ui.dialogs.api.callback.OnShowListener
import tiiehenry.android.ui.dialogs.api.strategy.loading.ILoadingDialogBuilder
import tiiehenry.android.ui.dialogs.api.strategy.loading.ILoadingTask
import tiiehenry.ktx.content.inflate
import tiiehenry.taokdao.R

class MDLoadingDialogBuilder(val context: Context) : MaterialBaseDialogBuilder<ILoadingDialogBuilder>, ILoadingDialogBuilder {
    override val builder: MaterialDialog = MaterialDialog(context)

    override val positiveTemp: PositiveButtonTemp = PositiveButtonTemp()
    override val negativeTemp: NegativeButtonTemp = NegativeButtonTemp()
    override val neutralTemp: NeutralButtonTemp = NeutralButtonTemp()
    private val loadTemp: LoadTemp = LoadTemp()

    override lateinit var dialog: IDialog

    override fun minDisplayTime(delay: Long): ILoadingDialogBuilder {
        loadTemp.minDisplayTime = delay
        return builder()
    }

    override fun showListener(listener: OnShowListener): ILoadingDialogBuilder {
        loadTemp.showListener = listener
        return super.showListener(listener)
    }

    override fun autoDismiss(enable: Boolean): ILoadingDialogBuilder {
        loadTemp.autoDismiss = enable
        return super.autoDismiss(enable)
    }

    override fun autoExecuteTask(auto: Boolean): ILoadingDialogBuilder {
        loadTemp.autoExecute = auto
        return builder()
    }

    override fun addLoadingTask(text: CharSequence, task: ILoadingTask): ILoadingDialogBuilder {
        loadTemp.loadingTaskList.add(text to task)
        return builder()
    }

    override fun addLoadingTask(text: Int, task: ILoadingTask): ILoadingDialogBuilder {
        return addLoadingTask(context.getString(text), task)
    }

    override fun build(): IDialog {
        val layout = context.inflate(R.layout.material_dialog_loading, null)
        builder.customView(view = layout, dialogWrapContent = false)
        dialog = MaterialLoadingDialogWrapper(this, layout, loadTemp)
        return dialog
    }

    override fun builder(): ILoadingDialogBuilder {
        return this
    }

}

