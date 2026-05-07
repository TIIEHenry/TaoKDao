package taokdao.main.business.setting_main.bean

import tiiehenry.ideditor.R
import java.util.*


enum class DisplayLanguage(val label: Int, val locale: Locale) {
    SYSTEM(R.string.main_setting_language_followsystem, Locale.getDefault()),
    SIMPLIFIED_CHINESE(R.string.main_setting_language_zh, Locale.SIMPLIFIED_CHINESE),
    ENGLISH(R.string.main_setting_language_en, Locale.ENGLISH);

    companion object {
        val list = listOf(SYSTEM, SIMPLIFIED_CHINESE, ENGLISH)
    }
}