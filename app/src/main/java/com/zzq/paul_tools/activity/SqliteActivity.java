package com.zzq.paul_tools.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zzq.paul_tools.MyApplication;
import com.zzq.paul_tools.R;

import butterknife.BindView;

/**
 * @author zhuzaiqing
 * @describe 测试sqlite数据库的创建
 * @time 2019/8/3 18:34
 */
public class SqliteActivity extends BaseActivity {

    @BindView(R.id.tv_create)
    TextView tvCreate;
//    MySQLiteHelper helper;
    public static  int REQUEST_WRITE_EXTERNAL_STORAGE=10000;



    @Override
    protected int getLayout() {
        return R.layout.activity_sqlite;
    }

    @Override
    protected void init() {
        checkPermission();
//        MyContextWrapper mContext = new MyContextWrapper(this, "Run");
//        helper = new MySQLiteHelper(mContext, "Book.db", null, 2);
        tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db =MyApplication.helper.getWritableDatabase();
            }
        });
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("eee", "checkPermission: 已经授权！");
        }
    }

}
