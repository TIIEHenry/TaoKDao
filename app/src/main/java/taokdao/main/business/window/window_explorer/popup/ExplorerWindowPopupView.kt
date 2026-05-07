package taokdao.main.business.window.window_explorer.popup

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.lxj.xpopup.core.DrawerPopupView
import taokdao.api.ui.explorer.IExplorer
import taokdao.api.ui.explorer.menu.ExplorerMenu
import taokdao.main.IMainView
import taokdao.main.business.window.window_explorer.explorermenu.ExplorerDisplayMenuAdapter
import taokdao.main.business.window.window_explorer.explorermenu.ExplorerExpandedMenuPopup
import tiiehenry.ktx.app.getStatusBarHeight
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ExplorerLayoutBinding
import tiiehenry.ktx.widthAndHeight
import tiiehenry.taokdao.ui.setting.LeftTopSettingPopup

@SuppressLint("ViewConstructor")
class ExplorerWindowPopupView(val main: IMainView) : DrawerPopupView(main.context) {
    val binding by lazy { ExplorerLayoutBinding.inflate(main.layoutInflater) }

    private val fragmentAdapter = ExplorerFragmentAdapter(main.activity.supportFragmentManager)

    private val spinnerAdapter = ExplorerSpinnerAdapter()
    override fun getImplLayoutId(): Int = R.layout.explorer_layout_container
    override fun onCreate() {
//        addView(layout)
        findViewById<FrameLayout>(R.id.fl_explorer_container).addView(binding.root)
    }

    fun addExplorer(iExplorer: IExplorer, select: Boolean) {
        fragmentAdapter.add(iExplorer)
        spinnerAdapter.add(iExplorer)
        if (select)
            selectExplorer(iExplorer)
    }

    fun removeExplorer(iExplorer: IExplorer): Boolean {
        val b = fragmentAdapter.contains(iExplorer)
        fragmentAdapter.remove(iExplorer)
        spinnerAdapter.remove(iExplorer)
        return b
    }

    fun clearExplorer() {
        fragmentAdapter.clear()
        spinnerAdapter.clear()
    }

    fun getExplorerList(): MutableList<IExplorer> {
        return fragmentAdapter.dataList
    }

    fun selectExplorer(data: IExplorer) {
        val pos = fragmentAdapter.getPosition(data)
        if (pos != -1) {
            selectExplorer(pos)
        }
    }

    private fun selectExplorer(index: Int) {
        if (index < fragmentAdapter.dataList.size) {
            binding.explorerToolbarSpinner.setSelection(index)
            binding.explorerContainerVp.currentItem = index
            setCurrentMenus(fragmentAdapter.dataList[index].menuList)
        }
    }

    fun getCurrentExplorer(): IExplorer? {
        val currentItem = binding.explorerContainerVp.currentItem
        if (currentItem in 0 until fragmentAdapter.dataList.size)
            return fragmentAdapter.dataList[currentItem]
        return null
    }

    private fun initWindow() {
        binding.explorerContainerVp.adapter = fragmentAdapter
        binding.explorerContainerVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                selectExplorer(position)
            }
        })
        binding.explorerToolbarMenuMoreIv.setOnClickListener(this::showMoreMenuPopup)
        binding.explorerToolbarSpinner.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectExplorer(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            setOnLongClickListener {
                val settingList = getCurrentExplorer()?.settingList
                        ?: return@setOnLongClickListener false
                if (settingList.isEmpty()) {
                    return@setOnLongClickListener false
                }
                LeftTopSettingPopup(main, settingList, it).showAt()
                true
            }
        }
        binding.explorerToolbarMenuRv.apply {
            adapter = displayMenuAdapter
            layoutManager = LinearLayoutManager(main.activity, RecyclerView.HORIZONTAL, false).apply {
                stackFromEnd = true
            }
        }
        binding.vExplorerStatusbar.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, main.activity.getStatusBarHeight())
//        layout.window_explorer_float_ibtn.setOnClickListener {
//        }
    }

    val showMenuCount = 3

    private val moreMenuList = mutableListOf<ExplorerMenu>()
    private val displayMenuAdapter = ExplorerDisplayMenuAdapter()

    private fun setCurrentMenus(menus: MutableList<ExplorerMenu>) {
        moreMenuList.clear()
        if (menus.size > showMenuCount) {
            displayMenuAdapter.refresh(menus.subList(0, showMenuCount - 1))
            binding.explorerToolbarMenuMoreIv.visibility = View.VISIBLE
            moreMenuList.addAll(menus.subList(showMenuCount, menus.size))
        } else {
            displayMenuAdapter.refresh(menus)
            binding.explorerToolbarMenuMoreIv.visibility = View.GONE
        }
    }

    private fun showMoreMenuPopup(view: View) {
        ExplorerExpandedMenuPopup(main).apply {
            updateDataList(moreMenuList)
        }.showAt(view)
    }


    fun updateHeight() {
        binding.root.widthAndHeight(main.dp2px(300f).toInt(), -1)
    }

    fun initLayout() {
        binding.root.widthAndHeight(main.dp2px(300f).toInt(), -1)
        initWindow()
    }


}