package taokdao.main.business.language_manage

import taokdao.api.setting.language.ILanguageManager
import taokdao.main.IMainView


interface LanguageManageContract {
    interface V : IMainView {
        val languageManagePresenter: LanguageManagePresenter

    }

    interface P : ILanguageManager

    interface M
}