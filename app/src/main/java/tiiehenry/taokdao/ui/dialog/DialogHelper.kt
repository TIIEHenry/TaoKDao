package tiiehenry.taokdao.ui.dialog

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.StateListDrawable
import android.text.InputType
import com.afollestad.materialdialogs.MaterialDialog
import taokdao.api.main.base.IContext
import tiiehenry.android.text.SimpleTextWatcher
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.android.ui.dialogs.api.strategy.confirm.IConfirmDialogBuilder
import tiiehenry.android.ui.dialogs.api.strategy.input.IInputDialog
import tiiehenry.android.ui.dialogs.api.strategy.input.IInputDialogBuilder
import tiiehenry.android.ui.dialogs.mddialogs.runOnUIThread
import tiiehenry.ideditor.R
import tiiehenry.ktx.res.getAttrColor
import java.io.File

private fun createColorStateListAttr(context: Context, normal: Int, pressed: Int, focused: Int, unable: Int): ColorStateList {
    return createColorStateList(context.getAttrColor(normal), context.getAttrColor(pressed), context.getAttrColor(focused), context.getAttrColor(unable))
}

/** 对TextView设置不同状态时其文字颜色。  */
private fun createColorStateList(normal: Int, pressed: Int, focused: Int, unable: Int): ColorStateList {
    val colors = intArrayOf(pressed, focused, normal, focused, unable, normal)
    val states = arrayOfNulls<IntArray>(6)
    states[0] = intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled)
    states[1] = intArrayOf(android.R.attr.state_enabled, android.R.attr.state_focused)
    states[2] = intArrayOf(android.R.attr.state_enabled)
    states[3] = intArrayOf(android.R.attr.state_focused)
    states[4] = intArrayOf(android.R.attr.state_window_focused)
    states[5] = intArrayOf()
    return ColorStateList(states, colors)
}

/** 设置Selector。  */
fun newSelector(context: Context, idNormal: Int, idPressed: Int, idFocused: Int,
                idUnable: Int): StateListDrawable {
    val bg = StateListDrawable()
    val normal = if (idNormal == -1) null else context.getDrawable(idNormal)
    val pressed = if (idPressed == -1) null else context.getDrawable(idPressed)
    val focused = if (idFocused == -1) null else context.getDrawable(idFocused)
    val unable = if (idUnable == -1) null else context.getDrawable(idUnable)
    // View.PRESSED_ENABLED_STATE_SET
    bg.addState(intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled), pressed)
    // View.ENABLED_FOCUSED_STATE_SET
    bg.addState(intArrayOf(android.R.attr.state_enabled, android.R.attr.state_focused), focused)
    // View.ENABLED_STATE_SET
    bg.addState(intArrayOf(android.R.attr.state_enabled), normal)
    // View.FOCUSED_STATE_SET
    bg.addState(intArrayOf(android.R.attr.state_focused), focused)
    // View.WINDOW_FOCUSED_STATE_SET
    bg.addState(intArrayOf(android.R.attr.state_window_focused), unable)
    // View.EMPTY_STATE_SET
    bg.addState(intArrayOf(), normal)
    return bg
}


fun MaterialDialog.applyTheme(): MaterialDialog {
//    titleColorAttr(R.attr.main_content_foreground_color_hint)
//    contentColorAttr(R.attr.main_content_foreground_color)

    /*backgroundColorAttr(R.attr.main_content_background_color)
    */
//    itemsColorAttr(R.attr.main_content_foreground_color)
//
//    positiveColorAttr(R.attr.main_content_foreground_color)
//    negativeColorAttr(R.attr.main_content_foreground_color)
//    neutralColorAttr(R.attr.main_content_foreground_color)
//    buttonRippleColorAttr(R.attr.colorAccent)
//    btnSelector(R.drawable.main_btn_selector)
    return this
}


fun IInputDialogBuilder.inputTypeFile(): IInputDialogBuilder {
    inputType(InputType.TYPE_TEXT_VARIATION_URI)
    return this
}

fun IInputDialogBuilder.inputRangeFile(): IInputDialogBuilder {
    inputRange(1, 255)
    return this
}

fun MaterialDialog.showInAnim(): MaterialDialog {
    context.runOnUIThread {
        window?.setWindowAnimations(R.style.Animation_MaterialDialog)
        show()
    }

//    show()
    return this
}

fun isValidFileName(fileName: CharSequence): Boolean {
    if (fileName.length > 255)
        return false
    if (fileName.isEmpty())
        return false
//    return fileName.matches("[^\\s\\\\/:*?\"<>|](\\x20|[^\\s\\\\/:*?\"<>|])*[^\\s\\\\/:*?\"<>|.]$".toRegex())
    return fileName.matches("[^\\s\\\\/:*?\"<>|]?(\\x20|[^\\s\\\\/:*?\"<>|])*[^\\s\\\\/:*?\"<>|.]?$".toRegex())
}

fun getNameError(parent: File?, name: String): String? {
    if (!isValidFileName(name)) {
        return "非法文件名！"
    }
    if (parent == null) {
        return null
    }
    val newFile = File(parent, name)
    if (newFile.exists()) {
        return "文件已存在！"
    }
    return null
}

fun IInputDialog.addFileNameChecker(parent: File): IInputDialog {
    inputField.apply {
        addTextChangedListener(SimpleTextWatcher.newAfterWatcher { input ->
            getNameError(parent, input)?.let {
                error = it
            }
        })
    }
    return this
}

fun IConfirmDialogBuilder.showInThread() {
    runOnUIThread {
        show()
    }
}

fun ErrorDialog(context: Context, title: String?, message: String?): IConfirmDialogBuilder {
    return Dialogs.global.asConfirm()
            .title(title ?: context.getString(R.string.error))
            .content(message ?: "")
            .positiveText(android.R.string.ok)
}

fun ErrorDialog(main: IContext, title: String?, message: String?): IConfirmDialogBuilder {
    return ErrorDialog(main.context, title, message)
}

fun ErrorDialog(context: Context, message: String?): IConfirmDialogBuilder {
    return ErrorDialog(context, context.getString(R.string.error), message)
}

fun ErrorDialog(main: IContext, message: String?): IConfirmDialogBuilder {
    return ErrorDialog(main.context, main.getString(R.string.error), message)
}

fun ErrorDialog(main: IContext, e: Exception): IConfirmDialogBuilder {
    return ErrorDialog(main.context, main.getString(R.string.error), e.message.toString())
}

