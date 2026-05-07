package taokdao.main.business.drawable_manage

import android.graphics.drawable.Drawable
import taokdao.api.data.drawable.IDrawableManager
import taokdao.main.IMainView


interface DrawableManageContract {
    interface V : IMainView {
        fun loadDefaultDrawableMapForSuffix(): Map<String, Drawable?>
        fun loadDefaultDrawableMapForFileName(): Map<String, Drawable?>
        fun loadDefaultDrawableMapForDirName(): Map<String, Drawable?>

        val drawableManagePresenter: DrawableManagePresenter

    }

    interface P : IDrawableManager {

        fun init()
    }

    interface M {
        fun setDrawableForSuffix(map: Map<String, Drawable?>)
        fun getDrawableForSuffix(suffix: String): Drawable?
        fun setDefaultDrawableForSuffix(drawable: Drawable?)
        fun getDefaultDrawableForSuffix(): Drawable?

        fun setDrawableForFileName(map: Map<String, Drawable?>)
        fun getDrawableForFileName(fileName: String?): Drawable?
        fun setDefaultDrawableForFileName(drawable: Drawable?)
        fun getDefaultDrawableForFileName(): Drawable?

        fun setDrawableForDirName(map: Map<String, Drawable?>)
        fun getDrawableForDirName(fileName: String?): Drawable?
        fun setDefaultDrawableForDirName(drawable: Drawable?)
        fun getDefaultDrawableForDirName(): Drawable?
    }
}