package com.zzq.paul_tools;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zzq.paul_tools.activity.BaseActivity;
import com.zzq.paul_tools.activity.CheckFaceActivity;
import com.zzq.paul_tools.activity.EcgDrawActivity;
import com.zzq.paul_tools.activity.H5Activity;
import com.zzq.paul_tools.activity.ListViewAndListviewActivity;
import com.zzq.paul_tools.activity.NoticeViewActivity;
import com.zzq.paul_tools.activity.NumberInputActivity;
import com.zzq.paul_tools.activity.PayActivity;
import com.zzq.paul_tools.activity.PlayVoiceActivity;
import com.zzq.paul_tools.activity.RadarViewActivity;
import com.zzq.paul_tools.activity.RecycleViewActivity;
import com.zzq.paul_tools.activity.ReflectActivity;
import com.zzq.paul_tools.activity.RxJavaActivity;
import com.zzq.paul_tools.activity.SlidmenuActivity;
import com.zzq.paul_tools.activity.SqliteActivity;
import com.zzq.paul_tools.activity.StatusBarActivity;
import com.zzq.paul_tools.activity.StickHeadScrollviewActivity;
import com.zzq.paul_tools.activity.SurfaceViewActivity;
import com.zzq.paul_tools.activity.TableLayoutActivity;
import com.zzq.paul_tools.activity.TakeCameraActivity;
import com.zzq.paul_tools.activity.TextToSpeechActivity;
import com.zzq.paul_tools.activity.TextViewActivity;
import com.zzq.paul_tools.activity.ViewPagerIndicatorActivity;
import com.zzq.paul_tools.activity.WebViewActivity;
import com.zzq.paul_tools.activity.XuanzhuanActivity;
import com.zzq.paul_tools.adapter.MainToolAdapter;
import com.zzq.paul_tools.bean.MainToolBean;
import com.zzq.paul_tools.service.OneProcessService;
import com.zzq.paul_tools.utils.AppUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @author zhuzaiqing
 * @describe ?????????
 * @time 2018/10/31 15:21
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static final String TAG = "MainActivity";
    //    @BindView(R.id.title)
//    TitleView title;
    @BindView(R.id.main_list)
    ListView mList;
    List<MainToolBean> list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initData();
        initView();
//        Test.main();
//        startService(new Intent(this, OneProcessService.class));



//        Intent intent1 = new Intent("com.android.scanner.service_settings");
//        intent1.putExtra("scan_continue", true);
//        sendBroadcast(intent1);
//
//        Intent intent = new Intent("com.android.scanner.service_settings");
//        intent.putExtra("barcode_send_mode", "BROADCAST");
//        sendBroadcast(intent);

        TestBroadCastReceiver myBroadCastReceiver = new TestBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.server.scannerservice.broadcast");
        registerReceiver(myBroadCastReceiver, intentFilter);

        Log.e("eee","----imei:"+ AppUtil.getIMEI(MainActivity.this));
        Log.e("eee","----SN:"+ AppUtil.getSerialNumber(MainActivity.this));
    }
    class TestBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.android.server.scannerservice.broadcast")) {
                String code = intent.getStringExtra("scannerdata");
                Log.e("eee", "-----------????????????????????????" + code);
            }
        }
    }

    private void test() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 10000);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("eee", "--onRestart");
    }


    public void initData() {
        MainToolBean bean24 = new MainToolBean();
        bean24.setName("texttospeech");
        bean24.setDescribe("?????????????????????????????????????????????");
        bean24.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean23 = new MainToolBean();
        bean23.setName("listview_listview");
        bean23.setDescribe("??????listview????????????");
        bean23.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean22 = new MainToolBean();
        bean22.setName("number_input");
        bean22.setDescribe("?????????????????????");
        bean22.setBitmap(R.mipmap.ic_launcher);


        MainToolBean bean21 = new MainToolBean();
        bean21.setName("take_camera");
        bean21.setDescribe("??????????????????????????????????????????");
        bean21.setBitmap(R.mipmap.ic_launcher);


        MainToolBean bean20 = new MainToolBean();
        bean20.setName("notive_view");
        bean20.setDescribe("????????????");
        bean20.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean19 = new MainToolBean();
        bean19.setName("xuanzhuan");
        bean19.setDescribe("??????????????????");
        bean19.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean18 = new MainToolBean();
        bean18.setName("reflect");
        bean18.setDescribe("????????????");
        bean18.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean17 = new MainToolBean();
        bean17.setName("wdcloud_pay");
        bean17.setDescribe("???????????????????????????");
        bean17.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean16 = new MainToolBean();
        bean16.setName("customer_textview");
        bean16.setDescribe("?????????textview  ???????????????????????????????????????");
        bean16.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean15 = new MainToolBean();
        bean15.setName("check_face");
        bean15.setDescribe("????????????");
        bean15.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean14 = new MainToolBean();
        bean14.setName("sqlite");
        bean14.setDescribe("sqlite??????");
        bean14.setBitmap(R.mipmap.ic_launcher);


        MainToolBean bean13 = new MainToolBean();
        bean13.setName("stickHeaderScrollview");
        bean13.setDescribe("??????????????????scrollview");
        bean13.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean12 = new MainToolBean();
        bean12.setName("playvoiceView");
        bean12.setDescribe("?????????view???????????????????????????");
        bean12.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean11 = new MainToolBean();
        bean11.setName("slidmenu");
        bean11.setDescribe("?????????????????????");
        bean11.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean10 = new MainToolBean();
        bean10.setName("HorizontalScrollView");
        bean10.setDescribe("??????????????????????????????");
        bean10.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean9 = new MainToolBean();
        bean9.setName("tablelayout");
        bean9.setDescribe("??????????????????????????????");
        bean9.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean8 = new MainToolBean();
        bean8.setName("ViewPagerIndicator");
        bean8.setDescribe("??????????????????????????????");
        bean8.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean1 = new MainToolBean();
        bean1.setName("Retrofit2.0+RxJava2.0+okhttp");
        bean1.setDescribe("??????Retrofit2.0+RxJava2.0+okhttp??????????????????");
        bean1.setBitmap(R.mipmap.ic_launcher);


        MainToolBean bean2 = new MainToolBean();
        bean2.setName("EcgDraw");
        bean2.setDescribe("????????????????????????");
        bean2.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean3 = new MainToolBean();
        bean3.setName("StatusBar");
        bean3.setDescribe("????????????????????????????????????");
        bean3.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean4 = new MainToolBean();
        bean4.setName("RecycleView");
        bean4.setDescribe("RecycleView??????????????????");
        bean4.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean5 = new MainToolBean();
        bean5.setName("WebView");
        bean5.setDescribe("Webview????????????");
        bean5.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean6 = new MainToolBean();
        bean6.setName("SurfaceView");
        bean6.setDescribe("????????????SurfaceView??????????????????");
        bean6.setBitmap(R.mipmap.ic_launcher);

        MainToolBean bean7 = new MainToolBean();
        bean7.setName("RadarView");
        bean7.setDescribe("?????????view?????????????????????");
        bean7.setBitmap(R.mipmap.ic_launcher);


        list.add(bean24);
        list.add(bean23);
        list.add(bean22);
        list.add(bean21);
        list.add(bean20);
        list.add(bean19);
        list.add(bean18);
        list.add(bean16);
        list.add(bean15);
        list.add(bean14);
        list.add(bean13);
        list.add(bean12);
        list.add(bean11);
        list.add(bean10);
        list.add(bean9);
        list.add(bean8);
        list.add(bean7);
        list.add(bean6);
        list.add(bean5);
        list.add(bean4);
        list.add(bean3);
        list.add(bean2);
        list.add(bean1);
    }


    public void initView() {
        MainToolAdapter adapter = new MainToolAdapter(MainActivity.this, list);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String name = list.get(i).getName();
        Intent intent;
        switch (name) {
            case "texttospeech":
                intent = new Intent(MainActivity.this, TextToSpeechActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "listview_listview":
                intent = new Intent(MainActivity.this, ListViewAndListviewActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "number_input":
                intent = new Intent(MainActivity.this, NumberInputActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "take_camera":
                intent = new Intent(MainActivity.this, TakeCameraActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "notive_view":
                intent = new Intent(MainActivity.this, NoticeViewActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "Retrofit2.0+RxJava2.0+okhttp":
                intent = new Intent(MainActivity.this, RxJavaActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "EcgDraw":
                intent = new Intent(MainActivity.this, EcgDrawActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "StatusBar":
                intent = new Intent(MainActivity.this, StatusBarActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "RecycleView":
                intent = new Intent(MainActivity.this, RecycleViewActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "WebView":
                intent = new Intent(MainActivity.this, H5Activity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "SurfaceView":
                intent = new Intent(MainActivity.this, SurfaceViewActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "RadarView":
                intent = new Intent(MainActivity.this, RadarViewActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "ViewPagerIndicator":
                intent = new Intent(MainActivity.this, ViewPagerIndicatorActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;

            case "tablelayout":
                intent = new Intent(MainActivity.this, TableLayoutActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "slidmenu":
                intent = new Intent(MainActivity.this, SlidmenuActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "playvoiceView":
                intent = new Intent(MainActivity.this, PlayVoiceActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "stickHeaderScrollview":
                intent = new Intent(MainActivity.this, StickHeadScrollviewActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "sqlite":
                intent = new Intent(MainActivity.this, SqliteActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;

            case "check_face":
                intent = new Intent(MainActivity.this, CheckFaceActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;

            case "customer_textview":
                intent = new Intent(MainActivity.this, TextViewActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "wdcloud_pay":
                intent = new Intent(MainActivity.this, PayActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;

            case "reflect":
                intent = new Intent(MainActivity.this, ReflectActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;
            case "xuanzhuan":
                intent = new Intent(MainActivity.this, XuanzhuanActivity.class);
                intent.putExtra("data", name);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int i = 0;
        Log.e("eee", "--onResume");

    }
}

