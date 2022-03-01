package com.zzq.paul_tools.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebViewClient;

/**
 * Description:  网页加载帮助类
 * Author:       张丹枫
 * CreateDate:   2019/4/8 10:41
 */
public class HtmlUtil {

    public static final String JSKey = "UTeacher_Android";

    /**
     * html注入本地js css
     */
    public static String injectJsCss(String page) {
        String css1 = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/math.css\" type=\"text/css\">";
        String css2 = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/katex.css\" type=\"text/css\">";

        String js1 = "<script src=\"file:///android_asset/js/jquery.min.js\"></script>";
        String js2 = "<script src=\"file:///android_asset/js/mathquill.js\"></script> ";
        String js3 = "<script src=\"file:///android_asset/js/katex.js\"></script>";
        String js4 = "<script src=\"file:///android_asset/js/auto.render.js\"></script> ";

        int bodyEnd = page.indexOf("</body>");
        if (bodyEnd > 0) {
            page = page.substring(0, bodyEnd) + js1 + js2 + js3 + js4 + page.substring(bodyEnd, page.length());
        } else {
            page = "<body>" + page + js1 + js2 + js3 + js4 + "</body>";
        }

        int headEnd = page.indexOf("</head>");
        if (headEnd > 0) {
            page = page.substring(0, headEnd) + css1 + css2 + page.substring(headEnd, page.length());
        } else {
            page = "<head>" + css1 + css2 + "</head>" + page;
        }

        if (page.indexOf("<!DOCTYPE html>") <= 0) {
            page = "<!DOCTYPE html><html lang=\"zh\">" + page + "</html>";
        }

        return page;
    }


    /**
     * 加载webView链接
     *
     * @param WebRoot webView父控件
     * @param url     链接
     */
    public static AgentWeb loadWebUrl(Activity activity, ViewGroup WebRoot, String url) {
        return loadWebUrl(activity, WebRoot, url, null, null);
    }

    /**
     * 加载webView链接
     *
     * @param WebRoot     webView父控件
     * @param url         链接
     * @param defaultView 默认占位view
     */
    public static AgentWeb loadWebUrl(Activity activity, ViewGroup WebRoot, String url, View defaultView) {
        return loadWebUrl(activity, WebRoot, url, null, defaultView);
    }

    /**
     * 加载webView链接
     *
     * @param WebRoot webView父控件
     * @param url     链接
     * @param jsClass js方法类
     */
    public static AgentWeb loadWebUrl(Activity activity, ViewGroup WebRoot, String url, Object jsClass) {
        return loadWebUrl(activity, WebRoot, url, jsClass, null);
    }

    /**
     * 加载webView链接
     *
     * @param WebRoot webView父控件
     * @param url     链接
     * @param jsClass js方法类
     */
    public static AgentWeb loadWebUrl(Activity activity, ViewGroup WebRoot, String url, Object jsClass, final View defaultView) {
        AgentWeb agentWeb;
        if (null == jsClass) {
            agentWeb = AgentWeb.with(activity)//传入Activity
                    .setAgentWebParent(WebRoot, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                            , ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb 的父控件
                    .closeIndicator()//关闭进度条
                    .setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            //界面开始
                            if (null != defaultView
                                    && defaultView.getVisibility() == View.GONE) {
                                defaultView.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onPageFinished(WebView view, String url) {
                            //界面结束
                            if (null != defaultView
                                    && defaultView.getVisibility() == View.VISIBLE) {
                                defaultView.setVisibility(View.GONE);
                            }
                        }
                    })
                    .createAgentWeb()
                    .ready()
                    .go((url));
        } else {
            agentWeb = AgentWeb.with(activity)//传入Activity
                    .setAgentWebParent(WebRoot, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                            , ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb 的父控件
                    .closeIndicator()//关闭进度条
                    .setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            //界面开始
                            if (null != defaultView
                                    && defaultView.getVisibility() == View.GONE) {
                                defaultView.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onPageFinished(WebView view, String url) {
                            //界面结束
                            if (null != defaultView
                                    && defaultView.getVisibility() == View.VISIBLE) {
                                defaultView.setVisibility(View.GONE);
                            }
                        }
                    })
                    .addJavascriptInterface(JSKey, jsClass)
                    .createAgentWeb()
                    .ready()
                    .go((url));
        }

        //关闭滑动到尽头再划阴影
        agentWeb.getWebCreator().getWebView().setOverScrollMode(View.OVER_SCROLL_NEVER);
        //关闭滚动条
        agentWeb.getWebCreator().getWebView().setVerticalScrollBarEnabled(false);
        agentWeb.getWebCreator().getWebView().setHorizontalScrollBarEnabled(false);

        return agentWeb;
    }







    /**
     * 加载webview链接
     *
     * @param webView webView控件
     * @param url     链接
     */
    public static void loadWebUrl(WebView webView, String url, Object jsClass) {
        setWebViewSetting(webView, jsClass);
        webView.loadUrl(url);
    }

    /**
     * 设置webview属性
     */
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    public static void setWebViewSetting(WebView webView, Object jsClass) {
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        if (null != jsClass) {
            webView.addJavascriptInterface(jsClass, JSKey);
        }
        webSettings.setDomStorageEnabled(true);//兼容不支持的标签
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //不使用缓存，一直使用网络请求
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setBuiltInZoomControls(false); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setAppCacheEnabled(false);// 设置启动缓存
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.requestFocusFromTouch();
    }

}
