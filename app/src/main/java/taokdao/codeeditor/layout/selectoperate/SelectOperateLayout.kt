package taokdao.codeeditor.layout.selectoperate

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taokdao.codeeditor.CodeIEditor
import tiiehenry.ideditor.R

class SelectOperateLayout(val editor: CodeIEditor, val layout: RecyclerView) {
    private val selectionOperateAdapter = SelectOperateAdapter()

    private val noteBlock = SelectOperate(layout.context.getDrawable(R.drawable.codeeditor_selectoperate_comment), View.OnClickListener {
        editor.noteBlock()
    }, View.OnLongClickListener {
        editor.noteDocBlock()
        true
    })
    private val selectMore = SelectOperate(layout.context.getDrawable(R.drawable.codeeditor_selectoperate_select_group), View.OnClickListener {
        editor.selectMore()
    }, View.OnLongClickListener {
        editor.selectAll(false)
        true
    })
    private val cut = SelectOperate(layout.context.getDrawable(R.drawable.codeeditor_selectoperate_cut), View.OnClickListener {
        editor.cut()
    })
    private val copy = SelectOperate(layout.context.getDrawable(R.drawable.codeeditor_selectoperate_copy), View.OnClickListener {
        editor.copy()
    })
    private val paste = SelectOperate(layout.context.getDrawable(R.drawable.codeeditor_selectoperate_paste), View.OnClickListener {
        editor.paste()
    })

    private val findReplace = SelectOperate(layout.context.getDrawable(R.drawable.codeeditor_selectoperate_findreplace), View.OnClickListener {
        editor.searcher.showFinder()
        editor.selector.selectedData?.let { it1 -> editor.searcher.setFindText(it1) }
    }, View.OnLongClickListener {
        editor.searcher.showReplacer()
        editor.selector.selectedData?.let { it1 -> editor.searcher.setFindText(it1) }
        true
    })

    init {
        layout.apply {
            adapter = selectionOperateAdapter
            layoutManager = LinearLayoutManager(layout.context, LinearLayoutManager.HORIZONTAL, false)
        }
        selectionOperateAdapter.refresh(listOf(
                noteBlock, selectMore, cut, copy, paste, findReplace
        ))

    }

    fun show() {
        if (!editor.isEditable)
            return
        layout.visibility = View.VISIBLE
    }

    fun hide() {
        layout.visibility = View.GONE
    }

}