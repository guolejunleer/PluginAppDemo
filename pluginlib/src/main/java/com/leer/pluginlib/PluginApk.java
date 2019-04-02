package com.leer.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {

    private PackageInfo mPackageInfo;
    private Resources mResources;
    private AssetManager mAssetManager;
    private DexClassLoader mClassLoader;

    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }

    public void setPackageInfo(PackageInfo mPackageInfo) {
        this.mPackageInfo = mPackageInfo;
    }

    public Resources getResources() {
        return mResources;
    }

    public void setResources(Resources mResources) {
        this.mResources = mResources;
    }

    public AssetManager getAssetManager() {
        return mResources.getAssets();
    }

    public DexClassLoader getClassLoader() {
        return mClassLoader;
    }

    public void setClassLoader(DexClassLoader mClassLoader) {
        this.mClassLoader = mClassLoader;
    }
}
