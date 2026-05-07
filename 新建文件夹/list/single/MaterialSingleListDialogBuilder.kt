package taokdao.main.business.dialog_manage.list.single

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import taokdao.main.business.dialog_manage.base.NegativeButtonTemp
import taokdao.main.business.dialog_manage.base.NeutralButtonTemp
import taokdao.main.business.dialog_manage.base.PositiveButtonTemp
import taokdao.main.business.dialog_manage.list.base.MaterialBaseListDialogBuilder
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.android.ui.dialogs.api.callback.ListCallbackSingleChoice
import tiiehenry.android.ui.dialogs.api.strategy.list.single.ISingleListDialogBuilder

class MaterialSingleListDialogBuilder(val context: Context) : MaterialBaseListDialogBuilder<ISingleListDialogBuilder>, ISingleListDialogBuilder {
    override val builder: MaterialDialog = MaterialDialog(context)

    override val positiveTemp: PositiveButtonTemp = PositiveButtonTemp()
    override val negativeTemp: NegativeButtonTemp = NegativeButtonTemp()
    override val neutralTemp: NeutralButtonTemp = NeutralButtonTemp()
    override val listTemp: SingleListTemp = SingleListTemp()


    override lateinit var dialog: IDialog

    override fun alwaysCallSingleChoiceCallback(): ISingleListDialogBuilder {
        listTemp.alwaysCallSingleChoiceCallback = true
        return builder()
    }


    override fun itemsCallbackSingleChoice(selectedIndex: Int, callback: ListCallbackSingleChoice): ISingleListDialogBuilder {
        listTemp.selectedIndex = selectedIndex
        listTemp.itemCallback = callback
        return builder()
    }

    override fun builder(): ISingleListDialogBuilder {
        return this
    }

}