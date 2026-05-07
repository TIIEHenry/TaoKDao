package tiiehenry.android.dl.plugin

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.res.AssetManager
import android.content.res.Resources
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import tiiehenry.android.dl.bean.APKConfig
import java.io.File

class PluginContext(
        private val host: Context,
        private val config: APKConfig,
        private val classLoader: ClassLoader,
        private val assetManager: AssetManager,
        private val resources: Resources,
        private val packageInfo: PackageInfo
) : ContextWrapper(host) {
    private val dirOfPlugin = "plugin/$packageName"

    override fun getApplicationContext(): Context {
        return host.applicationContext
    }

    override fun getPackageResourcePath(): String {
        return config.apkPath
    }

    override fun setTheme(resid: Int) {
//        super.setTheme(resid)
    }

    override fun getPackageCodePath(): String {
        return config.apkPath
    }

    override fun getCodeCacheDir(): File {
        return File(config.optimizedDirectory)
    }

    override fun getPackageName(): String {
        return packageInfo.packageName
    }

    override fun getClassLoader(): ClassLoader {
        return classLoader
    }

    override fun getAssets(): AssetManager {
        return assetManager
    }

    override fun getResources(): Resources {
        return resources
    }

    override fun getApplicationInfo(): ApplicationInfo? {
        return packageInfo.applicationInfo
    }

    private val theme = resources.newTheme().apply {
        val theme = host.applicationContext.theme
        if (theme != null) {
            setTo(theme)
        }
//        setTo(host.getTheme())
    }

    override fun getTheme(): Resources.Theme {
        return theme
    }

    override fun getDir(name: String?, mode: Int): File {
        return host.getDir(packageName + "_" + name, mode)
    }

    override fun getObbDir(): File {
        return File(host.obbDir, dirOfPlugin).apply { mkdirs() }
    }

    override fun getObbDirs(): Array<File> {
        return host.obbDirs.map {
            File(it, dirOfPlugin).apply { mkdirs() }
        }.toTypedArray()
    }

    override fun getFilesDir(): File {
        return File(host.filesDir, dirOfPlugin).apply { mkdirs() }
    }

    override fun getCacheDir(): File {
        return File(host.cacheDir, dirOfPlugin).apply { mkdirs() }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getDataDir(): File {
        return File(host.dataDir, dirOfPlugin).apply { mkdirs() }
    }

    override fun getDatabasePath(name: String?): File {
        return host.getDatabasePath(packageName + "_" + name)
    }

    override fun deleteDatabase(name: String?): Boolean {
        return host.deleteDatabase(packageName + "_" + name)
    }

    override fun openOrCreateDatabase(name: String?, mode: Int, factory: SQLiteDatabase.CursorFactory?): SQLiteDatabase {
        return host.openOrCreateDatabase(packageName + "_" + name, mode, factory)
    }

    override fun openOrCreateDatabase(name: String?, mode: Int, factory: SQLiteDatabase.CursorFactory?, errorHandler: DatabaseErrorHandler?): SQLiteDatabase {
        return host.openOrCreateDatabase(packageName + "_" + name, mode, factory, errorHandler)
    }

    override fun getExternalCacheDir(): File? {
        val file = host.externalCacheDir ?: return null
        return File(file, dirOfPlugin).apply { mkdirs() }
    }

    override fun getExternalCacheDirs(): Array<File> {
        return host.externalCacheDirs.map {
            File(it, dirOfPlugin).apply { mkdirs() }
        }.toTypedArray()
    }

    override fun getExternalFilesDir(type: String?): File? {
        val file = host.getExternalFilesDir(type) ?: return null
        return File(file, dirOfPlugin).apply { mkdirs() }
    }


    override fun getExternalFilesDirs(type: String?): Array<File> {
        return host.getExternalFilesDirs(type).map {
            File(it, dirOfPlugin).apply { mkdirs() }
        }.toTypedArray()
    }

    override fun getExternalMediaDirs(): Array<File> {
        return host.externalMediaDirs
    }

    override fun getSharedPreferences(name: String?, mode: Int): SharedPreferences {
        return host.getSharedPreferences(packageName + "_" + name, mode)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun deleteSharedPreferences(name: String?): Boolean {
        return host.deleteSharedPreferences(packageName + "_" + name)
    }

}