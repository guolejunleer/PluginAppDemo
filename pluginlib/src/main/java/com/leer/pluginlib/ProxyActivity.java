package com.leer.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

public class ProxyActivity extends Activity {

    private String mClassName;
    private PluginApk mPluginApk;
    private IPlugin mIPlugin;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstance().getPluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (mPluginApk == null) throw new RuntimeException("请先加载插件");
        try {
            Class<?> clazz = mPluginApk.getClassLoader().loadClass(mClassName);
            Object obj = clazz.newInstance();
            if (obj instanceof IPlugin) {
                mIPlugin = (IPlugin) obj;
                mIPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("from", IPlugin.FROM_EXTERNER);
                mIPlugin.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mIPlugin != null ? mPluginApk.getResources() : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mIPlugin != null ? mPluginApk.getAssetManager() : super.getAssets();
    }
}
