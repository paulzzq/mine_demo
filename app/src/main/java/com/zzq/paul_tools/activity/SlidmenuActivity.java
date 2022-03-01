package com.zzq.paul_tools.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzq.paul_tools.R;

import butterknife.BindView;

import static android.view.View.VISIBLE;

public class SlidmenuActivity extends BaseActivity {

    @BindView(R.id.btn_ok)
    TextView btnOk;
    @BindView(R.id.extra_ll)
    LinearLayout extraLl;
    @BindView(R.id.arraw_ll)
    LinearLayout arrawLl;
    @BindView(R.id.parent_ll)
    LinearLayout parentLl;

    @Override
    protected int getLayout() {
        return R.layout.activity_slidmenu;
    }

    @Override
    protected void init() {
        getScreenSize();
        btnOk.setTag(0);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //0:显示  1：隐藏
                showSliemenue((Integer) btnOk.getTag());
                //从上往下移动
//                arrawLl.animate().translationY(-(arrawLl.getHeight())).setDuration(0).start();
//                arrawLl.animate().translationY(0).setDuration(500).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        parentLl.setVisibility(VISIBLE);
//                        super.onAnimationStart(animation);
//                    }
//                }).start();


                //从左往右移动
//                arrawLl.animate().translationX(-arrawLl.getHeight()).setDuration(0).start();
//                arrawLl.animate().translationX(0).setDuration(500).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        parentLl.setVisibility(VISIBLE);
//                        super.onAnimationStart(animation);
//                    }
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        parentLl.setVisibility(VISIBLE);
//                        super.onAnimationEnd(animation);
//                    }
//                }).start();

                //从右往左移动

            }
        });
        extraLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSliemenue((Integer) btnOk.getTag());
            }
        });

    }

    private void showSliemenue(int status) {
        switch (status) {
            case 0://显示
                btnOk.setTag(1);
                arrawLl.animate().translationX(2 * getWidth() / 3).setDuration(0).start();
                arrawLl.animate().translationX(0).setDuration(300).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        parentLl.setVisibility(VISIBLE);
                        super.onAnimationStart(animation);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        parentLl.setVisibility(VISIBLE);
                        extraLl.setVisibility(VISIBLE);
                        super.onAnimationEnd(animation);
                    }
                }).start();
                break;
            case 1://隐藏
                btnOk.setTag(0);
                arrawLl.animate().translationX(0).setDuration(0).start();
                arrawLl.animate().translationX(2 * getWidth() / 3).setDuration(300).setListener(new AnimatorListenerAdapter() {
                    //                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        extraLl.setVisibility(View.GONE);
//                        super.onAnimationStart(animation);
//                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        parentLl.setVisibility(View.GONE);
                        extraLl.setVisibility(View.INVISIBLE);
                        super.onAnimationEnd(animation);
                    }
                }).start();
                break;
        }
    }

    private void getScreenSize() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

    }

    private int getWidth() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        return width;
    }

}
