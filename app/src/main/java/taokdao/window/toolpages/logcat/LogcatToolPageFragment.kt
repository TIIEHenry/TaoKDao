package taokdao.window.toolpages.logcat


import android.util.SparseArray
import androidx.core.util.forEach
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taokdao.api.event.senders.TabToolSender
import taokdao.api.internal.InnerIdentifier
import taokdao.api.ui.base.PanelProp
import taokdao.api.ui.toolpage.fragment.ToolPageBindingFragment
import taokdao.api.ui.toolpage.menu.ToolPageMenu
import taokdao.main.IMainView
import taokdao.window.toolpages.logcat.adapter.LogItemAdapter
import taokdao.window.toolpages.logcat.data.LogLevels
import taokdao.window.toolpages.logcat.data.LogLine
import taokdao.window.toolpages.logcat.data.LogLineParser
import taokdao.window.toolpages.logcat.reader.LogcatReaderTask
import tiiehenry.android.text.SimpleTextWatcher
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ToolpagesLogcatBinding
import tiiehenry.ktx.content.getWidth
import java.io.File
import java.util.Locale

class LogcatToolPageFragment(val main: IMainView) : ToolPageBindingFragment<ToolpagesLogcatBinding>(
    PanelProp(
        InnerIdentifier.ToolGroup.LOGCAT, main.context, R.string.toolpages_logcat_label,
        R.drawable.toolpages_logcat_icon
    )
) {

    private var toggleStatePageMenu: ToolPageMenu
    private var toggleSuPageMenu: ToolPageMenu

    private val sender = TabToolSender(this)

    private var filterTag: String = ""
    private var filterText: String = ""
    private var filterProcess: Int = -1
    private val logAdapter: LogItemAdapter = LogItemAdapter(main)

    init {
        toggleStatePageMenu =
            ToolPageMenu(main.getDrawable(R.drawable.toolpages_logcat_menu_start)) {
                toggleLogReaderState()
            }
        menuList.add(
            ToolPageMenu(
                main.getDrawable(R.drawable.toolpage_public_menu_clear),
                main.getString(R.string.toolpages_logcat_menu_clear)
            ) {
                clearLog()
            }.apply {
                //                        this.showLabel=false
            })
        menuList.add(toggleStatePageMenu)
        menuList.add(ToolPageMenu(main.getDrawable(R.drawable.toolpages_logcat_menu_stop)) {
            stopLogCat()
        }.apply {
        })
        toggleSuPageMenu =
            ToolPageMenu(main.getDrawable(R.drawable.toolpages_logcat_menu_refresh), null) {
                stopLogCat()
                if (logcatReaderTask.su) {
                    sender.message(getString(R.string.toolpages_logcat_event_su_disabled))
                        .send(main)
                } else {
                    sender.message(getString(R.string.toolpages_logcat_event_su_enabled)).send(main)
                }
                logcatReaderTask.su = !logcatReaderTask.su
                clearAdapterAndRecorded()
            }.apply {
                this.showLabel = false
            }
        menuList.add(toggleSuPageMenu)
        menuList.add(
            ToolPageMenu(
                main.getDrawable(R.drawable.toolpage_public_menu_to_bottom),
                null
            ) {
                toBottom()
            }.apply {
                this.showLabel = false
            })
//        textSizeProcess = main.sp2px(12f)
//        textSizeMsg = main.sp2px(14f)
    }

    override fun initView(binding: ToolpagesLogcatBinding) {
        rv = binding.rvToolpagesLogcat.apply {
            minimumWidth = main.activity.getWidth()
            adapter = logAdapter
            itemAnimator = DefaultItemAnimator().apply { addDuration = 100 }
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
        binding.toolpagePublicZoomControls.ivZoomIn.setOnClickListener { logAdapter.zoomIn() }
        binding.toolpagePublicZoomControls.ivZoomOut.setOnClickListener { logAdapter.zoomOut() }
        binding.tvToolpagesLogcatTag.apply {
            setOnClickListener {
                val allTagsList = allTags.toMutableList().apply {
                    sortBy { it.toUpperCase(Locale.getDefault()) }
                }
                val items = allTagsList.map { it as CharSequence }
                Dialogs.global
                    .asList()
                    .typeRegular()
                    .title(R.string.toolpages_logcat_filter_title)
                    .items(items)
                    .itemsCallback { dialog, position, tag ->
                        dialog.dismiss()
                        resetTag(tag)
                        text = tag
                    }
                    .neutralText(R.string.toolpages_logcat_filter_clear)
                    .onNeutral {
                        resetTag(tagFilterClear)
                        text = ""
                    }
                    .negativeText()
                    .show()
            }
            addTextChangedListener(SimpleTextWatcher.newAfterWatcher {
                resetTag(it)
            })
        }
        binding.tvToolpagesLogcatLevel.apply {
            setOnClickListener {
                val keys = LogLevels.levelMap.keys.toMutableList()

                val items = LogLevels.levelMap.map { it.value as CharSequence }.toMutableList()
                items.removeAt(0)
                Dialogs.global
                    .asList()
                    .typeRegular()
                    .title(R.string.toolpages_logcat_filter_title)
                    .items(items)
                    .itemsCallback { dialog, position, tag ->
//                BottomSheet.BottomListSheetBuilder(main.activity).apply {
//                    for (key in keys) {
//                        addItem(LogLevels.levelMap[key])
//                    }
                        dialog.dismiss()
                        filterLevel = keys[position + 1]
                        resetLogAdapter()
                        text = tag
                    }
                    .neutralText(R.string.toolpages_logcat_filter_clear)
                    .onNeutral {
                        filterLevel = -1
                        resetLogAdapter()
                        text = ""
                    }
                    .negativeText()
                    .show()
            }
        }
        binding.tvToolpagesLogcatProcess.apply {
            setOnClickListener {
                val processList = allProcess.toMutableList()
//                processList.removeAt(0)
                val items = mutableListOf<CharSequence>()
                for (pid in processList) {
                    if (pid != 0) {
                        File("/proc/$pid/cmdline").let {
                            if (it.canRead())
                                items.add(it.readText().trim() + "(" + pid.toString() + ")")
                            else
                                items.add(pid.toString())
                        }
                    } else
                        items.add(pid.toString())
                }
                Dialogs.global
                    .asList()
                    .typeRegular()
                    .title(R.string.toolpages_logcat_filter_title)
                    .items(items)
                    .itemsCallback { dialog, position, tag ->
//                            processList.add(0, -1)
                        dialog.dismiss()
                        filterProcess = processList[position]
                        resetLogAdapter()
                        text = tag
                    }
                    .neutralText(R.string.toolpages_logcat_filter_clear)
                    .onNeutral {
                        filterProcess = -1
                        resetLogAdapter()
                        text = ""
                    }
                    .negativeText()
                    .show()
            }
        }

        binding.etToolpagesLogcatFilter.apply {
            setOnClickListener {
                Dialogs.global
                    .asInput()
                    .title(R.string.toolpages_logcat_filter_title)
                    .allowEmptyInput(false)
                    .input(
                        getString(R.string.toolpages_logcat_filter_hint),
                        ""
                    ) { _, input ->
                        resetFilter(input.toString())
                        setText(input.toString())
                    }
                    .negativeText()
                    .positiveText()
                    .show()
            }
            addTextChangedListener(SimpleTextWatcher.newAfterWatcher { input ->
                resetFilter(input)
            })

        }


    }

    private fun resumeLogReaderState() {
        if (logcatReaderTask.isStarted()) {
            logcatReaderTask.resume()
            toggleStatePageMenu.icon = main.getDrawable(R.drawable.toolpages_logcat_menu_pause)
            main.toolPageWindow.refreshMenu()
            shouldPause = false
        }
    }

    private fun stopLogCat() {
        logcatReaderTask.stop()
        toggleStatePageMenu.icon = main.getDrawable(R.drawable.toolpages_logcat_menu_start)
        main.toolPageWindow.refreshMenu()
    }

    private fun pauseLogReaderState() {
        if (logcatReaderTask.isStarted()) {
            logcatReaderTask.pause()
            toggleStatePageMenu.icon = main.getDrawable(R.drawable.toolpages_logcat_menu_start2)
            main.toolPageWindow.refreshMenu()
            shouldPause = true
        }
    }

    private fun clearAdapterAndRecorded() {
        logAdapter.clear()
        synchronized(dataListMap) {
            dataListMap.forEach { i, mutableList -> mutableList.clear() }
        }
    }

    private fun createAndStartLogReader() {
        clearAdapterAndRecorded()
        logcatReaderTask.create()
        logcatReaderTask.start()
        resumeLogReaderState()
    }

    private fun toggleLogReaderState() {
        if (logcatReaderTask.isStarted()) {
            if (logcatReaderTask.isPaused()) {
                resumeLogReaderState()
            } else {
                pauseLogReaderState()
            }
        } else {
            createAndStartLogReader()
        }
    }


//    private var readLogThread = ReadLogThread(this).apply {
//        start()
//    }

    var filterLevel: Int = -1

    private val dataListMap = SparseArray<MutableList<LogLine>>().apply {
        LogLevels.levelMap.keys.forEach { s -> this.put(s, mutableListOf()) }
    }
    private val allTags = hashSetOf<String>()
    private val allProcess = hashSetOf<Int>()

    private lateinit var rv: RecyclerView
    private var logcatReaderTask: LogcatReaderTask =
        LogcatReaderTask(main, this::parseAndAddLogLine, sender, false)
    private val tagFilterClear = main.getString(R.string.toolpages_logcat_filter_clear)

    private fun resetTag(s: String) {
        var text = ""
        if (s != tagFilterClear)
            text = s
        filterTag = text
        resetLogAdapter()
    }

    private fun resetFilter(text: String) {
        filterText = text
        resetLogAdapter()
    }

    fun refresh() {
        resetLogAdapter()
    }

    fun toBottom() {
        rv.stopScroll()
        (rv.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            logAdapter.size() - 1,
            0
        )
    }

    fun clearLog() {
        Dialogs.global
            .asConfirm()
            .title(R.string.toolpages_logcat_label)
            .content(R.string.toolpages_logcat_action_clear_logcat_dialog_content)
            .negativeText()
            .positiveText()
            .onPositive { _ ->
//                    readLogThread.interrupt()
                stopLogCat()
                main.launchIO {
                    logcatReaderTask.clearLogCat()
                }
                clearAdapterAndRecorded()
//                    readLogThread = ReadLogThread(this).apply {
//                        start()
//                    }
            }
            .show()
    }


    private fun resetLogAdapter() {
//        sender.message("resetLogAdapter:$level").log(main)
        val list = dataListMap[filterLevel]
        allTags.clear()
        allProcess.clear()
        synchronized(list) {
            val newList = mutableListOf<LogLine>()
            for (it in list) {
                if (filterProcess == -1 || it.processId == filterProcess) {
                    if (it.logOutput.contains(filterText)) {
                        if (it.tag.contains(filterTag))
                            newList.add(it)
                    }
                }
                allTags.add(it.tag)
                allProcess.add(it.processId)
            }

            val subedList = if (newList.size - 1 > 300) {
                val l = newList.subList(newList.size - 1 - 300, newList.size - 1)
//                list.clear()
                dataListMap.forEach<MutableList<LogLine>>({ i, mutableList ->
                    val start = list.indexOf(newList.first())
                    if (start >= 0) {
                        mutableList.removeAll(mutableList.subList(0, start))
                    }
                })
//                dataListMap.put(level, list.subList(list.indexOf(newList.first()), list.size - 1))
                l
            } else
                newList
            main.launchMain {
                logAdapter.refresh(subedList)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        resetLogAdapter()
    }

    private var shouldPause = false

    override fun onPause() {
        super.onPause()
        if (!logcatReaderTask.isPaused() && !shouldPause) {
            pauseLogReaderState()
            shouldPause = false
        }
    }

    override fun onResume() {
        super.onResume()
        if (logcatReaderTask.isPaused() && !shouldPause) {
            resumeLogReaderState()
//            shouldPause = true
        }
    }

    fun getLevelInt(s: String): Int {
        return when (s) {
            "V" -> 9
            "D" -> 8
            "I" -> 7
            "W" -> 6
            "E" -> 5
            "A" -> 4
            else -> 0
        }
    }


    private fun parseAndAddLogLine(line: String) {
//        sender.message("logLine.originalLine").send(main)
//        loge("addLog: "+line)
        val logLine = LogLineParser.parseLogLine(line)
        addLogItem(logLine)
    }

    private fun addLogItem(log: LogLine) {
//        if (filterLevel <= log.logLevel) {
//            synchronized(logAdapter) {
//                main.launchMain {
//                    if (logAdapter.size() > 500) {
//                        logAdapter.delete(0)
//                    }
//                    logAdapter.add(log)
//                }
//            }
//        }
//            loge("addLogItem: ")
        for (s in LogLevels.levelMap) {
            if (s.key <= log.logLevel) {
                val list = dataListMap[s.key]
                synchronized(list) {
                    if (list.size > 299) {
                        list.removeAt(0)
                    }
                    list.add(log)
                }
            }
        }
        resetLogAdapter()

    }

    private fun shouldAddToList(listLevel: String, logLevel: String): Boolean {
        return getLevelInt(logLevel) <= getLevelInt(listLevel)
    }

    private fun getLogDataFromLine(line: String): LogItem {
        try {
            val s = line.indexOf(':', 18)
            if (s >= 0) {
                val time = line.substring(6, 14)
                val level = line.substring(19, 20)
                val pcs = line.substring(21, s)
                val s2 = pcs.indexOf('(')
                var tag = ""
                if (s2 >= 0) {
                    tag = pcs.substring(0, s2)
                }
                val msg = line.substring(s + 1, line.length)
                return LogItem(level, tag, "$level $time $pcs", "$tag $msg")
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return LogItem("V", "", null, line)
    }
}