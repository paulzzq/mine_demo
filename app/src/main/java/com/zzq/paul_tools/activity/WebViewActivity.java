package com.zzq.paul_tools.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zzq.paul_tools.R;

import java.util.HashMap;

import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    //    String url = "http://192.168.15.135:8080/teacher/pages/uclass/courseware-info.html";
//    String url = "http://192.168.15.88:5501/teacher/pages/uclass/courseware-info.html";
//    String url = "https://blog.csdn.net/weixin_40763897/article/details/97398342";
    String url = "http://pay.wdyedu.com/cashier?datetime=1588909818564&trade_no=20200508115018500001278&version=v1.0&app_id=da3j4kbnh4k2po15&sign=e61ba8d9a1db07b22535595a67295605a607ef9b8a3becab50c42f87ca631e95\n";

//    String url = "https://ask.csdn.net/questions/192027?locationNum=10&fps=1";


    @Override
    protected int getLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void init() {
        initView();
    }

    public void initData() {
//        AgentWeb.with(this)//传入Activity
//                .setAgentWebParent(linWeb, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
//                .useDefaultIndicator()// 使用默认进度条
//                .createAgentWeb()//
//                .ready()
//                .go("http://www.jd.com");


//        agentWeb=AgentWeb.with(this)
//                .setAgentWebParent(linWeb, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()//进度条
//                .createAgentWeb()
//                .ready()
//                .go("https://blog.csdn.net/iamchan/article/details/78743074");

    }

    public void initView() {
        WebView webView = findViewById(R.id.web);
        //解决webview加载的网页上的按钮点击失效  以及有些图标显示不出来的问题
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //设置 缓存模式
        webView.requestFocus();
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//                toFinish(RESULT_ERROR_INIT);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("weixin://")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity( intent );
                        return true;
                    } catch (Exception e) {
                        // 防止手机没有安装处理某个 scheme 开头的 url 的 APP 导致 crash
//                        toFinish(NO_WEICHAT_APP);
                        return true;
                    }
                } else if (url.startsWith("alipays://") || url.startsWith("alipay")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity( intent );
                        return true;
                    } catch (Exception e) {
                        // 防止手机没有安装处理某个 scheme 开头的 url 的 APP 导致 crash
                        // 启动支付宝 App 失败，会自行跳转支付宝网页支付
                        return true;
                    }
                }

                // 处理微信 H5 支付跳转时验证请求头 referer 失效
                // 验证不通过会出现“商家参数格式有误，请联系商家解决”
                if (url.contains("wx.tenpay.com")) {
                    // 申请微信 H5 支付时填写的域名
                    // 比如经常用来测试网络连通性的 http://www.baidu.com
                    String referer = "http://pay.wdyedu.com";
                    // 兼容 Android 4.4.3 和 4.4.4 两个系统版本设置 referer 无效的问题
                    if (("4.4.3".equals(android.os.Build.VERSION.RELEASE))
                            || ("4.4.4".equals(android.os.Build.VERSION.RELEASE))) {
                        view.loadDataWithBaseURL(referer, "<script>window.location.href=\"" + url + "\";</script>",
                                "text/html", "utf-8", null);
                        // 修改标记位状态，避免循环调用
                        // 再次进入微信H5支付流程时记得重置状态 firstVisitWXH5PayUrl = true
                        return true;
                        // 返回 false 由系统 WebView 自己处理该 url
                    } else {
                        // HashMap 指定容量初始化，避免不必要的内存消耗
                        HashMap<String, String> map = new HashMap<>(1);
                        map.put("Referer", referer);
                        view.loadUrl(url, map);
                        return true;
                    }
                }
                return false;
            }
        });
        webView.loadUrl(url);

    }
}
