package taokdao.window.toolpages.logcat

data class LogItem(val level: String, val tag: String, val process: String?, val msg: String) {
    var isExpand = false
    var oriString = ""
    fun toggle() {
        isExpand = !isExpand

    }
}