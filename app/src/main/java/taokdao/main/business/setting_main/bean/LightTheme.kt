package taokdao.main.business.setting_main.bean

import tiiehenry.ideditor.R

enum class LightTheme(val label: Int, val id: Int) {
    DEFAULT(R.string.main_setting_lighttheme_default, R.style.MainLightDefault);

    companion object {
        val list = listOf(DEFAULT)
        var current = DEFAULT
    }
}