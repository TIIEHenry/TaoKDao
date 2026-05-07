package taokdao.main.business.dex_load

import android.content.Context
import dalvik.system.DexClassLoader
import tiiehenry.taokdao.data.dex.DynamicDexLoader
import java.io.File


class DexLoadPresenter(internal val view: DexLoadContract.V) : DexLoadContract.P {
    private val model = DexLoadModel()
    private val dexLoader by lazy {
        DynamicDexLoader(view.context, optimizedDirectory, librarySearchPath)
    }

    override fun getClassLoader() = dexLoader

    override fun getOptimizedDirectory(): String {
        return view.context.getDir("odex", Context.MODE_PRIVATE).absolutePath
    }

    override fun getLibrarySearchPath(): String {
        return view.context.getDir("lib", Context.MODE_PRIVATE).absolutePath
    }

    override fun loadDexFile(dexFile: File): DexClassLoader? {
        return dexLoader.loadDexFile(dexFile)
    }

    override fun loadClass(name: String, resolve: Boolean): Class<*>? {
        return dexLoader.loadClass(name, resolve)
    }

    override fun loadDex(path: String): DexClassLoader? {
        return dexLoader.loadDex(path)
    }

    override fun loadInstalledApkDex(packageName: String): DexClassLoader? {
        return dexLoader.loadInstalledApkDex(packageName)
    }

    override fun loadApk(apkFile: File): DexClassLoader? {
        return dexLoader.loadApk(apkFile)
    }

}