package taokdao.codeeditor.layout

import taokdao.codeeditor.CodeIEditor
import taokdao.main.IMainView
import tiiehenry.ideditor.databinding.ContentsCodeeditorBinding
import tiiehenry.ktx.gone
import tiiehenry.ktx.visible

class QuickControlLayout(
    val main: IMainView,
    private val editor: CodeIEditor,
    val binding: ContentsCodeeditorBinding
) {

    fun init(): QuickControlLayout {
        binding.quickControlLeft.apply {
            setOnClickListener {
                editor.undoManager.undo()
            }
            setOnLongClickListener {
                editor.format()
                true
            }
        }
        binding.quickControlRight.apply {
            setOnClickListener {
                editor.undoManager.redo()
            }

            setOnLongClickListener {
                if (main.projectManager.isOpenedProject) {
                    main.buildManager.buildProject(true)
                } else {
                    main.buildManager.buildFile(true)
                }
                true
            }
        }

        return this
    }

    fun show() {
        binding.quickControlLeft.visible()
        binding.quickControlRight.visible()
    }

    fun hide() {
        binding.quickControlLeft.gone()
        binding.quickControlRight.gone()
    }
}