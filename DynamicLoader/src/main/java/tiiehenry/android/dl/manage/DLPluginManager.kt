/*
 * Copyright (C) 2014 singwhatiwanna(任玉刚) <singwhatiwanna@gmail.com>
 *
 * collaborator:田啸,宋思宇,Mr.Simple
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tiiehenry.android.dl.manage

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.ServiceConnection
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.text.TextUtils
import android.util.Log
import android.view.ContextThemeWrapper
import dalvik.system.DexClassLoader
import tiiehenry.android.dl.*
import tiiehenry.android.dl.bean.APKConfig
import tiiehenry.android.dl.bean.DLPluginPackage
import tiiehenry.android.dl.plugin.PluginContext
import tiiehenry.android.dl.utils.DLConstants
import tiiehenry.android.dl.utils.SoLibManager
import java.io.IOException
import java.util.*

class DLPluginManager private constructor(val context: Context) {
    private val applicationContext: Context = context.applicationContext
    private val mPackagesHolder = HashMap<String, DLPluginPackage>()
    private var mFrom = DLConstants.FROM_INTERNAL
    private var mResult = 0

    /**
     * @param packageName installed apk packageName
     */
    @Throws(PackageManager.NameNotFoundException::class)
    fun loadInstalledApk(packageName: String, config: APKConfig): DLPluginPackage {
        val manager = applicationContext.packageManager
        config.apkPath = manager.getPackageInfo(packageName, 0).applicationInfo?.publicSourceDir?:throw IOException("getPackageInfo error")
        return loadAPK(config)
    }
    /**
     * @param dexPath  plugin path
     * @param config
     * @param hasSoLib whether exist so lib in plugin
     * @return
     */
    /**
     * Load a apk. Before start a plugin Activity, we should do this first.<br></br>
     * NOTE : will only be called by host apk.
     *
     * @param apkPath
     */
    @JvmOverloads
    @Throws(RuntimeException::class)
    fun loadAPK(config: APKConfig, hasSoLib: Boolean = true): DLPluginPackage {
        mFrom = DLConstants.FROM_EXTERNAL
        val packageInfo = applicationContext.packageManager.getPackageArchiveInfo(config.apkPath,
                PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES)
                ?: throw RuntimeException("getPackageArchiveInfo error")
        val pluginPackage = preparePluginEnv(packageInfo, config)
        if (hasSoLib) {
            copySoLib(config.apkPath, config.librarySearchPath)
        }
        return pluginPackage
    }

    /**
     * prepare plugin runtime env, has DexClassLoader, Resources, and so on.
     *
     * @param packageInfo
     * @param dexPath
     * @param config
     * @return
     */
    private fun preparePluginEnv(packageInfo: PackageInfo, config: APKConfig): DLPluginPackage {
//        DLPluginPackage pluginPackage = mPackagesHolder.get(packageInfo.packageName);
//        if (pluginPackage != null) {
//            return pluginPackage;
//        }
//        always load
        val dexClassLoader = createDexClassLoader(config)
        val assetManager = createAssetManager(config.apkPath)
        val resources = createResources(assetManager)
        val pluginContext = PluginContext(
                context,
                config,
                dexClassLoader,
                assetManager,
                resources,
                packageInfo
        )
        val pluginPackage = DLPluginPackage(config, pluginContext,packageInfo)
        mPackagesHolder[packageInfo.packageName] = pluginPackage
        return pluginPackage
    }

    private fun createDexClassLoader(config: APKConfig): DexClassLoader {
        return DexClassLoader(config.apkPath, config.optimizedDirectory, config.librarySearchPath, context.classLoader)
    }

    @Throws(Exception::class)
    private fun createAssetManager(dexPath: String): AssetManager {
        val assetManager = AssetManager::class.java.newInstance()
        val addAssetPath = assetManager.javaClass.getMethod("addAssetPath", String::class.java)
        addAssetPath.invoke(assetManager, dexPath)
        return assetManager
    }

    fun getPackage(packageName: String): DLPluginPackage? {
        return mPackagesHolder[packageName]
    }

    private fun createResources(assetManager: AssetManager): Resources {
        val superRes = applicationContext.resources
        return Resources(assetManager, superRes.displayMetrics, superRes.configuration)
    }

    /**
     * copy .so file to pluginlib dir.
     *
     * @param dexPath
     * @param soDir
     */
    private fun copySoLib(dexPath: String, soDir: String) {
        // TODO: copy so lib async will lead to bugs maybe, waiting for
        // resolved later.

        // TODO : use wait andvi signal is ok ? that means when copying the
        // .so files, the main thread will enter waiting status, when the
        // copy is done, send a signal to the main thread.
        // new Thread(new CopySoRunnable(dexPath)).start();
        SoLibManager.getSoLoader().copyPluginSoLib(applicationContext, dexPath, soDir)
    }

    /**
     * [.startPluginActivityForResult]
     */
    fun startPluginActivity(context: Context, dlIntent: DLIntent): Int {
        return startPluginActivityForResult(context, dlIntent, -1)
    }

    /**
     * @param context
     * @param dlIntent
     * @param requestCode
     * @return One of below: [.START_RESULT_SUCCESS]
     * [.START_RESULT_NO_PKG] [.START_RESULT_NO_CLASS]
     * [.START_RESULT_TYPE_ERROR]
     */
    fun startPluginActivityForResult(context: Context, dlIntent: DLIntent, requestCode: Int): Int {
        if (mFrom == DLConstants.FROM_INTERNAL) {
            dlIntent.setClassName(context, dlIntent.pluginClass)
            performStartActivityForResult(context, dlIntent, requestCode)
            return START_RESULT_SUCCESS
        }
        val packageName = dlIntent.pluginPackage
        if (TextUtils.isEmpty(packageName)) {
            throw NullPointerException("disallow null packageName.")
        }
        val pluginPackage = mPackagesHolder[packageName] ?: return START_RESULT_NO_PKG
        val className = getPluginActivityFullPath(dlIntent, pluginPackage)
        val clazz = loadPluginClass(pluginPackage.context.classLoader, className)
                ?: return START_RESULT_NO_CLASS

        // get the proxy activity class, the proxy activity will launch the
        // plugin activity.
        val activityClass = getProxyActivityClass(clazz) ?: return START_RESULT_TYPE_ERROR

        // put extra data
        dlIntent.putExtra(DLConstants.EXTRA_CLASS, className)
        dlIntent.putExtra(DLConstants.EXTRA_PACKAGE, packageName)
        dlIntent.setClass(applicationContext, activityClass)
        performStartActivityForResult(context, dlIntent, requestCode)
        return START_RESULT_SUCCESS
    }

    fun startPluginService(context: Context, dlIntent: DLIntent): Int {
        if (mFrom == DLConstants.FROM_INTERNAL) {
            dlIntent.setClassName(context, dlIntent.pluginClass)
            context.startService(dlIntent)
            return START_RESULT_SUCCESS
        }
        fetchProxyServiceClass(dlIntent, object : OnFetchProxyServiceClass {
            override fun onFetch(result: Int, proxyServiceClass: Class<out Service?>?) {
                // TODO Auto-generated method stub
                if (result == START_RESULT_SUCCESS) {
                    dlIntent.setClass(context, proxyServiceClass!!)
                    // start代理Service
                    context.startService(dlIntent)
                }
                mResult = result
            }
        })
        return mResult
    }

    fun stopPluginService(context: Context, dlIntent: DLIntent): Int {
        if (mFrom == DLConstants.FROM_INTERNAL) {
            dlIntent.setClassName(context, dlIntent.pluginClass)
            context.stopService(dlIntent)
            return START_RESULT_SUCCESS
        }
        fetchProxyServiceClass(dlIntent, object : OnFetchProxyServiceClass {
            override fun onFetch(result: Int, proxyServiceClass: Class<out Service?>?) {
                // TODO Auto-generated method stub
                if (result == START_RESULT_SUCCESS) {
                    dlIntent.setClass(context, proxyServiceClass!!)
                    // stop代理Service
                    context.stopService(dlIntent)
                }
                mResult = result
            }
        })
        return mResult
    }

    fun bindPluginService(context: Context, dlIntent: DLIntent, conn: ServiceConnection?,
                          flags: Int): Int {
        if (mFrom == DLConstants.FROM_INTERNAL) {
            dlIntent.setClassName(context, dlIntent.pluginClass)
            context.bindService(dlIntent, conn!!, flags)
            return START_RESULT_SUCCESS
        }
        fetchProxyServiceClass(dlIntent, object : OnFetchProxyServiceClass {
            override fun onFetch(result: Int, proxyServiceClass: Class<out Service?>?) {
                // TODO Auto-generated method stub
                if (result == START_RESULT_SUCCESS) {
                    dlIntent.setClass(context, proxyServiceClass!!)
                    // Bind代理Service
                    context.bindService(dlIntent, conn!!, flags)
                }
                mResult = result
            }
        })
        return mResult
    }

    fun unBindPluginService(context: Context, dlIntent: DLIntent, conn: ServiceConnection?): Int {
        if (mFrom == DLConstants.FROM_INTERNAL) {
            context.unbindService(conn!!)
            return START_RESULT_SUCCESS
        }
        fetchProxyServiceClass(dlIntent, object : OnFetchProxyServiceClass {
            override fun onFetch(result: Int, proxyServiceClass: Class<out Service?>?) {
                // TODO Auto-generated method stub
                if (result == START_RESULT_SUCCESS) {
                    // unBind代理Service
                    context.unbindService(conn!!)
                }
                mResult = result
            }
        })
        return mResult
    }

    /**
     * 获取代理ServiceClass
     *
     * @param dlIntent
     * @param fetchProxyServiceClass
     */
    private fun fetchProxyServiceClass(dlIntent: DLIntent, fetchProxyServiceClass: OnFetchProxyServiceClass) {
        val packageName = dlIntent.pluginPackage
        if (TextUtils.isEmpty(packageName)) {
            throw NullPointerException("disallow null packageName.")
        }
        val pluginPackage = mPackagesHolder[packageName]
        if (pluginPackage == null) {
            fetchProxyServiceClass.onFetch(START_RESULT_NO_PKG, null)
            return
        }

        // 获取要启动的Service的全名
        val className = dlIntent.pluginClass
        val clazz = loadPluginClass(pluginPackage.context.classLoader, className)
        if (clazz == null) {
            fetchProxyServiceClass.onFetch(START_RESULT_NO_CLASS, null)
            return
        }
        val proxyServiceClass = getProxyServiceClass(clazz)
        if (proxyServiceClass == null) {
            fetchProxyServiceClass.onFetch(START_RESULT_TYPE_ERROR, null)
            return
        }

        // put extra data
        dlIntent.putExtra(DLConstants.EXTRA_CLASS, className)
        dlIntent.putExtra(DLConstants.EXTRA_PACKAGE, packageName)
        fetchProxyServiceClass.onFetch(START_RESULT_SUCCESS, proxyServiceClass)
    }

    // zhangjie1980 重命名 loadPluginActivityClass -> loadPluginClass
    fun loadPluginClass(classLoader: ClassLoader?, className: String?): Class<*>? {
        var clazz: Class<*>? = null
        try {
            clazz = Class.forName(className!!, true, classLoader)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return clazz
    }

    private fun getPluginActivityFullPath(dlIntent: DLIntent, pluginPackage: DLPluginPackage): String? {
        var className = dlIntent.pluginClass
        className = className ?: pluginPackage.defaultActivity
        if (className!!.startsWith(".")) {
            className = dlIntent.pluginPackage + className
        }
        return className
    }

    /**
     * get the proxy activity class, the proxy activity will delegate the plugin
     * activity
     *
     * @param clazz target activity's class
     * @return
     */
    private fun getProxyActivityClass(clazz: Class<*>): Class<out Activity>? {
        var activityClass: Class<out Activity>? = null
        if (DLBasePluginActivity::class.java.isAssignableFrom(clazz)) {
            activityClass = DLProxyActivity::class.java
        } else if (DLBasePluginFragmentActivity::class.java.isAssignableFrom(clazz)) {
            activityClass = DLProxyFragmentActivity::class.java
        }
        return activityClass
    }

    private fun getProxyServiceClass(clazz: Class<*>): Class<out Service?>? {
        var proxyServiceClass: Class<out Service?>? = null
        if (DLBasePluginService::class.java.isAssignableFrom(clazz)) {
            proxyServiceClass = DLProxyService::class.java
        }
        // 后续可能还有IntentService，待补充
        return proxyServiceClass
    }

    private fun performStartActivityForResult(context: Context, dlIntent: DLIntent, requestCode: Int) {
        Log.d(TAG, "launch " + dlIntent.pluginClass)
        if (context is Activity) {
            context.startActivityForResult(dlIntent, requestCode)
        } else {
            context.startActivity(dlIntent)
        }
    }

    private interface OnFetchProxyServiceClass {
        fun onFetch(result: Int, proxyServiceClass: Class<out Service?>?)
    }

    companion object {
        private const val TAG = "DLPluginManager"

        /**
         * return value of [.startPluginActivity] start
         * success
         */
        const val START_RESULT_SUCCESS = 0

        /**
         * return value of [.startPluginActivity] package
         * not found
         */
        const val START_RESULT_NO_PKG = 1

        /**
         * return value of [.startPluginActivity] class
         * not found
         */
        const val START_RESULT_NO_CLASS = 2

        /**
         * return value of [.startPluginActivity] class
         * type error
         */
        const val START_RESULT_TYPE_ERROR = 3
        private var sInstance: DLPluginManager? = null

        @JvmStatic
        fun getInstance(context: Context): DLPluginManager {
            if (sInstance == null) {
                synchronized(Companion::class.java) {
                    if (sInstance == null) {
                        sInstance = DLPluginManager(context)
                    }
                }
            }
            return sInstance!!
        }
    }

}