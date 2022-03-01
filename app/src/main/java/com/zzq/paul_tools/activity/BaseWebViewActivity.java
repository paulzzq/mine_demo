package com.zzq.paul_tools.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.zzq.paul_tools.R;

import butterknife.BindView;

/**
 * Description:  WebView Activity 基类
 * Author:       张丹枫
 * CreateDate:   2019/4/8 15:16
 */
public abstract class BaseWebViewActivity extends BaseActivity {

    @BindView(R.id.back_iv)
    protected ImageView backIv;
    @BindView(R.id.title_tv)
    protected TextView titleTv;
    @BindView(R.id.parent_ll)
    protected LinearLayout parentLl;
    //WebView
    protected AgentWeb mAgentWeb;

    private String titleStr;


    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.initData();
    }

    /**
     * 设置webview
     *
     * @param Url 链接地址
     */
    protected void setWebView(String Url) {
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(parentLl, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件
                .closeIndicator()//关闭进度条
                .setWebViewClient(mWebViewClient)
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb()
                .ready()
                .go(Url);

        //关闭滑动到尽头再划阴影
        mAgentWeb.getWebCreator().getWebView().setOverScrollMode(View.OVER_SCROLL_NEVER);
        //关闭滚动条
        mAgentWeb.getWebCreator().getWebView().setVerticalScrollBarEnabled(false);
        mAgentWeb.getWebCreator().getWebView().setHorizontalScrollBarEnabled(false);

        this.initJs();
    }



    /**
     * 设置title
     *
     * @param title title名称
     */
    protected void setTitle(String title) {
        this.titleStr = title;
        titleTv.setText(titleStr);
    }

    /**
     * 返回webview实例
     */
    protected AgentWeb getAgentWeb() {
        return mAgentWeb;
    }

    @Override
    protected void onPause() {
        if (null != mAgentWeb) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (null != mAgentWeb) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (null != mAgentWeb) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    //WebViewClient 用于监听界面的开始和结束
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //界面开始
//            showLoading();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //界面结束
//            hideLoading();
        }
//        @Override
//        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            handler.proceed();
//            super.onReceivedSslError(view, handler, error);
//        }
    };

    //WebChromeClient 监听界面的改变
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //界面改变
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if(TextUtils.isEmpty(titleStr)){
                titleTv.setText(title);
            }
            super.onReceivedTitle(view, title);
        }
    };

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化Js交互
     */
    protected abstract void initJs();

}
