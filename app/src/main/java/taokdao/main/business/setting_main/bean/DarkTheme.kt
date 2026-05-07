package taokdao.main.business.setting_main.bean

import tiiehenry.ideditor.R

enum class DarkTheme(val label: Int, val id: Int) {
    DEFAULT(R.string.main_setting_darktheme_default, R.style.MainDarkDefault);

    companion object {
        val list = listOf(DEFAULT)
        var current = DEFAULT
    }
}