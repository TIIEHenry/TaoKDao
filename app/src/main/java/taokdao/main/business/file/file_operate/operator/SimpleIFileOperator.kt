package taokdao.main.business.file.file_operate.operator

import android.graphics.drawable.Drawable
import taokdao.api.data.bean.Properties
import taokdao.api.file.operate.IFileOperator

abstract class SimpleIFileOperator(val properties: Properties) : IFileOperator {

    override fun getIcon(): Drawable? = null

    override fun id(): String = properties.id

    override fun getLabel(): String = properties.label

    override fun getDescription(): String? = properties.description

    override fun isSupport(path: String): Boolean {
        return true
    }

}