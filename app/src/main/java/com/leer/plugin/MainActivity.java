package com.leer.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.leer.pluginlib.PluginManager;
import com.leer.pluginlib.ProxyActivity;
import com.leer.pluginlib.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);
    }

    public void jumpPlugin(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", "com.leer.plugindemo.ChaJianActivity");
        startActivity(intent);
    }

    public void loadPlugin(View view) {
        PluginManager.getInstance().loadPluginApk(
                Utils.copyAssetAndWrite(this, "test002.apk"));
    }
}
