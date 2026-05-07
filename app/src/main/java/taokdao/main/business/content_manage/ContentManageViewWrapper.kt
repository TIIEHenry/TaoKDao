package taokdao.main.business.content_manage

import android.view.View
import com.lxj.xpopup.XPopup
import taokdao.api.event.senders.ContentSender
import taokdao.api.main.action.MainAction
import taokdao.api.setting.preference.base.IPreference
import taokdao.api.ui.content.IContent
import taokdao.api.ui.content.menu.ControlMenu
import taokdao.main.business.content_manage.list.ContentListPopupLayout
import taokdao.main.business.content_manage.menu.ContentMenuPopup
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ActivityMainBinding
import tiiehenry.ktx.gone
import tiiehenry.ktx.visible
import tiiehenry.taokdao.ui.content.ContentAdapter
import tiiehenry.taokdao.ui.setting.LeftTopSettingPopup
import tiiehenry.taokdao.ui.view.tablayout.TabLayoutOnPageChangeListener


class ContentManageViewWrapper(
    val view: ContentManageContract.V,
    override val contentManagePresenter: ContentManagePresenter
) : ContentManageContract.VW {

    val binding = ActivityMainBinding.bind(view.contentView)

    private val contentAdapter = ContentAdapter(view.activity.supportFragmentManager)

    override fun initContentManger() {
        MainAction.onCreate.addObserver {
            binding.mainBodyContentViewPager.apply {
//                offscreenPageLimit=0
                adapter = contentAdapter
                addOnPageChangeListener(TabLayoutOnPageChangeListener(binding.mainToolbarTabLayout))
                addOnPageChangeListener(contentManagePresenter)
            }
            binding.mainToolbarTabLayout.apply {
                isTabIndicatorFullWidth = false
                setupWithViewPager(binding.mainBodyContentViewPager, true)
                setOnTabLongClickListener {
                    showContentListPopup(contentAdapter.dataList)
                    true
                }
            }.addOnTabSelectedListener(contentManagePresenter)

            checkContentGuider()

            view.indicatorManager.endIndicator.setOnLongClickListener {
                contentManagePresenter.showSettingWindow()
                true
            }
        }
    }

    override fun addContent(content: IContent) {
        contentAdapter.add(content)
    }

    override fun removeContent(content: IContent) {
        contentAdapter.remove(content)
    }

    override fun removeContents(contents: MutableList<IContent>) {
        contentAdapter.remove(contents)
    }

    override fun clearContent() {
        contentAdapter.clear()
        checkContentGuider()
    }

    override fun onContentAdded() {
        checkContentGuider()
    }

    override fun onContentRemoved() {
        checkContentGuider()
    }

    override fun checkContentGuider() {
        view.launchMain {
            if (getContentList().size == 0) {
                binding.mainContentGuider.root.visible()
                view.main.toolBarLayoutPresenter.refreshQuickMenu(listOf())
            } else {
                binding.mainContentGuider.root.gone()
            }
        }
    }

    override fun selectContentTab(index: Int) {
        binding.mainToolbarTabLayout.getTabAt(index)?.select()
    }

    override fun getContentList(): MutableList<IContent> {
        return contentAdapter.dataList
    }

    override fun setCurrentContent(index: Int) {
        binding.mainBodyContentViewPager.setCurrentItem(index, true)
    }

    private val contentListPopupLayout by lazy {
        ContentListPopupLayout(view, this)
    }
    private val contentListPopup by lazy {

        XPopup.Builder(view.context)
            .atView(binding.mainToolbarCl)
            .hasShadowBg(true)
//                .popupPosition(PopupPosition.Bottom)
            .dismissOnTouchOutside(true)
            .dismissOnBackPressed(true)
            .isRequestFocus(true)
            .asCustom(contentListPopupLayout)
    }

    override fun showContentListPopup(dataList: MutableList<IContent>) {
        if (dataList.isEmpty()) {
            view.notify(R.string.content_nocontent_opened)
            return
        }
        contentListPopupLayout.refresh(dataList)
        contentListPopup.show()
    }

    override fun hideContentListPopup() {
        contentListPopup.dismiss()
    }

    override fun showSettingPopup(settingList: MutableList<IPreference<*>>) {
        if (settingList.size > 0) {
            val popup =
                LeftTopSettingPopup(view.main, settingList, binding.mainToolbarIndicatorStartTv)
            popup.setOnDismissListener {
                MainAction.onContentSettingChanged.runObservers(view.main)
            }
            popup.showAt()
        } else
            view.notify(R.string.setting_nosetting_provided)
    }

    override fun getDefaultContentMenuList(): MutableList<ControlMenu> {
        return mutableListOf(
            ControlMenu(view.getString(R.string.main_tab_popupmenu_closecurrent)) {
                contentManagePresenter.closeCurrent()
            },
            ControlMenu(view.getString(R.string.main_tab_popupmenu_closeother)) {
                contentManagePresenter.closeOther()
            },
            ControlMenu(view.getString(R.string.main_tab_popupmenu_closeall)) {
                contentManagePresenter.closeAll()
            }
        )
    }

    override fun showMenuPopup(menuList: MutableList<ControlMenu>?, view: View) {
        ContentMenuPopup(this.view.main, getDefaultContentMenuList(), menuList).show(view)
    }

    override fun onContentSaveSuccess(content: IContent, path: String) {
        ContentSender(content).saveSuccess(path).send(view)
        MainAction.CurrentFileSaved.runObservers(view)
    }

    override fun onContentSaveFailed(content: IContent, path: String, message: String?) {
        ContentSender(content).saveFailed(path).send(view)
        ContentSender(content).message(message.toString()).send(view)
    }

    override fun onContentAllSaved() {
        ContentSender.messageGlobal(view.main.getString(R.string.file_saved_all)).send(view.main)
    }

}