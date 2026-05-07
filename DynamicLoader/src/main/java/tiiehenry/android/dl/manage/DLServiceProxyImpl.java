package tiiehenry.android.dl.manage;

import java.lang.reflect.Constructor;

import tiiehenry.android.dl.DLServicePlugin;
import tiiehenry.android.dl.bean.DLPluginPackage;
import tiiehenry.android.dl.utils.DLConfigs;
import tiiehenry.android.dl.utils.DLConstants;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class DLServiceProxyImpl {
    
    private static final String TAG = "DLServiceProxyImpl";
    private Service mProxyService;
    private DLServicePlugin mRemoteService;
    
    public DLServiceProxyImpl(Service service) {
        mProxyService = service;
    }
    
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void init(Intent intent) {
     // set the extra's class loader
        intent.setExtrasClassLoader(DLConfigs.sPluginClassloader);

        String packageName = intent.getStringExtra(DLConstants.EXTRA_PACKAGE);
        String clazz = intent.getStringExtra(DLConstants.EXTRA_CLASS);
        Log.d(TAG, "clazz=" + clazz + " packageName=" + packageName);
        
        DLPluginManager pluginManager = DLPluginManager.getInstance(mProxyService);
        DLPluginPackage pluginPackage = pluginManager.getPackage(packageName);
        
        try {
            Class<?> localClass = pluginPackage.context.getClassLoader().loadClass(clazz);
            Constructor<?> localConstructor = localClass.getConstructor();
            Object instance = localConstructor.newInstance();
            mRemoteService = (DLServicePlugin) instance;
            ((DLServiceAttachable) mProxyService).attach(mRemoteService, pluginManager);
            Log.d(TAG, "instance = " + instance);
            // attach the proxy activity and plugin package to the
            // mPluginActivity
            mRemoteService.attach(mProxyService, pluginPackage);

            Bundle bundle = new Bundle();
            bundle.putInt(DLConstants.FROM, DLConstants.FROM_EXTERNAL);
            mRemoteService.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
