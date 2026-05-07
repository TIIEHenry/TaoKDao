package taokdao.main.business.content_manage

import androidx.viewpager.widget.ViewPager
import taokdao.api.setting.preference.base.IPreference
import taokdao.api.ui.content.IContent
import taokdao.api.ui.content.state.ContentState
import taokdao.api.ui.progressbar.ProgressBarSet
import tiiehenry.taokdao.ui.view.tablayout.OnTabSelectedListener
import tiiehenry.taokdao.ui.view.tablayout.TabLayout


class ContentManagePresenter(internal val view: ContentManageContract.V) : ContentManageContract.P, ViewPager.OnPageChangeListener, OnTabSelectedListener {
    private val model = ContentManageModel()

    internal val vw: ContentManageContract.VW by lazy {
        ContentManageViewWrapper(view, this)
    }

    private var currIndex = -1
    private var currentContent: IContent? = null

    //    private val fileObserverMap = hashMapOf<IContent, FileObserver>()
    override fun init() {
        vw.initContentManger()
    }


    override fun add(content: IContent, select: Boolean) {
        content.setContentState(ContentState.STATE_ADDING)
        content.stateObserver?.onAdding()
        view.launchMain {
            vw.addContent(content)
//            val file = File(content.path)
//            if (file.exists()) {
//                fileObserverMap[content] = object : FileObserver(file) {
//                    override fun onEvent(event: Int, path: String?) {
//                        when (event and ALL_EVENTS) {
//                            ACCESS, MODIFY, ATTRIB, CLOSE_WRITE, CLOSE_NOWRITE, OPEN -> {
//                            }
//                            MOVED_FROM,  MOVE_SELF -> {
//                            }
//                            MOVED_TO->{
//                                logea(path)
//                            }
//                            DELETE, DELETE_SELF -> {
//                            }
//                            CREATE -> {
//                            }
//                            else->{
//                                logea(path)
//                                logea(path)
//                            }
//                        }
//                    }
//                }.apply { startWatching() }
//            }
//            loge("addtabcontent " + tabContent.label + " index " + currIndex)
            if (select || current == null)
                show(content)
//            content.stateObserver?.onAdded()
            vw.onContentAdded()
        }
    }

    override fun remove(content: IContent) {
        vw.removeContent(content)
//        fileObserverMap.remove(content)?.stopWatching()
        content.setContentState(ContentState.STATE_REMOVED)
        content.stateObserver?.onRemoved()
        vw.onContentRemoved()
    }

    override fun removeAll(contents: MutableList<IContent>) {
        view.launchMain {
            vw.removeContents(contents)
            for (content in contents) {
                content.setContentState(ContentState.STATE_REMOVED)
                content.stateObserver?.onRemoved()
            }
            vw.onContentRemoved()
        }
    }

    override fun clear() {
        vw.clearContent()
        for (content in list) {
            content.setContentState(ContentState.STATE_REMOVED)
            content.stateObserver?.onRemoved()
        }
        onPageSelected(-1)
    }


    override fun show(filePath: String) {
        getContentFromFilePath(filePath)?.let {
            show(it)
        }
    }

    override fun show(content: IContent) {
//        view.indicatorManager.startIndicator.vi
//        view.indicatorManager.endIndicator
        show(list.indexOf(content))
    }

    override fun show(index: Int) {
//        loge(index.toString())
        if (index >= 0 && index < list.size) {
            refreshIndicator()
//                tabContentAdapter.notifyDataSetChanged()
            vw.setCurrentContent(index)
            onPageSelected(index)
//            vw.main_tablayout.getTabAt(index)?.select()
        }
    }


    override fun getCurrent(): IContent? {
        return currentContent
    }

    override fun getList(): MutableList<IContent> {
        return vw.getContentList()
    }


    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        currentContent = if (0 <= position && position < list.size) {
            list[position]
        } else {
            refreshIndicator()
            null
        }
        currIndex = position
        refreshQuickMenu()
    }

    private fun getContentFromFilePath(path: String?): IContent? {
        if (path != null)
            for (it in vw.getContentList()) {
                if (it.path == path)
                    return it
            }
        return null
    }

    private fun getPositionFromFilePath(path: String): Int? {
        for ((i, it) in vw.getContentList().withIndex()) {
            if (it.path == path)
                return i
        }
        return null
    }

    fun selectTab(filePath: String): Boolean {
        val p = getPositionFromFilePath(filePath)
        if (p != null) {
            vw.selectContentTab(p)
            return true
        }
        return false
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    @Suppress("INACCESSIBLE_TYPE")
    override fun onTabReselected(tab: TabLayout.Tab?) {
        tab?.let {
            vw.showMenuPopup(current?.controlMenuList, tab.view)
        }
    }


    override fun closeCurrent() {
        val c = current ?: return
        remove(c)
        for (index in currIndex downTo 0) {
            if (index < list.size) {
                show(index)
                break
            }
        }
    }

    override fun closeOther() {
        current?.let {
            val list = ArrayList(vw.getContentList())
            list.remove(it)
            removeAll(list)
        }
    }

    override fun closeAll() {
        clear()
    }


    override fun closeAllToTheLeft() {

    }

    override fun closeAllToTheRight() {
    }


    override fun saveCurrentAsync() {
        view.launchIO {
            ProgressBarSet.BOTTOM_HORIZONTAL.addUser(SAVE_CURRENT)
            saveCurrent()
            ProgressBarSet.BOTTOM_HORIZONTAL.removeUser(SAVE_CURRENT)
        }
    }

    override fun saveAllAsync() {
        view.launchIO {
            ProgressBarSet.BOTTOM_HORIZONTAL.addUser(SAVE_ALL)
            saveAll()
            ProgressBarSet.BOTTOM_HORIZONTAL.removeUser(SAVE_ALL)
        }
    }

    @Throws(Exception::class)
    private fun saveContent(content: IContent) {
        val editor = content.editor
        val ioController = editor.ioController
        if (!editor.dataController.isChanged)
            return
        val path = ioController.currentPath ?: throw RuntimeException("file path null")
        val data = ioController.exportData()
        ioController.writeTo(data, path)
    }

    override fun saveCurrent() {
        val content = current ?: return
        try {
            saveContent(content)
            vw.onContentSaveSuccess(content, content.path)
        } catch (e: Exception) {
            e.printStackTrace()
            vw.onContentSaveFailed(content, content.path, e.message)
        }
    }

    override fun saveAll() {
        for (content in list) {
            try {
                saveContent(content)
            } catch (e: Exception) {
                e.printStackTrace()
                vw.onContentSaveFailed(content, content.path, e.message)
            }
        }

        vw.onContentAllSaved()
    }

    override fun refreshQuickMenu() {
        view.main.toolBarLayoutPresenter.refreshQuickMenu(currentContent?.quickMenuList ?: listOf())
    }

    private fun refreshIndicator() {
        view.indicatorManager.startIndicator.setText("Setting")
        view.indicatorManager.startIndicator.setOnClickListenerDefault()
        view.indicatorManager.startIndicator.setOnLongClickListenerDefault()
        view.indicatorManager.endIndicator.setText("Plugin")
        view.indicatorManager.endIndicator.setOnClickListenerDefault()
        view.indicatorManager.endIndicator.setOnLongClickListenerDefault()
    }

    override fun showSettingWindow(settingList: MutableList<IPreference<*>>) {
        vw.showSettingPopup(settingList)
    }

    override fun showListWindow(contentList: MutableList<IContent>) {
        vw.showContentListPopup(contentList)
    }

    companion object {
        internal const val SAVE_CURRENT = "SAVE_CURRENT"
        internal const val SAVE_ALL = "SAVE_ALL"
    }

}