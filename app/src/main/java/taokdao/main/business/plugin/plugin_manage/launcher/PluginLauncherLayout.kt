package taokdao.main.business.plugin.plugin_manage.launcher

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.lxj.xpopup.impl.PartShadowPopupView
import taokdao.api.plugin.bean.Plugin
import taokdao.api.plugin.bean.PluginVisibility
import taokdao.main.IMainView
import taokdao.main.business.plugin.plugin_manage.PluginManageViewWrapper
import taokdao.main.business.plugin.plugin_manage.launcher.flowtag.PluginLauncherTagAdapter
import taokdao.main.business.plugin.plugin_manage.launcher.list.PluginLauncherAdapter
import taokdao.main.business.plugin.plugin_manage.launcher.list.PluginLauncherItem
import tiiehenry.android.text.SimpleTextWatcher
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.PluginLauncherPopupBinding
import tiiehenry.ktx.content.getWidth
import tiiehenry.ktx.res.getDimen
import tiiehenry.ktx.visibility
import java.util.*


@SuppressLint("ViewConstructor")
class PluginLauncherLayout(val main: IMainView, private val wrapper: PluginManageViewWrapper) : PartShadowPopupView(main.context) {
    lateinit var binding: PluginLauncherPopupBinding
    private var _isCreated: Boolean = false
    private val tagListAll: MutableList<String> = mutableListOf()
    private val tagListSelected: MutableList<String> = mutableListOf()

    //    private val itemManage = PluginItem(Plugin.Information().apply {
//        label = main.getString(R.string.plugin_manage_launcher_pluginitem_manage)
//        icon = main.getDrawable(R.drawable.plugin_manage_launcher_pluginitem_manage2)
//    }, PluginVisibility())
    private val itemGetMore = PluginLauncherItem(Plugin.Information().apply {
        label = main.getString(R.string.plugin_manage_launcher_pluginitem_getmore)
        icon = main.getDrawable(R.drawable.plugin_manage_launcher_pluginitem_getmore)
    }, PluginVisibility())
    private val itemShowAll = PluginLauncherItem(Plugin.Information().apply {
        label = main.getString(R.string.plugin_manage_launcher_pluginitem_showall)
        icon = main.getDrawable(R.drawable.plugin_manage_launcher_pluginitem_showall)
    }, PluginVisibility())
    private val itemShowSupported = PluginLauncherItem(Plugin.Information().apply {
        label = main.getString(R.string.plugin_manage_launcher_pluginitem_showsupported)
        icon = main.getDrawable(R.drawable.plugin_manage_launcher_pluginitem_showsupported)
    }, PluginVisibility())
    private var isExpanded = false
    private val listAdapter = PluginLauncherAdapter(main.pluginLoader)
    private val tagAdapter = PluginLauncherTagAdapter(main)
    override fun getImplLayoutId(): Int = R.layout.plugin_launcher_popup_container

    override fun onCreate() {
        val container = findViewById<FrameLayout>(R.id.fl_plugin_launcher_popup_container)
        container.addView(binding.root, -1, -2)
//        Log.e("PluginLauncheyout", "onCreate: " +parent.parent )
//        (container.parent.parent.parent as View).setOnClickListener { dismiss() }
//        setOnClickListener { dismiss() }
//        attachPopupContainer.setOnClickOutsideListener { dismiss() }
    }

    private var inSearchMode = false
    private fun onSearchModeChanged(isSearchMode: Boolean) {
        inSearchMode = isSearchMode
        binding.ftlTags.visibility(!inSearchMode)
        binding.ivClear.visibility(inSearchMode)
        refreshPluginList(main.pluginManager.pluginList)
    }

    private fun getMoreFromMarket() {
        val id = main.context.applicationInfo.loadLabel(main.context.packageManager)
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$id"))
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=$id"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (intent.resolveActivity(main.context.packageManager) != null) {
            main.startActivity(intent)
        }
    }

    private fun changeExpand(expand: Boolean) {
        isExpanded = expand
//        if (expand) {
//            imgv_show.setImageResource(R.drawable.plugin_manage_launcher_pluginitem_showsupported)
//        } else {
//            imgv_show.setImageResource(R.drawable.plugin_manage_launcher_pluginitem_showall)
//        }
        wrapper.refreshPluginList()
    }

    fun frequencyOfListElements(items: List<String>): Map<String, Int> {
        if (items.isEmpty()) return mutableMapOf()
        val map: MutableMap<String, Int> = HashMap()
        for (temp in items) {
            val count = map[temp]
            map[temp] = if (count == null) 1 else count + 1
        }
        return map
    }

    private fun refreshFlowTags(pluginList: List<Plugin>) {
        if (!_isCreated)
            return
        tagListAll.clear()
        val lists = pluginList.map {
            it.getInformation(main).tags
        }.toMutableList().apply {
//            if (BuildConfig.DEBUG) {
//                add(listOf("Unique", "Engine", "Lua"))
//                add(listOf("安卓社区", "Java社区", "Lua"))
//                add(listOf("Java", "Engine", "Lua"))
//            }
        }.flatten()
        val countMap = frequencyOfListElements(lists)
        val list: List<Map.Entry<String, Int>> = ArrayList(countMap.entries)
        Collections.sort(list) { o1, o2 -> o2.value - o1.value }
        val sortedTagList = list.map { it.key }
        tagListAll.addAll(sortedTagList)
        if (!tagListAll.containsAll(tagListSelected)) {
            tagListSelected.clear()
            binding.ftlTags.setSelectedPositions(listOf())
        }
        val limitTagNumber = 10
        if (tagListAll.size > limitTagNumber)
            binding.ftlTags.clearAndAddTags(tagListAll.subList(0, limitTagNumber))
        else
            binding.ftlTags.clearAndAddTags(tagListAll)
    }

    fun refreshAll() {
        refreshFlowTags(main.pluginManager.pluginList)
        refreshPluginList(main.pluginManager.pluginList)
    }

    private fun refreshPluginList(pluginList: List<Plugin>) {
        if (!_isCreated)
            return
        val filterText = binding.etSearch.text.toString()

        val list = pluginList.asSequence()
                .map {
                    PluginLauncherItem.from(it, main)
                }.filter {
                    it.visibility.isVisible(main.contentManager.current?.id(), main.contentManager.current?.path)
                }.filter {
                    it.information.label.toLowerCase(Locale.getDefault()).contains(filterText.toLowerCase(Locale.getDefault()))
                }.filter {
                    if (inSearchMode) {
                        return@filter true
                    }
                    if (tagListSelected.isEmpty())
                        return@filter true
                    val intersected = it.information.tags intersect tagListSelected
                    return@filter intersected.isNotEmpty()
                }
                .toMutableList()
        list.ifEmpty {
            list.add(itemGetMore)
        }
        if (isExpanded) {
            list.add(itemShowSupported)
        } else {
            list.add(itemShowAll)
        }
        listAdapter.refresh(list)
    }

    fun initLayout() {
        binding = PluginLauncherPopupBinding.inflate(main.layoutInflater)
//        layout.widthAndHeight(-1, -2)
        _isCreated = true
        binding.etSearch.addTextChangedListener(SimpleTextWatcher.newAfterWatcher {
            onSearchModeChanged(it.isNotEmpty())
        })
        changeExpand(isExpanded)
        binding.ibtnManage.setOnClickListener {
        }
        binding.ivRefresh.setOnClickListener {
            wrapper.startReloadPluginTask()
        }
        binding.ivClear.setOnClickListener {
            binding.etSearch.setText("")
        }
        binding.rvAll.apply {
//            layoutManager = GridLayoutManager(main.context, 6)
            layoutManager = GridLayoutManager(context, context.getWidth() / context.getDimen(R.dimen.plugin_launcher_list_all_item_W))
            adapter = listAdapter
        }

        listAdapter.apply {
            setOnItemClickListener { _, item, _ ->
                when (item) {
                    itemGetMore -> {
                        getMoreFromMarket()
                    }
                    itemShowSupported, itemShowAll -> {
                        changeExpand(!isExpanded)
                    }
                    else -> {
                        item.plugin?.onCall()
                        wrapper.hidePluginLauncherPopup()
                    }
                }
            }
            setOnItemLongClickListener { _, item, _ ->
                when (item) {
                    itemShowSupported, itemShowAll -> {
                    }
                    itemGetMore -> {
                    }
                    else -> {
                        item.plugin?.id?.let { main.pluginInstaller.requestUninstall(it) }
                    }
                }

            }
        }
        binding.ftlTags.apply {
            defaultHeight = main.dp2px(10F).toInt()
            adapter = tagAdapter
            setOnTagSelectListener { _, _, selectedList ->
                tagListSelected.clear()
                for (i in selectedList) {
                    tagListSelected.add(tagAdapter.getItem(i))
                }
                refreshPluginList(main.pluginManager.pluginList)
            }
        }
        refreshAll()
    }

}