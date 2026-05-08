package taokdao.window.toolpages.event

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import taokdao.api.main.IMainContext
import taokdao.api.main.base.IContext
import tiiehenry.ideditor.R
import java.text.SimpleDateFormat
import java.util.*

class EventItem(val time: Date, val tag: String, val message: String) {
    constructor(tag: String, message: String) : this(Date(System.currentTimeMillis()), tag, message)
    constructor(main: IMainContext, tagId: Int, messageId: Int) : this(Date(System.currentTimeMillis()), main.getString(tagId), main.getString(messageId))
    constructor(main: IMainContext, tagId: Int, message: String) : this(Date(System.currentTimeMillis()), main.getString(tagId), message)
    constructor(main: IMainContext, tag: String, messageId: Int) : this(Date(System.currentTimeMillis()), tag, main.getString(messageId))

    var str: SpannableString? = null

    override fun toString(): String {
        return str.let {
            if (it != null) {
                it.toString()
            } else {
                val s = SimpleDateFormat.getTimeInstance().format(time) + "  " + tag + "  " + message
                s
            }
        }
    }

    fun toSpannableString(main: IContext): SpannableString {
        return str.let {
            if (it != null) {
                it
            } else {
                val t = SimpleDateFormat.getTimeInstance().format(time)
                val t_tag = "$t  $tag  "
                val s = "$t_tag $message"
                val spannableString = SpannableString(s)
                val foregroundColorSpan = ForegroundColorSpan(main.getAttrColor(ideditor.api.skin.R.attr.main_content_foreground_color_hint))
                spannableString.setSpan(foregroundColorSpan, t.length, t_tag.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

                str = spannableString
                spannableString
            }
        }
    }
}