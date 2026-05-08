package taokdao.main.business.window.window_toolpage.popup

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.contentView
import org.jetbrains.anko.find
import taokdao.api.ui.toolpage.IToolPage
import taokdao.api.ui.toolpage.container.IToolTabContainer
import taokdao.api.ui.toolpage.container.IToolTabProvider
import taokdao.api.ui.toolpage.menu.ToolPageMenu
import taokdao.main.IMainView
import taokdao.main.business.window.window_toolpage.ToolPageWindowViewWrapper
import taokdao.window.toolpages.builder.TabChooserAdapter
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.android.view.recyclerview.adapter.SimpleIdRecyclerAdapter
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder
import tiiehenry.ktx.gone
import tiiehenry.ktx.visibility
import tiiehenry.ktx.visible
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ToolpageLayoutBinding
import tiiehenry.taokdao.ui.setting.LeftTopSettingPopup

@SuppressLint("ViewConstructor")
class ToolPageWindowPopupView(val main: IMainView, val vw: ToolPageWindowViewWrapper) {
    private val container: FrameLayout = main.activity.findViewById(R.id.cl_toolpage_container)
    val layout: ToolpageLayoutBinding by lazy {
        ToolpageLayoutBinding.inflate(main.layoutInflater)
    }

    init {
//        vw.addStateObserver(object : BaseWindowStateObserver<IToolPageWindow>() {
//            override fun onWindowShow(pageWindow: IToolPageWindow) {
//                Log.e("TabToolWindowPopupView", "onWindowShow: " + "")
//            }
//
//            override fun onWindowHide(pageWindow: IToolPageWindow) {
////                isMaxHeight = false
////                updateMax()
//            }
//        })
    }

    private val menuAdapter = ToolPageMenuAdapter()

    private val fragmentAdapter = ToolPageFragmentAdapter(main.activity.supportFragmentManager)
    private val toolTabBinder = TabChooserAdapter(main)


    private val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab == null) {
                return
            }
            val toolGroup = tab.tag as? IToolPage ?: return
            val isContainer = toolGroup is IToolTabContainer<*>

            layout.vpToolpageContainer.currentItem = tab.position

            val list = toolGroup.menuList.filter { it.displayType == ToolPageMenu.DisplayType.BAR }
            menuAdapter.refresh(list.reversed())

            val label = layout.tvToolpageToolbarMenuLabel
            label.text = toolGroup.prop.label
            //                    iconImgv.setImageDrawable(data.icon)
            //                    titleTxtv.text = data.label
            label.visibility(!isContainer)

            layout.spToolpageToolbarTabs.visibility(isContainer)

            val newTabView = layout.ivToolpageToolbarNewtab
            newTabView.visibility(isContainer)
            toolTabBinder.attachToolGroup(toolGroup)

            if (toolGroup is IToolTabContainer<*>) {
                toolGroup.attachAdapter(toolTabBinder)
                newTabView.visibility(toolGroup.toolTabProviderList.isNotEmpty())
            } else {
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

    private var isMaxHeight = false

    fun onCreate() {
        layout.rvToolpageToolbarMenu.apply {
            layoutManager = LinearLayoutManager(main.activity, RecyclerView.HORIZONTAL, true)
            adapter = menuAdapter
        }
        layout.tabLayoutToolpage.apply {
            addOnTabSelectedListener(tabSelectedListener)
        }
        toolTabBinder.attachSpinner(layout.spToolpageToolbarTabs)

        layout.ivToolpageToolbarNewtab.setOnClickListener {
            val toolGroup = getCurrentTabTool() ?: return@setOnClickListener
            if (toolGroup !is IToolTabContainer<*>)
                return@setOnClickListener
            var dialog: IDialog? = null
            val adapter = object : SimpleIdRecyclerAdapter<IToolTabProvider>(R.layout.toolpage_toolgroup_tooltab_provider_item, toolGroup.toolTabProviderList) {
                override fun bindData(holder: RecyclerViewHolder, pos: Int, item: IToolTabProvider) {
                    holder.image(R.id.iv_icon, item.icon)
                    holder.text(R.id.tv_label, item.label)
                }
            }.apply {
                setOnItemClickListener { view, iToolTabProvider, i ->
                    (toolGroup as IToolTabContainer<Any>).add(iToolTabProvider.provideNewToolTab())
                    dialog?.dismiss()
                }
            }
            dialog = Dialogs.global
                    .asList()
                    .typeCustom()
                    .title(R.string.business_toolpage_window_newtooltab_dialog_title)
                    .adapter(adapter)
                    .positiveText()
                    .show()
        }
        layout.vpToolpageContainer.apply {
            adapter = fragmentAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layout.tabLayoutToolpage))
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {

                }
            })
//            offscreenPageLimit = 10//防止频繁的销毁视图的解决方案

        }

        updateMax()
        layout.ivToolpageToolbarMenuMax.setOnClickListener {
            isMaxHeight = !isMaxHeight
            updateMax()
        }
        layout.ivToolpageToolbarMenuClose.setOnClickListener {
            main.toolPageWindow.hideWindow()
            main.contentManager.current?.editor?.requestFocus()
        }
        layout.llToolpageToolbarMenu.setOnClickListener { v ->
            getCurrentTabTool()?.settingList?.let { settings ->
                LeftTopSettingPopup(main, settings, v).apply {
                    setPositionOffsetYWhenTop(v.height)
                }.showAt()
            }
        }

        main.toolPageWindow.apply {
            add(main.tabToolInternal.eventTabToolFragment, false)
            add(main.tabToolInternal.buildTabFragment, false)
            add(main.tabToolInternal.errorTabFragment, false)
            add(main.tabToolInternal.searchTabFragment, false)
            add(main.tabToolInternal.logcatTabFragment, false)
        }
        layout.vpToolpageContainer.currentItem = 0
    }


    fun updateMax() {
        layout.ivToolpageToolbarMenuMax.setImageResource(if (isMaxHeight) R.drawable.toolpage_menu_max_min else R.drawable.toolpage_menu_max_max)
        layout.ivToolpageToolbarMenuMax.invalidate()
//        layout.v_explorer_statusbar.visibility(isMaxHeight)
//        layout.cl_toolpage.height(if (isMaxHeight) -1 else (main.height * .618).toInt())
        if (isMaxHeight) {
            vw.setPeekHeightMax()
        } else {
            vw.setPeekHeightDefault()
        }
    }

//
//    override fun getMinimumHeight(): Int {
//        return (XPopupUtils.getWindowHeight(context) * (1 - .618)).toInt()
//    }
//
//    override fun getPopupHeight(): Int {
//        return (XPopupUtils.getWindowHeight(context) * .618).toInt()
//    }
//
//    override fun getMaxHeight(): Int {
//        return (XPopupUtils.getWindowHeight(context) - main.activity.getStatusBarHeight())
//    }

    private fun newTabView(data: IToolPage): TabLayout.Tab {
        val v = View.inflate(main.context, R.layout.toolpage_tooltab_spinner_item, null)
        v.find<ImageView>(R.id.iv_icon).setImageDrawable(data.prop.icon)
        v.find<TextView>(R.id.tv_label).text = data.prop.label
        return layout.tabLayoutToolpage.newTab().apply {
            tag = data
            customView = v
        }
    }

    fun addTabTool(iToolPage: IToolPage, select: Boolean) {
        if (fragmentAdapter.contains(iToolPage)) {
            return
        }
        fragmentAdapter.add(iToolPage)
        newTabView(iToolPage).apply {
            layout.tabLayoutToolpage.addTab(this).apply {
                if (select)
                    select()
            }
        }
    }

    fun removeTabTool(iToolPage: IToolPage): Boolean {
        val result = fragmentAdapter.contains(iToolPage)
        fragmentAdapter.remove(iToolPage)
        val pos = fragmentAdapter.getPosition(iToolPage)
        if (pos < 0)
            return false
        layout.tabLayoutToolpage.getTabAt(pos)?.let {
            layout.tabLayoutToolpage.removeTab(it)
        }
        return result
    }

    fun clearTabTool() {
        fragmentAdapter.clear()
        layout.tabLayoutToolpage.removeAllTabs()
    }

    fun selectTabTool(iToolPage: IToolPage) {
        val pos = fragmentAdapter.getPosition(iToolPage)
        if (pos < 0)
            return
        layout.tabLayoutToolpage.getTabAt(pos)?.select()
    }

    fun getTabToolList(): MutableList<IToolPage> {
        return fragmentAdapter.dataList
    }

    fun getCurrentTabTool(): IToolPage? {
        val current = layout.vpToolpageContainer.currentItem
        val list = getTabToolList()
        if (current in 0 until list.size)
//        if (drawer_bottom_rv.currentItem >= 0 && getTabToolList().size > drawer_bottom_rv.currentItem)
            return list[current]
        return null
    }

    fun refreshMenu() {
        return menuAdapter.notifyDataSetChanged()
    }

    fun initLayout() {
        onCreate()
    }

    fun show(showListener: DialogInterface.OnShowListener) {
        if (isShow())
            return
        if (isPeekHeightMax)
            main.contentManager.current?.editor?.imeController?.hideSoftInput()

        container.visible()
        container.addView(layout.root, -1, -1)
        showListener.onShow(null)
        startAnimEnter()
    }

    private fun startAnimEnter() {
        val alphaAnimation = AnimationUtils.loadAnimation(main.context,
                R.anim.cl_toolpage_container_enter)
        container.startAnimation(alphaAnimation)
    }

    private fun startAnimExit(dismissListener: DialogInterface.OnDismissListener) {
        val alphaAnimation = AnimationUtils.loadAnimation(main.context,
                R.anim.cl_toolpage_container_exit)
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                container.let {
                    it.post {
                        doHide(dismissListener)
                    }
                }
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        container.startAnimation(alphaAnimation)
    }

    fun doHide(dismissListener: DialogInterface.OnDismissListener) {
        container.gone()
        container.removeAllViews()
        dismissListener.onDismiss(null)
    }

    fun hide(dismissListener: DialogInterface.OnDismissListener) {
        if (!isShow())
            return
        startAnimExit(dismissListener)
    }

    fun isShow(): Boolean {
        return layout.root.isShown
    }

    fun changeHeight(h: Int) {
//        Log.e("ToolPageWind", "changeHeight: " + h)
        container.layoutParams = container.layoutParams.apply { height = h }
    }

    var isPeekHeightMax = false
    fun setPeekHeightDefault() {
        if (main.height > main.width)
            changeHeight((main.width * 1.2).toInt())
        else
            changeHeight((main.height * 0.4).toInt())
        isPeekHeightMax = false
//        drawerPopup.setPeekHeight(res = R.dimen.toolpage_peek_height)
    }

    fun setPeekHeightMax() {
        main.contentManager.current?.editor?.imeController?.hideSoftInput()

//        popupView.changeHeight(view.height)
        changeHeight((main.activity.contentView!!.measuredHeight - (main.activity.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.main_toolbar_quickmenu_rv)?.measuredHeight ?: 0)))
//        drawerPopup.setPeekHeight(literal = view.height)

        isPeekHeightMax = true
    }

}