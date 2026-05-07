package taokdao.main.business.dialog_manage.base

import com.afollestad.materialdialogs.MaterialDialog
import tiiehenry.android.ui.dialogs.api.IDialog

class NegativeButtonTemp : ButtonTemp() {
    override fun apply(builder: MaterialDialog, dialog: IDialog) {
        if (exists())
            builder.negativeButton(res, text) { click?.onClick(dialog) }
    }
}