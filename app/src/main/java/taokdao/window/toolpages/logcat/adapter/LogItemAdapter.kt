package taokdao.window.toolpages.logcat.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import taokdao.main.IMainView
import taokdao.window.toolpages.logcat.data.LogLevels
import taokdao.window.toolpages.logcat.data.LogLine
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.android.view.recyclerview.adapter.BaseIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ToolpagesLogcatItemBinding
import tiiehenry.ktx.res.getAttrColor

class LogItemAdapter(val main: IMainView) : BaseIdRecyclerAdapter<LogLine>() {
    private var scale = 1.0f
    private var scaleStep = 0.1f

    init {
        setOnItemClickListener { _, data, pos ->
            data.isExpanded = !data.isExpanded
            notifyItemChanged(pos)
        }
        setOnItemLongClickListener { _, item, _ ->
            Dialogs.global
                .asConfirm()
                .title(item.tag)
                .content(item.originalLine)
                .negativeText(R.string.copy_text)
                .onNegative { _ ->
                    copyItem(item)
                }
                .positiveText()
                .show()
        }
    }

    private fun copyItem(item: LogLine) {
        val cm = main.activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("LogFragment", item.originalLine)
        cm.setPrimaryClip(clip)
    }

    override fun getItemLayoutId(viewType: Int) = R.layout.toolpages_logcat_item

    @SuppressLint("SetTextI18n")
    override fun bindData(holder: RecyclerViewHolder, position: Int, item: LogLine) {
        val binding = ToolpagesLogcatItemBinding.bind(holder.itemView)
        binding.tvToolpagesLogcatItemMessage.apply {
            textSize = 14f * scale
            text = toSpannableString(item)
            val color = when (item.logLevel) {
                -1 -> R.attr.main_bottom_log_v_color
                Log.VERBOSE -> R.attr.main_bottom_log_v_color
                Log.DEBUG -> R.attr.main_bottom_log_d_color
                Log.INFO -> R.attr.main_bottom_log_i_color
                Log.WARN -> R.attr.main_bottom_log_w_color
                Log.ERROR -> R.attr.main_bottom_log_e_color
                Log.ASSERT -> R.attr.main_bottom_log_a_color
                else -> R.attr.main_bottom_log_v_color
            }
            setTextColor(main.activity.getAttrColor(color))
//                textSize = taokdao.tabtool.logcat.adapter.textSizeMsg * taokdao.tabtool.logcat.adapter.scale
        }
        binding.tvToolpagesLogcatItemProcess.apply {
            visibility = if (item.isExpanded) {
                textSize = 12f * scale
                text = "${LogLevels.levelMap[item.logLevel]} ${item.timestamp} ${item.processId}"
                View.VISIBLE
            } else {
                View.GONE
//                    textSize = textSizeProcess * scale
            }
        }
    }

    private fun toSpannableString(logLine: LogLine): SpannableString {
        val tTag = logLine.tag
        val s = "$tTag ${logLine.logOutput}"
        val spannableString = SpannableString(s)
        val foregroundColorSpan =
            ForegroundColorSpan(main.getAttrColor(ideditor.api.skin.R.attr.main_content_foreground_color_hint))
        spannableString.setSpan(
            foregroundColorSpan,
            0,
            tTag.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        return spannableString
    }

    fun size(): Int {
        return mData.size
    }

    fun zoomIn() {
        scale += scaleStep
        notifyDataSetChanged()
    }

    fun zoomOut() {
        if (scale > scaleStep) {
            scale -= scaleStep
            notifyDataSetChanged()
        }
    }
}