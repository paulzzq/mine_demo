package com.zzq.paul_tools.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.adapter.StickHeaderScrollViewAdapter;
import com.zzq.paul_tools.view.MyNestedScrollView;

public class StickHeadScrollviewActivity extends BaseActivity {
    private String TAG = StickHeadScrollviewActivity.class.getSimpleName();

    RecyclerView rv;
    MyNestedScrollView nsv;


    @Override
    protected int getLayout() {
        return R.layout.activity_stick_head_scrollview;
    }

    @Override
    protected void init() {
        rv = findViewById(R.id.rv);
        nsv = findViewById(R.id.nsv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new StickHeaderScrollViewAdapter());
        final View rootView = findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                View topLl = findViewById(R.id.top_ll);
                View topView2 = findViewById(R.id.top_2);
                nsv.setMyScrollHeight(topLl.getHeight());
                int rvNewHeight = rootView.getHeight() - topView2.getHeight()-dip2px(StickHeadScrollviewActivity.this,90);
                rv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,rvNewHeight));
            }
        });
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
