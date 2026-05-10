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
        // XPopup PartShadowPopupView 的 popupContentView 是 PartShadowContainer（FrameLayout），
        // 用户布局 ConstraintLayout 被 inflate 为其子 view，因此不能直接 bind popupContentView。
        // 通过 RecyclerView 的 parent 找到实际的 ConstraintLayout 根视图。
        val recyclerView = popupContentView.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.content_contentlist_rv)
        val binding = ContentContentlistPopupBinding.bind(recyclerView.parent as androidx.constraintlayout.widget.ConstraintLayout)
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
