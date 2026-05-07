package taokdao.window.explorers.fileexplorer

import android.graphics.drawable.Drawable
import java.io.File

data class FileExplorerItem(val icon: Drawable?, val label: String, val file: File, var type: Type = Type.FILE) {
    enum class Type {
        FILE, OP, UPLEVEL
    }

}