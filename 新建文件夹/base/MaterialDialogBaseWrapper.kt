package taokdao.main.business.dialog_manage.base

import com.afollestad.materialdialogs.MaterialDialog
import com.lxj.androidktx.core.runOnUIThread
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.taokdao.ui.dialog.showInAnim

open class MaterialDialogBaseWrapper(val dialog: MaterialDialog) : IDialog {
    override fun dismiss() {
        runOnUIThread {
            dialog.hide()
        }
    }

    override fun show() {
        dialog.showInAnim()
    }

}