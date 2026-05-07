//package taokdao.tabtool.logcat.reader
//
//import tiiehenry.taokdao.main.IMainView
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//
//class ReadLogThread(val main:IMainView,private val addLog: (String) -> Unit) : Thread() {
//    var isInBackground = true
//    var process: Process? = null
//    override fun run() {
//        try {
//            process = ProcessBuilder()
//                    .command("logcat", "${taokdao.tabtool.logcat.reader.filterProcess}:${taokdao.tabtool.logcat.reader.filterLevel}"
//                            , "-v", "time"
////                                , "-t", "300"
//                    )
//                    .redirectErrorStream(true)
//                    .start()
//            if (process != null) {
//                val inputStream = process!!.inputStream
//                Thread(Runnable {
//                    val br = BufferedReader(InputStreamReader(inputStream))
//                    try {
//                        var line = br.readLine()
//                        while (line != null) {
//                            addLog(line)
//                            while (isInBackground)
//                                sleep(500)
//                            line = br.readLine()
//                        }
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }).start()
//                process?.waitFor()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            process?.destroy()
//        }
//    }
//
//    override fun interrupt() {
//        super.interrupt()
//        process?.destroy()
//    }
//}