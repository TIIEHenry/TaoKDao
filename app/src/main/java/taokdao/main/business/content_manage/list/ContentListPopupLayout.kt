package taokdao.main.business.content_manage.list

import com.lxj.xpopup.impl.PartShadowPopupView
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.IContent
import taokdao.main.business.content_manage.ContentManageViewWrapper
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ContentContentlistPopupBinding

class ContentListPopupLayout(val main: IMainContext, private val contentManageViewWrapper: ContentManageViewWrapper) : PartShadowPopupView(main.context) {
    private val contentListAdapter = ContentListAdapter(main)

    override fun getImplLayoutId(): Int = R.layout.content_contentlist_popup

    override fun onCreate() {
        val binding = ContentContentlistPopupBinding.bind(popupContentView)
        binding.contentContentlistRv.apply {
            adapter = contentListAdapter
            isVerticalScrollBarEnabled = false
        }
        contentListAdapter.apply {
            setOnItemClickListener { _, item, _ ->
                contentManageViewWrapper.hideContentListPopup()
                main.contentManager.show(item)
            }
            setOnItemLongClickListener { _, item, _ ->
                main.notify(item.path)
            }
        }
    }


    fun refresh(dataList: List<IContent>) {
        contentListAdapter.refresh(dataList)
    }

}
