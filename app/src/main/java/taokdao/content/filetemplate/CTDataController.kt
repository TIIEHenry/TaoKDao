package taokdao.content.filetemplate

import taokdao.codeeditor.ieditor.CEDataController
import tiiehenry.code.view.CodeEditor

class CTDataController(editor: CodeEditor) : CEDataController(editor) {
    override fun isChanged(): Boolean {
        return true
    }
}
