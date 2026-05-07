package taokdao.main.toolbar

import android.graphics.drawable.Drawable
import android.view.View
import taokdao.api.main.IMainContext

data class ToolbarMenu(val icon: Drawable, val click: View.OnClickListener, val longClick: View.OnClickListener? = null) {
    constructor(main: IMainContext, iconId: Int, click: View.OnClickListener, longClick: View.OnClickListener? = null) : this(main.getDrawable(iconId), click, longClick)
}