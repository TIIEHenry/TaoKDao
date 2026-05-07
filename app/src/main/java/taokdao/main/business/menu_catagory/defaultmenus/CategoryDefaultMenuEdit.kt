package taokdao.main.business.menu_catagory.defaultmenus

import taokdao.api.main.IMainContext
import taokdao.api.main.menu.MainMenu
import tiiehenry.code.view.CodeEditor
import tiiehenry.ideditor.R

class CategoryDefaultMenuEdit(val main: IMainContext) {
    val searchCurrent = MainMenu(main.getString(R.string.main_menu_edit_searchcurrent)) { _, editor ->
        editor?.searcher?.showFinder()
    }
    val searchReplace = MainMenu(main.getString(R.string.main_menu_edit_findreplace)) { _, editor ->
        editor?.searcher?.showReplacer()
    }

    val noteBlock = MainMenu(main.getString(R.string.main_menu_edit_noteblock)) { _, editor ->
        if (editor is CodeEditor)
            editor.noteBlock()
    }
    val docBlock = MainMenu(main.getString(R.string.main_menu_edit_docblock)) { _, editor ->
        if (editor is CodeEditor)
            editor.noteDocBlock()
    }
}