package taokdao.window.toolpages.logcat.reader

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import taokdao.api.event.senders.TabToolSender
import taokdao.main.IMainView
import taokdao.window.toolpages.logcat.RuntimeHelper
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class LogcatReaderTask(val main: IMainView, val onReadInputLine: (String) -> Unit, private val sender: TabToolSender, var su: Boolean = false, private val cmd: List<String> = listOf("logcat", "-v", "time")) {
    //    private var bufferedReader: BufferedReader?=null
    private var process: Process? = null
    private var started: Boolean = false
    private var mPaused: Boolean = false
    private var isAfterLastLine: Boolean = true
    private val lock = Object()

    private var processJob: Job? = null
    private var inputStreamJob: Job? = null
    private var lastItem: String? = null
    val readSelfCmd = listOf("logcat", "-v", "time")

    //    "logcat", "${taokdao.tabtool.logcat.reader.filterProcess}:${taokdao.tabtool.logcat.reader.filterLevel}"
//    , "-v", "time"
    //                                , "-t", "300"
    fun create() {
        processJob = main.launch(Dispatchers.IO, start = CoroutineStart.LAZY) {
            sender.message("LogcatReaderTask start").log(main)
            process = getProcess(cmd, su)?.apply {
                inputStreamJob = launch(Dispatchers.Default) {
                    readInputStream(inputStream)
                }
                //            errorStreamJob = launch(Dispatchers.Default) { readErrorStream(process.errorStream) }
                waitFor()
                sender.message("LogcatReaderTask finish").log(main)
                destroyProcess()
                restart()
//                started = false
            }
        }
    }

    fun getProcess(cmd: List<String>, su: Boolean): Process? {
        if (!su)
            try {
                return ProcessBuilder()
                        .command(cmd)
//                    .redirectErrorStream(true)
                        .start()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        try {
            return RuntimeHelper.exec(cmd)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun clearLogCat() {
        getProcess(listOf("logcat", "-c"), su)?.apply {
            waitFor()
            destroyProcess(this)
        }
    }

    fun destroyProcess() {
        destroyProcess(process)
        process = null
    }

    fun destroyProcess(pcs: Process?) {
        pcs?.let {
            if (su)
                RuntimeHelper.destroy(it)
            else
                it.destroy()
        }
    }

    /**
     * auto restart
     */
    fun restart() {
        if (started) {
            create()
            isAfterLastLine = false
            start()
        }
    }

    fun isStarted(): Boolean {
        return started
    }


    fun start() {
        if (!started) {
            started = true
            processJob?.start()
            inputStreamJob?.start()
//            destroyProcess()
        }
    }

    fun stop() {
        if (started) {
            sender.message("LogcatReaderTask stop").log(main)
            started = false
            pause()
            inputStreamJob?.cancel()
            processJob?.cancel()
            main.launchIO {
                destroyProcess()
            }
        }
    }

    fun isPaused(): Boolean {
        return mPaused
    }

    fun pause() {
        if (started) {
            sender.message("LogcatReaderTask pause").log(main)
            mPaused = true
//        lock.lock
        }
    }

    fun resume() {
        if (started) {
            if (mPaused) {
                sender.message("LogcatReaderTask resume").log(main)
                mPaused = false
                synchronized(lock) {
                    lock.notify()
                }
            }
        }
    }

    private fun readInputStream(inputStream: InputStream) {
        sender.message("LogcatReaderTask readInputStream start").log(main)
        try {
            val bufferedReader = BufferedReader(InputStreamReader(inputStream), 8192)
            bufferedReader.use {
                var line = it.readLine()
                while (line != null) {
                    if (!started) {
                        break
                    }
                    if (mPaused) {
                        synchronized(lock) {
                            lock.wait()
                        }
                    }
                    if (isAfterLastLine) {
                        onReadInputLine(line)
                        lastItem = line
                    } else {
                        while (lastItem != line) {
                            line = it.readLine()
                        }
                        isAfterLastLine = true
                    }
                    line = it.readLine()
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        sender.message("LogcatReaderTask readInputStream finish").log(main)
    }

}