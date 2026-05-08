package taokdao.main.business.file.file_operate.operator

import android.graphics.drawable.Drawable
import taokdao.api.data.bean.Properties
import taokdao.api.file.operate.IFileOperator

abstract class SimpleIFileOperator(val properties: Properties) : IFileOperator {

    override fun id(): String = properties.id

    override val icon: Drawable? get() = null

    override val label: String get() = properties.label

    override val description: String? get() = properties.description

    override fun isSupport(path: String): Boolean {
        return true
    }

}