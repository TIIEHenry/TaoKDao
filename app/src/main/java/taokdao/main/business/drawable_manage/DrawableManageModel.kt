package taokdao.main.business.drawable_manage

import android.graphics.drawable.Drawable

class DrawableManageModel : DrawableManageContract.M {


    private val drawableForSuffix = mutableMapOf<String, Drawable?>()
    override fun setDrawableForSuffix(map: Map<String, Drawable?>) {
        for (item in map) {
            drawableForSuffix[item.key] = item.value
        }
    }

    override fun getDrawableForSuffix(suffix: String): Drawable? {
        return drawableForSuffix[suffix]
    }


    private var defaultDrawableForSuffix: Drawable? = null
    override fun setDefaultDrawableForSuffix(drawable: Drawable?) {
        defaultDrawableForSuffix = drawable
    }

    override fun getDefaultDrawableForSuffix(): Drawable? {
        return defaultDrawableForSuffix
    }

    private val drawableForFileName = mutableMapOf<String, Drawable?>()
    override fun setDrawableForFileName(map: Map<String, Drawable?>) {
        for (item in map) {
            drawableForFileName[item.key] = item.value
        }
    }

    override fun getDrawableForFileName(fileName: String?): Drawable? {
        return drawableForFileName[fileName]
    }


    private var defaultDrawableForFileName: Drawable? = null
    override fun setDefaultDrawableForFileName(drawable: Drawable?) {
        defaultDrawableForFileName = drawable
    }

    override fun getDefaultDrawableForFileName(): Drawable? {
        return defaultDrawableForFileName
    }

    private val drawableForDirName = mutableMapOf<String, Drawable?>()
    override fun setDrawableForDirName(map: Map<String, Drawable?>) {
        for (item in map) {
            drawableForDirName[item.key] = item.value
        }
    }

    override fun getDrawableForDirName(fileName: String?): Drawable? {
        return drawableForDirName[fileName]
    }


    private var defaultDrawableForDirName: Drawable? = null
    override fun setDefaultDrawableForDirName(drawable: Drawable?) {
        defaultDrawableForDirName = drawable
    }

    override fun getDefaultDrawableForDirName(): Drawable? {
        return defaultDrawableForDirName
    }
}
