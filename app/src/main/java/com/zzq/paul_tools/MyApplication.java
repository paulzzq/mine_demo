package com.zzq.paul_tools;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.zzq.paul_tools.db.MyContextWrapper;
import com.zzq.paul_tools.db.MySQLiteHelper;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2019/8/3 19:28
 */
public class MyApplication extends Application {
    public static  int REQUEST_WRITE_EXTERNAL_STORAGE=10000;
    public static MySQLiteHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
//        checkPermission();
        MyContextWrapper mContext = new MyContextWrapper(this, "Run");
         helper = new MySQLiteHelper(mContext, "Book2.db", null, 2);
    }
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(new MainActivity(), Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(new MainActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("eee", "checkPermission: 已经授权！");
        }
    }
}
