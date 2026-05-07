package tiiehenry.taokdao.data.dex

import android.content.Context
import android.content.pm.PackageManager
import dalvik.system.DexClassLoader
import java.io.File
import java.io.FileNotFoundException
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


open class DynamicDexLoader(val context: Context, val optimizedDirectory: String, val librarySearchPath: String) : ClassLoader(context.classLoader) {


    @Throws(FileNotFoundException::class)
    fun loadDex(path: String): DynamicDexClassLoader? {
        return loadDexFile(File(path))
    }

    @Throws(FileNotFoundException::class)
    fun loadDexFile(dexFile: File): DynamicDexClassLoader? {
        if (!dexFile.isFile) {
            throw FileNotFoundException(dexFile.path)
        }
        val path = dexFile.absolutePath
        //path查找
        var loader = dexClassLoaderMap[path]
        if (loader != null)
            return loader
        //md5查找
        dexFile.getMD5().let {
            if (it != "0") {
                val classLoader = dexClassLoaderMap[it]
                if (classLoader != null)
                    return classLoader
            }
        }
        //没找到加载
        loader = DynamicDexClassLoader(path, optimizedDirectory, librarySearchPath, parent)
        //        val loader = DexClassLoader(file.path, scriptContext.scriptProvider.getOdexDir(), scriptContext.scriptProvider.getSoDir(), parent)
        dexClassLoaderMap[path] = loader
        mDexClassLoaders.add(loader)
        return loader
    }

    @Throws(ClassNotFoundException::class)
    public override fun loadClass(name: String?, resolve: Boolean): Class<*>? {
        var loadedClass = findLoadedClass(name)
        if (loadedClass == null) {
            for (dex in mDexClassLoaders) {
                loadedClass = dex.loadClass(name)
                if (loadedClass != null) {
                    break
                }
            }
            if (loadedClass == null) {
                loadedClass = parent.loadClass(name)
            }
        }
        return loadedClass
    }


    //    加载其他安装的app的dex
    fun loadInstalledApkDex(packageName: String): DynamicDexClassLoader? {
        try {
            var dex = dexClassLoaderMap[packageName]
            if (dex == null) {
                val manager = context.packageManager
                val apkPath = manager.getPackageInfo(packageName, 0).applicationInfo?.publicSourceDir?:return null
                dex = DynamicDexClassLoader(apkPath, optimizedDirectory, librarySearchPath, parent)
                dexClassLoaderMap[packageName] = dex
            }
            return dex
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    fun loadApk(apkFile: File): DexClassLoader? {
        return loadApk(apkFile.absolutePath)
    }

    fun loadApk(apkPath: String): DexClassLoader? {
        var dex = dexClassLoaderMap[apkPath]
        if (dex == null) {
            dex = DynamicDexClassLoader(apkPath, optimizedDirectory, librarySearchPath, parent)
            dexClassLoaderMap[apkPath] = dex
        }
        return dex
    }

    private fun File.getMD5(): String {
        val digest = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, digest.digest(readBytes()))
        return bigInt.toString(16)
    }


    companion object {
        private val mDexClassLoaders = mutableListOf<DexClassLoader>()

        //md5
        private val dexClassLoaderMap = HashMap<String, DynamicDexClassLoader>()
    }
}