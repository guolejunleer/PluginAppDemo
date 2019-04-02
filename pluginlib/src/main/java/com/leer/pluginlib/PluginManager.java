package com.leer.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    private PluginApk mPluginApk;
    private Context mContext;

    private static PluginManager mInstance = new PluginManager();

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 加载插件apk
     *
     * @param apkPath 插件apk路径
     */
    public void loadPluginApk(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager()
                .getPackageArchiveInfo(apkPath,
                        PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if (packageInfo == null) return;
        DexClassLoader classLoader = createDexClassloader(apkPath);
        AssetManager am = createAssetManager(apkPath);
        Resources rs = createResource(am);
        mPluginApk = new PluginApk();
        mPluginApk.setPackageInfo(packageInfo);
        mPluginApk.setClassLoader(classLoader);
        mPluginApk.setResources(rs);

    }

    public PluginApk getPluginApk() {
        return mPluginApk;
    }

    private Resources createResource(AssetManager am) {
        Resources resources = mContext.getResources();
        return new Resources(am, resources.getDisplayMetrics(), resources.getConfiguration());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",
                    String.class);
            method.invoke(assetManager, apkPath);
            return assetManager;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private DexClassLoader createDexClassloader(String apkPath) {
        File file = mContext.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath, file.getAbsolutePath(), null,
                mContext.getClassLoader());
    }

}
