package taokdao.main.business.drawable_manage

import android.graphics.drawable.Drawable
import taokdao.main.IMainView
import tiiehenry.ideditor.R
import java.io.File


class DrawableManagePresenter(internal val view: DrawableManageContract.V) : DrawableManageContract.P {
    private val model = DrawableManageModel()

    override fun init() {
        val defaultDrawableMapForSuffix = view.loadDefaultDrawableMapForSuffix()
        setDrawableForSuffix(defaultDrawableMapForSuffix)
        defaultDrawableForSuffix = view.getDrawable(R.drawable.ic_file_any)

        val defaultDrawableMapForFileName = view.loadDefaultDrawableMapForFileName()
        defaultDrawableForFileName = view.getDrawable(R.drawable.ic_file_any)
        setDrawableForFileName(defaultDrawableMapForFileName)

        val defaultDrawableMapForDirName = view.loadDefaultDrawableMapForDirName()
        setDrawableForDirName(defaultDrawableMapForDirName)
        defaultDrawableForDirName = view.getDrawable(R.drawable.ic_folder)
    }

    override fun setDrawableForSuffix(map: Map<String, Drawable?>) {
        model.setDrawableForSuffix(map)
    }

    override fun getDrawableForSuffix(suffix: String): Drawable? {
        return model.getDrawableForSuffix(suffix)
    }

    override fun setDefaultDrawableForSuffix(drawable: Drawable?) {
        model.setDefaultDrawableForSuffix(drawable)
    }

    override fun getDefaultDrawableForSuffix(): Drawable? {
        return model.getDefaultDrawableForSuffix()
    }

    override fun setDrawableForFileName(map: Map<String, Drawable?>) {
        model.setDrawableForFileName(map)
    }

    override fun getDrawableForFileName(fileName: String?): Drawable? {
        return model.getDrawableForFileName(fileName)
    }

    override fun setDefaultDrawableForFileName(drawable: Drawable?) {
        model.setDefaultDrawableForFileName(drawable)
    }

    override fun getDefaultDrawableForFileName(): Drawable? {
        return model.getDefaultDrawableForFileName()
    }

    override fun setDrawableForDirName(map: Map<String, Drawable?>) {
        model.setDrawableForDirName(map)
    }

    override fun getDrawableForDirName(fileName: String?): Drawable? {
        return model.getDrawableForDirName(fileName)
    }

    override fun setDefaultDrawableForDirName(drawable: Drawable?) {
        model.setDefaultDrawableForDirName(drawable)
    }

    override fun getDefaultDrawableForDirName(): Drawable? {
        return model.getDefaultDrawableForDirName()
    }

    companion object {
        fun getForFile(c: IMainView, file: File): Drawable? {
            val presenter = c.main.drawableManagePresenter
            val suffix = file.extension
            return if (DrawableManager.isImageSuffix(suffix))
                Drawable.createFromPath(file.absolutePath)
            else
                presenter.getDrawableForFileName(file.name)
                        ?: presenter.getDrawableForSuffix(file.extension)
                        ?: presenter.defaultDrawableForSuffix
        }
    }
}