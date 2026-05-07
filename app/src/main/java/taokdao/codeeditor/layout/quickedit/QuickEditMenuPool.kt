package taokdao.codeeditor.layout.quickedit

import taokdao.api.main.IMainContext
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R

//Presenter
object QuickEditMenuPool {

    fun initMenuList(context: IMainContext) {
        QuickEditMenuSet.addMenu(context.getString(R.string.contents_codeeditor_quickmenu_gotoline)) { _, editor ->
            Dialogs.global
                    .asInput()
                    .title(R.string.contents_codeeditor_quickmenu_gotoline)
                    .inputRange(1, editor.lineCount.toString().length)
                    .allowEmptyInput(false)
                    .autoDismiss(false)
                    .input(context.getString(R.string.contents_codeeditor_quickmenu_gotoline_dialog_hint), "") { dialog, input ->
                        try {
                            val line = input.toString().toInt()
                            if (line > 0 && line <= editor.lineCount + 1) {
                                editor.gotoLine(line)
                                dialog.dismiss()
                            } else
                                dialog.setInputError(context.getString(R.string.contents_codeeditor_quickmenu_gotoline_failed_beyondrange))
                        } catch (e: NumberFormatException) {
                            dialog.setInputError(context.getString(R.string.contents_codeeditor_quickmenu_gotoline_failed_inputerror))
                        }
                    }
                    .negativeText()
                    .positiveText()
                    .show()
        }

        QuickEditMenuSet.addMenu(context.getString(R.string.contents_codeeditor_quickmenu_deleteline)) { _, editor -> editor.deleteLines() }
        QuickEditMenuSet.addMenu(context.getString(R.string.contents_codeeditor_quickmenu_cutline)) { _, editor -> editor.cutLines() }
        QuickEditMenuSet.addMenu(context.getString(R.string.contents_codeeditor_quickmenu_copyline)) { _, editor -> editor.copyLines() }
        QuickEditMenuSet.addMenu(context.getString(R.string.contents_codeeditor_quickmenu_selectline)) { _, editor -> editor.selectLines() }
        QuickEditMenuSet.addMenu(context.getString(R.string.contents_codeeditor_quickmenu_noteline)) { _, editor -> editor.noteLines() }
        QuickEditMenuSet.addMenu(context.getString(R.string.contents_codeeditor_quickmenu_indentline)) { _, editor -> editor.indentLines() }

    }
}
