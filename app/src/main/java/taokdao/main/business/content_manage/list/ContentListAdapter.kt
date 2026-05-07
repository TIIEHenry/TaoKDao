package taokdao.main.business.content_manage.list

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import ideditor.api.skin.R
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.IContent
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.databinding.ContentContentlistItemBinding
import java.io.File


class ContentListAdapter(val main: IMainContext) : BaseIdRecyclerAdapter<IContent>() {
    private val colorSelected = main.getAttrColor(R.attr.main_content_background_color_accent)
    private val colorUnselected = main.getAttrColor(R.attr.main_content_background_color)
    private val colorPath = main.getAttrColor(R.attr.main_content_foreground_color_hint)
    override fun getItemLayoutId(viewType: Int): Int =
        tiiehenry.ideditor.R.layout.content_contentlist_item

    override fun bindData(holder: RecyclerViewHolder, position: Int, item: IContent) {
//        val binding=holder.getBinding<ContentContentlistItemBinding>()
        val binding = ContentContentlistItemBinding.bind(holder.itemView)
        binding.ivIcon.setImageDrawable(item.icon)

        val label = if (item.editor.dataController.isChanged) {
            "*"
        } else {
            ""
        } + item.label
        val spannableString = SpannableString("$label  ${File(item.path).parent}")
        spannableString.let {
            it.setSpan(
                ForegroundColorSpan(colorPath),
                label.length,
                it.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            it.setSpan(
                RelativeSizeSpan(0.85f),
                label.length,
                it.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            it.setSpan(
                StyleSpan(Typeface.ITALIC),
                label.length,
                it.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        binding.tvName.text = spannableString
        if (main.contentManager.current == item) {
            holder.itemView.setBackgroundColor(colorSelected)
        } else {
            holder.itemView.setBackgroundColor(colorUnselected)
        }
    }
}