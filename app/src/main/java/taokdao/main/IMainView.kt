package taokdao.main

import kotlinx.coroutines.*
import taokdao.api.data.mmkv.IMMKV
import taokdao.api.main.IMainContext
import taokdao.main.handler.MainHandler
import taokdao.window.toolpages.TabToolInternal

interface IMainView : IMainContext, CoroutineScope {

    val main: MainActivity

    val mainHandler: MainHandler

    val tabToolInternal: TabToolInternal
    override fun runOnMain(runnable: Runnable) {
        launchMain { runnable.run() }
    }

    fun launchMain(start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit): Job {
        return launch(Dispatchers.Main, start, block)
    }

    override fun runOnIO(runnable: Runnable) {
        launchIO { runnable.run() }
    }

    fun launchIO(start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit): Job {
        return launch(Dispatchers.IO, start, block)
    }


    fun getMMKV(): IMMKV


}