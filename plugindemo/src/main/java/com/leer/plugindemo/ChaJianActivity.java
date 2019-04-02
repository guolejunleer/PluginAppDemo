package com.leer.plugindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.leer.pluginlib.PluginActivity;

public class ChaJianActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        Toast.makeText(this, "这是一个插件的点击事件", Toast.LENGTH_SHORT).show();
    }
}
