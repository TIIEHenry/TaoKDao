package taokdao.main.business.dialog_manage.list

import android.content.Context
import taokdao.main.business.dialog_manage.base.NegativeButtonTemp
import taokdao.main.business.dialog_manage.base.NeutralButtonTemp
import taokdao.main.business.dialog_manage.base.PositiveButtonTemp
import taokdao.main.business.dialog_manage.list.custom.MaterialCustomListDialogBuilder
import taokdao.main.business.dialog_manage.list.multi.MaterialMultiListDialogBuilder
import taokdao.main.business.dialog_manage.list.regular.MaterialRegularListDialogBuilder
import taokdao.main.business.dialog_manage.list.single.MaterialSingleListDialogBuilder
import tiiehenry.android.ui.dialogs.api.strategy.list.IListDialogBuilder
import tiiehenry.android.ui.dialogs.api.strategy.list.custom.ICustomListDialogBuilder
import tiiehenry.android.ui.dialogs.api.strategy.list.multi.IMultiListDialogBuilder
import tiiehenry.android.ui.dialogs.api.strategy.list.regular.IRegularListDialogBuilder
import tiiehenry.android.ui.dialogs.api.strategy.list.single.ISingleListDialogBuilder

class MDListDialogBuilder(context: Context) : MaterialRegularListDialogBuilder(context), IListDialogBuilder {

    override val positiveTemp: PositiveButtonTemp = PositiveButtonTemp()
    override val negativeTemp: NegativeButtonTemp = NegativeButtonTemp()
    override val neutralTemp: NeutralButtonTemp = NeutralButtonTemp()

    override fun typeRegular(): IRegularListDialogBuilder {
        return this

    }

    override fun typeMultiChoice(): IMultiListDialogBuilder {
        return MaterialMultiListDialogBuilder(context)
    }

    override fun typeSingleChoice(): ISingleListDialogBuilder {
        return MaterialSingleListDialogBuilder(context)
    }

    override fun typeCustom(): ICustomListDialogBuilder {
        return MaterialCustomListDialogBuilder(context)
    }
}