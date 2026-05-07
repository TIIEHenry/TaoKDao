package taokdao.codeeditor.layout

import android.view.View
import org.jetbrains.anko.toast
import taokdao.codeeditor.CodeIEditor
import tiiehenry.android.text.SimpleTextWatcher
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.FindLayoutBinding
import tiiehenry.ideditor.databinding.ReplaceLayoutBinding
import kotlin.math.max

class FindReplaceLayout(
    val editor: CodeIEditor,
    val findLayout: View,
    private val replaceLayout: View
) {
    val findLayoutBinding = FindLayoutBinding.bind(findLayout)
    val replaceLayoutBinding = ReplaceLayoutBinding.bind(replaceLayout)

    private fun findPrior() {
        val index = editor.selector.selection?.start ?: editor.cursorController.currentIndex
        val selection =
            editor.searcher.findPrior(getFindText(), index, 0, editor.dataController.data.length)
        if (selection != null) {
            editor.selector.selection = selection
        }
    }

    private fun findNextFromStart(len: Int) {
        val index = max(
            (editor.selector.selection?.end
                ?: editor.cursorController.currentIndex) - len, 0
        )
        val selection =
            editor.searcher.findNext(getFindText(), index, 0, editor.dataController.data.length)
        if (selection != null) {
            editor.selector.selection = selection
        }
    }

    private fun findNext() {
        val index = editor.selector.selection?.end ?: editor.cursorController.currentIndex
        val selection =
            editor.searcher.findNext(getFindText(), index, 0, editor.dataController.data.length)
        if (selection != null) {
            editor.selector.selection = selection
        }
    }


    private fun replace() {
        val selection = editor.selector.selection
        if (selection != null) {
            editor.searcher.replace(getReplaceText(), selection)
            findNext()
        } else {
            findLayout.context.toast(findLayout.context.getString(R.string.contents_codeeditor_findreplace_notfound))
            findNext()
        }
    }

    fun setFindText(s: String) {
        findLayoutBinding.findEditInput.setText(s)
    }

    private fun getFindText(): String {
        return findLayoutBinding.findEditInput.text.toString()
    }

    fun setReplaceText(s: String) {
        replaceLayoutBinding.replaceEditInput.setText(s)
    }

    private fun getReplaceText(): String {
        return replaceLayoutBinding.replaceEditInput.text.toString()
    }

    private fun replaceAll() {
        editor.searcher.replaceAll(
            getFindText(),
            getReplaceText(),
            0,
            0,
            editor.dataController.data.length
        )
    }

    private fun switchMatchCase(v: View) {
        isMatchCase = !isMatchCase
        if (isMatchCase) {
            v.setBackgroundResource(ideditor.api.skin.R.drawable.main_bottom_round_selected)
        } else {
            v.setBackgroundResource(ideditor.api.skin.R.drawable.main_bottom_round_selector)
        }
        findLayout.context.toast(v.context.getString(R.string.contents_codeeditor_findreplace_matchcase) + isMatchCase)
    }

    private fun switchMatchRegex(v: View) {
        isMatchRegex = !isMatchRegex
        if (isMatchRegex) {
            v.setBackgroundResource(ideditor.api.skin.R.drawable.main_bottom_round_selected)
        } else {
            v.setBackgroundResource(ideditor.api.skin.R.drawable.main_bottom_round_selector)
        }
        findLayout.context.toast(v.context.getString(R.string.contents_codeeditor_findreplace_matchregex) + isMatchRegex)
    }

    var isMatchCase = false
    var isMatchRegex = false

    fun init(): FindReplaceLayout {
        findLayoutBinding.findEditInput.addTextChangedListener(SimpleTextWatcher.newAfterWatcher {
            if (it.isNotEmpty())
                findNextFromStart(it.length)
        })
        val onClickSearchLayout = View.OnClickListener { v ->
            when (v) {
                findLayoutBinding.findIbtnPrior -> findPrior()
                findLayoutBinding.findIbtnNext -> findNext()
                replaceLayoutBinding.replaceReplaceOne -> replace()
                replaceLayoutBinding.replaceReplaceAll -> replaceAll()
                findLayoutBinding.findIbtnCase -> switchMatchCase(v)
                findLayoutBinding.findIbtnRegex -> switchMatchRegex(v)
                findLayoutBinding.findIbtnClose -> editor.searcher.hideFinder()
            }
        }
//        val onLongClickSearchLayout = View.OnLongClickListener { v ->
//            true
//        }
        arrayListOf<View>(
            findLayoutBinding.findIbtnPrior,
            findLayoutBinding.findIbtnNext,
            replaceLayoutBinding.replaceReplaceOne,
            replaceLayoutBinding.replaceReplaceAll,
            findLayoutBinding.findIbtnCase,
            findLayoutBinding.findIbtnRegex,
            findLayoutBinding.findIbtnClose
        ).forEach {
            it.setOnClickListener(onClickSearchLayout)
//            it.setOnLongClickListener(onLongClickSearchLayout)
        }
        return this
    }

    fun show(showReplaceLayout: Boolean = false) {
        if (!editor.isEditable)
            return
        replaceLayout.visibility = if (showReplaceLayout) View.VISIBLE else View.GONE
        findLayout.visibility = View.VISIBLE
    }


    fun hideAll() {
        findLayout.visibility = View.GONE
        hideReplaceLayout()
    }

    fun hideReplaceLayout() {
        replaceLayout.visibility = View.GONE
    }
}