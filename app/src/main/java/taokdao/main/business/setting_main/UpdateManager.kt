package taokdao.main.business.setting_main

import android.content.Context
import android.content.pm.PackageManager
import taokdao.api.data.mmkv.IMMKV

class UpdateManager {
    companion object {
        fun shouldUpdateLocal(context: Context, projectPluginMMKV: IMMKV): Boolean {
            try {
                val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                val lastTime = packageInfo.lastUpdateTime
                val oldLastTime = projectPluginMMKV.decodeLong("lastUpdateTime", 0)
                if (oldLastTime != lastTime) {
                    projectPluginMMKV.encode("lastUpdateTime", lastTime)
                    return true
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return false
        }
    }
}