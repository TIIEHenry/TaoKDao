package tiiehenry.android.dl.bean

import android.content.Context
import android.content.pm.PackageInfo

data class DLPluginPackage(
        @JvmField
        val config: APKConfig,
        @JvmField
        val context: Context,
        @JvmField
        val packageInfo: PackageInfo,
        @JvmField
        val packageName: String = context.packageName,
        @JvmField
        val classLoader: ClassLoader = context.classLoader
) {


    @JvmField
    var defaultActivity: String? = parseDefaultActivityName()


    private fun parseDefaultActivityName(): String? {
        return packageInfo.activities?.let {
            if (it.isNotEmpty()) {
                it[0]?.name
            }
            ""
        } ?: ""
    }

}