package com.zzq.paul_tools.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zzq.paul_tools.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhuzaiqing
 * @describe 测试按钮控制横竖屏效果
 * @time 2020/8/4 16:12
 */
public class XuanzhuanActivity extends BaseActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.layout_horizontal)
    LinearLayout layoutHorizontal;

    @Override
    protected int getLayout() {
        return R.layout.activity_xuanzhuan;
    }

    @Override
    protected void init() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                // 获取屏幕的尺寸
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                // 屏幕宽
                int width = displayMetrics.widthPixels;
                // 屏幕高
                int height = displayMetrics.heightPixels;
                // 设置布局的宽和高,必须要和屏幕的反过来

                layoutHorizontal.setLayoutParams(new LinearLayout.LayoutParams(height, width));
                if(width<height){
                    //当前是竖屏
                    // 顺时针旋转90度
                    layoutHorizontal.setRotation(90f);
                    layoutHorizontal.setY((height - width) / 2);
                    // 将布局位移到屏幕中心
                    layoutHorizontal.setX((width - height) / 2);
                }else{
                    //当前是横屏
                    //逆时针旋转
                    layoutHorizontal.setRotation(90f);
                    layoutHorizontal.setY((height - width) / 2);
                    // 将布局位移到屏幕中心
                    layoutHorizontal.setX((width - height) / 2);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}