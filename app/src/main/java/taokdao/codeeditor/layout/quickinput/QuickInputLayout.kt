package taokdao.codeeditor.layout.quickinput

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taokdao.api.ui.window.Windows
import taokdao.codeeditor.CodeIEditor
import tiiehenry.ideditor.databinding.QuickInputLayoutBinding

class QuickInputLayout(val editor: CodeIEditor, private val binding: QuickInputLayoutBinding) {

    private val quickInputAdapter = QuickInputAdapter().apply {
        refresh(editor.language.symbolList)
        setOnItemClickListener { _, item, _ ->
            if (!editor.isEditable)
                return@setOnItemClickListener
            editor.insert(item)
        }
        setOnItemLongClickListener { _, s, _ ->
            if (!editor.isEditable)
                return@setOnItemLongClickListener
            val b = ("[]-+=/&|").contains(s)
            val b2 = ("'\"").contains(s)
            val b3 = ("~!<>").contains(s)
//            val b5 = ("\$").contains(s)
            when {
                b -> editor.paste(s + s)
                b2 -> {
                    editor.paste(s + s)
                    editor.moveCaretLeft(true)
                }

                b3 -> editor.paste("$s=")
                b -> {
                    editor.paste("$s{}")
                    editor.moveCaretLeft(true)
                }

                s == "(" -> {
                    editor.paste("()")
                    editor.moveCaretLeft(true)
                }

                s == ")" -> editor.paste("()")
                else -> editor.paste(s)
            }
        }
    }

    fun init(): QuickInputLayout {
        binding.quickInputRv.apply {
            adapter = quickInputAdapter
            layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        }
        binding.quickInputIbtnIndent.setOnClickListener {
            editor.indentLines()
        }
        binding.quickInputIbtnTabtool.setOnClickListener {
            Windows.TOOL_PAGES.window.showWindow()
        }
        return this
    }


    fun show() {
        binding.root.visibility = View.VISIBLE
    }

    fun hide() {
        binding.root.visibility = View.GONE
    }
}
