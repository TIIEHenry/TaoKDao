package taokdao.main.business.theme_manage

import android.content.Intent
import com.lxj.xpopup.XPopup
import taokdao.api.setting.theme.ThemeParts
import taokdao.api.setting.theme.resource.ThemeColors
import taokdao.api.setting.theme.resource.ThemeDrawables
import taokdao.main.MainActivity
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import ideditor.api.skin.R


interface ThemeManageView : ThemeManageContract.V {
    override fun showChangeThemeDialogToDark() {
        showChangeThemeDialog()
    }

    override fun showChangeThemeDialogToLight() {
        showChangeThemeDialog()
    }


    private fun showChangeThemeDialog() {
        Dialogs.global
                .asConfirm()
                .title(tiiehenry.ideditor.R.string.business_theme_manage_changetheme_dialog_title)
                .content(tiiehenry.ideditor.R.string.business_theme_manage_changetheme_dialog_content)
                .positiveText()
                .negativeText()
                .onPositive { dialog ->
                    dialog.dismiss()
                    activity.startActivity(Intent(activity,MainActivity::class.java).apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) })
                }
                .show()
    }

    override fun applyThemeColors() {
        XPopup.setPrimaryColor(getAttrColor(com.lxj.xpopup.R.attr.colorPrimary))
    }

    override fun getThemeColors(themeParts: ThemeParts): ThemeColors {
        return ThemeColors().apply {
            when (themeParts) {
                ThemeParts.APPBAR -> {
                    foregroundColorHint = getAttrColor(R.attr.main_appbar_foreground_color_hint)
                    foregroundColor = getAttrColor(R.attr.main_appbar_foreground_color)
                    foregroundColorAccent = getAttrColor(R.attr.main_appbar_foreground_color_accent)
                    backgroundColorHint = getAttrColor(R.attr.main_appbar_background_color_hint)
                    backgroundColor = getAttrColor(R.attr.main_appbar_background_color)
                    backgroundColorAccent = getAttrColor(R.attr.main_appbar_background_color_accent)
                }
                ThemeParts.CONTENT -> {
                    foregroundColorHint = getAttrColor(R.attr.main_content_foreground_color_hint)
                    foregroundColor = getAttrColor(R.attr.main_content_foreground_color)
                    foregroundColorAccent = getAttrColor(R.attr.main_content_foreground_color_accent)
                    backgroundColorHint = getAttrColor(R.attr.main_content_background_color_hint)
                    backgroundColor = getAttrColor(R.attr.main_content_background_color)
                    backgroundColorAccent = getAttrColor(R.attr.main_content_background_color_accent)
                }
                ThemeParts.BOTTOM -> {
                    foregroundColorHint = getAttrColor(R.attr.main_bottom_foreground_color_hint)
                    foregroundColor = getAttrColor(R.attr.main_bottom_foreground_color)
                    foregroundColorAccent = getAttrColor(R.attr.main_bottom_foreground_color_accent)
                    backgroundColorHint = getAttrColor(R.attr.main_bottom_background_color_hint)
                    backgroundColor = getAttrColor(R.attr.main_bottom_background_color)
                    backgroundColorAccent = getAttrColor(R.attr.main_bottom_background_color_accent)
                }
                ThemeParts.FLOAT -> {
                    foregroundColorHint = getAttrColor(R.attr.main_float_foreground_color_hint)
                    foregroundColor = getAttrColor(R.attr.main_float_foreground_color)
                    foregroundColorAccent = getAttrColor(R.attr.main_float_foreground_color_accent)
                    backgroundColorHint = getAttrColor(R.attr.main_float_background_color_hint)
                    backgroundColor = getAttrColor(R.attr.main_float_background_color)
                    backgroundColorAccent = getAttrColor(R.attr.main_float_background_color_accent)
                }
            }

        }
    }

    override fun getThemeDrawables(themeParts: ThemeParts): ThemeDrawables {
        return ThemeDrawables().apply {
            when (themeParts) {
                ThemeParts.APPBAR -> {
                    rectangleBackground = R.drawable.main_appbar_rectangle_bg
                    rectangleNormal = R.drawable.main_appbar_rectangle_normal
                    rectangleSelected = R.drawable.main_appbar_rectangle_selected
                    rectangleSelector = R.drawable.main_appbar_rectangle_selector
                    roundBackground = R.drawable.main_appbar_round_bg
                    roundNormal = R.drawable.main_appbar_round_normal
                    roundSelected = R.drawable.main_appbar_round_selected
                    roundSelector = R.drawable.main_appbar_round_selector
                }
                ThemeParts.CONTENT -> {
                    rectangleBackground = R.drawable.main_content_rectangle_bg
                    rectangleNormal = R.drawable.main_content_rectangle_normal
                    rectangleSelected = R.drawable.main_content_rectangle_selected
                    rectangleSelector = R.drawable.main_content_rectangle_selector
                    roundBackground = R.drawable.main_content_round_bg
                    roundNormal = R.drawable.main_content_round_normal
                    roundSelected = R.drawable.main_content_round_selected
                    roundSelector = R.drawable.main_content_round_selector
                }
                ThemeParts.BOTTOM -> {
                    rectangleBackground = R.drawable.main_bottom_rectangle_bg
                    rectangleNormal = R.drawable.main_bottom_rectangle_normal
                    rectangleSelected = R.drawable.main_bottom_rectangle_selected
                    rectangleSelector = R.drawable.main_bottom_rectangle_selector
                    roundBackground = R.drawable.main_bottom_round_bg
                    roundNormal = R.drawable.main_bottom_round_normal
                    roundSelected = R.drawable.main_bottom_round_selected
                    roundSelector = R.drawable.main_bottom_round_selector
                }
                ThemeParts.FLOAT -> {
                    rectangleBackground = R.drawable.main_float_rectangle_bg
                    rectangleNormal = R.drawable.main_float_rectangle_normal
                    rectangleSelected = R.drawable.main_float_rectangle_selected
                    rectangleSelector = R.drawable.main_float_rectangle_selector
                    roundBackground = R.drawable.main_float_round_bg
                    roundNormal = R.drawable.main_float_round_normal
                    roundSelected = R.drawable.main_float_round_selected
                    roundSelector = R.drawable.main_float_round_selector
                }
            }
        }

    }
}