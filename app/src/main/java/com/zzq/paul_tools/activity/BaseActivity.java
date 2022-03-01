package com.zzq.paul_tools.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2019/11/29 16:24
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        this.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 获取布局资源id
     */
    protected abstract int getLayout();

    /**
     * 开始初始化操作，activity准备工作基本完成
     */
    protected abstract void init();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }
}
