package taokdao.main.business.language_manage

import taokdao.api.setting.language.ILanguageManager
import java.util.*


class LanguageManagePresenter(internal val view: LanguageManageContract.V) : LanguageManageContract.P {
    private val model = LanguageManageModel()

    override fun getLocale(): Locale {
        val config = view.resources.configuration
//        config.setLocale(Locale.getDefault())
        return ILanguageManager.getLocale(config)
    }

    override fun setLocale(locale: Locale) {
        val config = view.resources.configuration
        return ILanguageManager.setLocale(config, locale)
    }

    override fun getCountry(): String {
        return ILanguageManager.getCountry(locale)
    }

    override fun getLanguage(): String {
        return ILanguageManager.getLanguage(locale)
    }

    override fun getLanguageCountry(): String {
        return ILanguageManager.getLanguageCountry(locale)
    }
}