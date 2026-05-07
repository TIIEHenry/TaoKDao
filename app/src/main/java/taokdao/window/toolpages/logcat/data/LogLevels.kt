package taokdao.window.toolpages.logcat.data

import android.util.Log

object LogLevels {
    val levelMap = mapOf(
            -1 to "All",
            Log.VERBOSE to "V",
            Log.DEBUG to "D",
            Log.INFO to "I",
            Log.WARN to "W",
            Log.ERROR to "E",
            Log.ASSERT to "A"
    )
}