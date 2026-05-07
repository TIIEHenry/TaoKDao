package taokdao.main.business.dialog_manage.custom

import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import tiiehenry.android.ui.dialogs.api.IDialog

class CustomViewTemp {
    var scrollable: Boolean = false
    var dialogWrapContent: Boolean = true
    var view: View? = null
    fun apply(builder: MaterialDialog, dialog: IDialog, margin: Boolean) {
        builder.customView(view = view, scrollable = dialogWrapContent, dialogWrapContent = dialogWrapContent, horizontalPadding = margin)
    }
}