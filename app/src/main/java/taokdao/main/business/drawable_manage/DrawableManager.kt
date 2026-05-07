package taokdao.main.business.drawable_manage

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import tiiehenry.ideditor.R.drawable.*
import java.io.File

object DrawableManager {
    fun getForDir(c: Context, file: File): Drawable? {
        val id = when (file.name) {
            "assets" -> ic_folder_resourcesroot
            "java" -> ic_folder_sourcefolder
            "libs" -> ic_folder_jardirectory
            "resources" -> ic_folder_resourcesroot
            "res" -> ic_folder_resourcesroot
            "src" -> ic_folder_sourcefolder
            "lua" -> ic_folder_sourcefolder
            else -> ic_folder
        }
        return ContextCompat.getDrawable(c, id)
    }

    private val imageSuffix = arrayListOf("jpg", "jpeg", "png", "bmp")
    fun isImageSuffix(suffix: String): Boolean {
        return imageSuffix.contains(suffix)
    }
}
