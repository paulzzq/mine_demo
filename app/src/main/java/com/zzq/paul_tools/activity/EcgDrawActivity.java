package com.zzq.paul_tools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.andy.ecgviewlib.EcgViewInterface;
import com.andy.ecgviewlib.EcgWaveView;
import com.zzq.paul_tools.R;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EcgDrawActivity extends AppCompatActivity implements View.OnTouchListener {

    private View view;
    private boolean flag = false;
    private ScheduledExecutorService scheduledExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg_draw);
        ButterKnife.bind(this);
        initView();


    }




    private void initView() {


    }

    private EcgViewInterface ecgViewListener = new EcgViewInterface() {
        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onShowMessage(String t, int i) {
            Log.i("tag", "心电接口回调--》" + t);
            if (i == 0) {
                //  Toast.makeText(getApplication(),"时间：" + t + "ms/格",Toast.LENGTH_SHORT).show();
            } else if (i == 1) {
                //   Toast.makeText(getApplication(),"电压："+t+"mv/格",Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("tag", "linear监听");
        return false;
    }
}
