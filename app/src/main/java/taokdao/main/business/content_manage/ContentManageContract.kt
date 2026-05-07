package taokdao.main.business.content_manage

import android.view.View
import taokdao.api.setting.preference.base.IPreference
import taokdao.api.ui.content.IContent
import taokdao.api.ui.content.manage.IContentManager
import taokdao.api.ui.content.menu.ControlMenu
import taokdao.main.IMainView


interface ContentManageContract {
    interface V : IMainView {
        val contentManagePresenter: ContentManagePresenter
    }

    interface P : IContentManager

    interface M
    interface VW {
        val contentManagePresenter: ContentManagePresenter

        fun setCurrentContent(index: Int)
        fun showContentListPopup(dataList: MutableList<IContent>)
        fun hideContentListPopup()
        fun showSettingPopup(settingList: MutableList<IPreference<*>>)

        fun getDefaultContentMenuList(): MutableList<ControlMenu>
        fun showMenuPopup(menuList: MutableList<ControlMenu>?, view: View)
        fun initContentManger()
        fun getContentList(): MutableList<IContent>
        fun selectContentTab(index: Int)
        fun checkContentGuider()
        fun onContentRemoved()
        fun onContentAdded()
        fun addContent(content: IContent)
        fun removeContent(content: IContent)
        fun removeContents(contents: MutableList<IContent>)
        fun clearContent()
        fun onContentSaveSuccess(content: IContent, path: String)
        fun onContentSaveFailed(content: IContent, path: String, message: String?)
        fun onContentAllSaved()
    }
}