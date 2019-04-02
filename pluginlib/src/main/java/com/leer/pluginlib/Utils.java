package com.leer.pluginlib;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Utils {

    public static String copyAssetAndWrite(Context context, String fileName) {
        try {
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir, fileName);
            if (!outFile.exists()) {
                boolean newFile = outFile.createNewFile();
                if (newFile) {
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, byteCount);
                    }
                    fileOutputStream.flush();
                    is.close();
                    fileOutputStream.close();
                    Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            } else {
                Toast.makeText(context, "文件已存在", Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
