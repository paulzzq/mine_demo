package com.zzq.paul_tools.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.zzq.paul_tools.MainActivity;
import com.zzq.paul_tools.MyApplication;
import com.zzq.paul_tools.R;
import com.zzq.paul_tools.utils.RegexUtils;
import com.zzq.paul_tools.view.CustomNumKeyView;
import com.zzq.paul_tools.view.floatwindow.FloatWindowService;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @Description: 自定义数字键盘
 * @Author: zzq
 * @Date: 2021-05-10 15:24
 */
public class NumberInputActivity extends BaseActivity {

    @BindView(R.id.pwd_view)
    CustomNumKeyView pwdView;
    @BindView(R.id.only_id_edt)
    TextView onlyIdEdt;


    @Override
    protected int getLayout() {
        return R.layout.activity_number_input;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    protected void init() {

        Intent intent = new Intent(this, FloatWindowService.class);
        startService(intent);
//finish();
        pwdView.setOnCallBack(new CustomNumKeyView.CallBack() {
            @Override
            public void clickNum(String num) {
                Log.e("eee", "-------numL:" + num);
            }

            @Override
            public void deleteNum() {
//                if (focusType == 1) {
//                    int last = secondSafePhoneEt.getText().length();
//                    if (last > 0) {
//                        //删除最后一位
//                        secondSafePhoneEt.getText().delete(last - 1, last);
//                    }
//                } else {
//                    int last = secondSafeVerfyEt.getText().length();
//                    if (last > 0) {
//                        //删除最后一位
//                        secondSafeVerfyEt.getText().delete(last - 1, last);
//                    }
//                }
            }
        });
        onlyIdEdt.setText( getDeviceID(NumberInputActivity.this));
    }

    static String onlyId = "";

    public   String getDeviceID(Context context) {
        onlyId = getUniqeId(context);
        return onlyId;
    }

    public static String getUniqeId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!TextUtils.isEmpty(androidId) && !RegexUtils.isZero(androidId)
                && !"9774d56d682e549c".equals(androidId)) {
            return androidId;
        } else if (!TextUtils.isEmpty(Build.SERIAL) && !RegexUtils.isZero(Build.SERIAL)
                && !"unknown".equals(Build.SERIAL)) {
            return Build.SERIAL;
        }
        return null;
    }


}