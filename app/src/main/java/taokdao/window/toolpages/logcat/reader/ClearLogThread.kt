//package taokdao.tabtool.logcat.reader
//
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//import java.util.concurrent.ForkJoinPool
//
//class ClearLogThread : Thread() {
////    private val service: ExecutorService = Executors.newFixedThreadPool(1)
//
//    override fun run() {
//        var process: Process? = null
//        try {
//            process = ProcessBuilder()
//                    .command("logcat", "-c")
//                    .redirectErrorStream(true)
//                    .start()
//            process?.waitFor()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            process?.destroy()
//        }
//    }
//}