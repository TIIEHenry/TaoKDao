package taokdao.api.ui.base

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class PanelProp(
    context: Context,
    @StringRes labelResId: Int,
    @DrawableRes iconResId: Int? = null,
    @StringRes descriptionResId: Int? = null
) : IPanelProp {
    override val icon: Drawable? = iconResId?.let { context.getDrawable(it) }
    override val label: String = context.getString(labelResId)
    override val description: String? = descriptionResId?.let { context.getString(it) }
}
