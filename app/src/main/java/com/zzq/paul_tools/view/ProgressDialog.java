package com.zzq.paul_tools.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzq.paul_tools.R;

/**
 * @author zhuzaiqing
 * @describe 加载框
 * @time 2018/11/2 15:05
 */

public class ProgressDialog extends Dialog {


    private Context context;
    private String bluetooth_state;
    private TextView tv_bluetooth_state;
    private  ImageView img_view;
    private Animation animation;

    public ProgressDialog(Context context, String bluetooth_state) {
        super(context, R.style.dialog_custom);
        this.context = context;
        this.bluetooth_state = bluetooth_state;
    }

    private boolean isVisible = false;

    public void setVisibleforXiuDaiYa(boolean Visible) {
        isVisible = Visible;
    }

    public void setState(String blueString) {
        tv_bluetooth_state.setText(blueString);
    }

    public void setXiuDaYa(String xiudaya, String state) {
        tv_bluetooth_state.setText(state);
    }

    public interface GetUserIdLinsterner {
        void getUserId(String id);
    }

    public void setGetUserIdLinsterner(GetUserIdLinsterner userIdLinsterner) {
        this.userIdLinsterner = userIdLinsterner;
    }

    public GetUserIdLinsterner userIdLinsterner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        initView();
    }

    private void initView() {
         img_view = (ImageView) findViewById(R.id.img_view);
        tv_bluetooth_state = (TextView) findViewById(R.id.tv_bluetooth_state);
        tv_bluetooth_state.setText(bluetooth_state);


        // 开启动画
         animation = AnimationUtils.loadAnimation(context,
                R.anim.img_animation);
        LinearInterpolator lin = new LinearInterpolator();//
        animation.setInterpolator(lin);
        img_view.startAnimation(animation);

    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(animation!=null){
            animation.cancel();
            animation=null;
        }
    }
}
