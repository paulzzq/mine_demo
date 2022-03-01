package com.zzq.paul_tools.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.view.LoadingImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @describe  调试伟东云支付页面
 * @author zhuzaiqing
 * @time 2020/5/8 9:51
 */
public class PayActivity  {



//    /**
//     * 待付款
//     */
//    private static final int RESULT_ERROR_INIT = 0;
//    /**
//     * 支付成功
//     */
//    private static final int RESULT_SUCESS = 2;
//    /**
//     * 参数错误
//     */
//    private static final int RESULT_PARAMS_ERROR = -4;
//    /**
//     * 下订单出错
//     */
//    private static final int RESULT_SIGN_ORDER_ERROR = -1;
//    /**
//     * 用户主动取消支付
//     */
//    private static final int RESULT_USER_CANCLE = -5;
//    /**
//     * 查询支付结果出错
//     */
//    private static final int RESULT_CHECK_ORDER_ERROR = -2;
//
//
//    /**
//     * 订单支付失败
//     */
//    private static final int RESULT_PAY_ERROR = 407;
//    /**
//     * 订单关闭
//     */
//    private static final int RESULT_ORDER_CLOSED = 408;
//    /**
//     * 已退款
//     */
//    private static final int RESULT_ORDER_REFOND = 409;
//    /**
//     * 没有支付宝客户端
//     */
//    private static final int NO_ALI_APP = -6;
//    /**
//     * 没有微信客户端
//     */
//    private static final int NO_WEICHAT_APP = -7;
//
//    private CallbackContext mResultCallBack;
//    private String mIp;
//
//    private WebView mWebView, mPayWebview;
//    private String mParmas;
//    private String mTradeNo;
//    private LoadingImageView mLoadingView;
//    private FrameLayout mFlLoadingRoot;
//
//    private boolean mToPayActivity = false;
//    private String encode;
//    private FrameLayout parentframeLayout;
//
//    @Override
//    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
//        super.initialize(cordova, webView);
//    }
//
//    @Override
//    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
//        this.mResultCallBack = callbackContext;
//        switch (action) {
//            case "payOrder":
////                if (!TextUtils.isEmpty(mParmas)) {
////                    return false;
////                }
//                mParmas = args.getString(0);
//                cordova.getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showPayDialog();
//                    }
//                });
//                return true;
//            default:
//                return false;
//        }
//    }
//
//
//    @TargetApi(14)
//    public void makeOrder() {
//        if (TextUtils.isEmpty(mIp)) {
//            getOutNetIp();
//            return;
//        }
//        if (!checkParams()) {
//            return;
//        }
//        JSONObject object;
//        try {
//            object = new JSONObject(mParmas);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return;
//        }
//        Map<String, String> paramsMap = new LinkedHashMap<>();
//        String app_id = object.optString("app_id");
//        String version = object.optString("version");
//
//
//        String detail = object.optString("detail");
//        String goods = object.optString("goods");
//        String notify_url = object.optString("notify_url");
//        String out_trade_no = object.optString("out_trade_no");
//        final String real_fee = object.optString("real_fee");
//        String time_expire = object.optString("time_expire");
//        String total_fee = object.optString("total_fee");
//
//
//        paramsMap.put("app_id", app_id);
//        paramsMap.put("detail", detail);
//        paramsMap.put("goods", goods);
//        paramsMap.put("notify_url", notify_url);
//        paramsMap.put("out_trade_no", out_trade_no);
//        paramsMap.put("real_fee", real_fee);
//        paramsMap.put("time_expire", time_expire);
//        paramsMap.put("total_fee", total_fee);
//        paramsMap.put("version", version);
//        paramsMap.put("return_url", "www.baidu.com");
//        paramsMap.put("datetime", System.currentTimeMillis() + "");
//        paramsMap.put("ip", mIp);
//        paramsMap.put("locale", "zh_CN");
//
//        String access_key = object.optString("access_key");
//        String secret_key = object.optString("secret_key");
//
//        String sign = paySignDesposit(paramsMap, access_key, secret_key);
//        paramsMap.put("sign", sign);
//        String requset = postRequset("http://pay.wdyedu.com/api/trade/unifiedorder", paramsMap);
////        String requset = postRequset("http://pay.test.wdyclass.com/api/trade/unifiedorder", paramsMap);
//
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(requset);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            toFinish(RESULT_SIGN_ORDER_ERROR);
//            return;
//        }
//        String status = jsonObject.optString("status");
//        if("200".equals(status))
//        {
//            mTradeNo = jsonObject.optJSONObject("data").optString("trade_no");
//            HashMap<String, String> map = new HashMap<>();
//            String dataTime = System.currentTimeMillis() + "";
//            map.put("app_id", app_id);
//            map.put("version", version);
//            map.put("datetime", dataTime);
//            map.put("trade_no", mTradeNo);
//            map.put("locale", "zh_CN");
//            map.put("is_app", "1");
//
//            String signUrl = paySignDesposit(map, access_key, secret_key);
//            final String url = "http://pay.wdyedu.com/cashier?trade_no=" + mTradeNo + "&app_id=" + app_id + "&datetime=" + dataTime + "&version=" + version + "&sign=" + signUrl + "&locale=zh_CN&is_app=1";
//            cordova.getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    mWebView.loadUrl(url);
//                }
//            });
//        }
//        else
//        {
//            toFinish(RESULT_SIGN_ORDER_ERROR);
//        }
//    }
//    private void checkPayResult() {
//        mFlLoadingRoot.setVisibility(View.VISIBLE);
//        parentframeLayout.setVisibility(View.VISIBLE);
//        JSONObject object;
//        try {
//            object = new JSONObject(mParmas);
//        } catch (JSONException e) {
//            toFinish(RESULT_CHECK_ORDER_ERROR);
//            return;
//        }
//        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("datetime", System.currentTimeMillis() + "");
//        paramsMap.put("app_id", object.optString("app_id"));
//        paramsMap.put("version", object.optString("version"));
//        paramsMap.put("trade_no", mTradeNo);
//        String access_key = object.optString("access_key");
//        String secret_key = object.optString("secret_key");
//        String sign = paySignDesposit(paramsMap, access_key, secret_key);
//        paramsMap.put("sign", sign);
////        String requset = postRequset("http://pay.wdyedu.com/api/trade/getorderstatus", paramsMap);
//        String requset = postRequset("http://pay.wdyedu.com/api/trade/getorderstatus", paramsMap);
//
//        JSONObject result;
//        try {
//            result = new JSONObject(requset);
//            if ("200".equals(result.optString("status"))) {
//                JSONObject data = result.optJSONObject("data");
//                int status = data.optInt("status");
//                toFinish(status);
//            } else {
//                toFinish(RESULT_CHECK_ORDER_ERROR);
//            }
//        } catch (JSONException e) {
//            toFinish(RESULT_CHECK_ORDER_ERROR);
//        }
//    }
//
//    /**
//     * 检查参数
//     */
//    private boolean checkParams() {
//        if (TextUtils.isEmpty(mParmas)) {
//            toFinish(RESULT_PARAMS_ERROR);
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void onResume(boolean multitasking) {
//        super.onResume(multitasking);
//        if (mToPayActivity) {
//            checkPayResult();
//        }
//    }
//
//    /**
//     * 关闭页面
//     *
//     * @param errorCode
//     */
//    @SuppressWarnings("all")
//    private void toFinish(final int errorCode) {
//        cordova.getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if(errorCode==RESULT_SUCESS)
//                {
//                    destoryWebview(mWebView);
//                    destoryWebview(mPayWebview);
//                    if (null != dialog && dialog.isShowing()) {
//                        dialog.dismiss();
//                        dialog = null;
//                    }
//                    mToPayActivity = false;
//                    mLoadingView = null;
//                    mFlLoadingRoot = null;
//                    mParmas = null;
//                    if (null != mResultCallBack) {
//                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, errorCode);
//                        pluginResult.setKeepCallback(true);
//                        mResultCallBack.sendPluginResult(pluginResult);
//                    }
//                }
//                else
//                {
//                    destoryWebview(mWebView);
//                    destoryWebview(mPayWebview);
//                    if (null != dialog && dialog.isShowing()) {
//                        dialog.dismiss();
//                        dialog = null;
//                    }
//                    mToPayActivity = false;
//                    mLoadingView = null;
//                    mFlLoadingRoot = null;
//                    mParmas = null;
//                    if (null != mResultCallBack) {
//                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, errorCode);
//                        pluginResult.setKeepCallback(true);
//                        mResultCallBack.sendPluginResult(pluginResult);
//                    }
//                }
//            }
//        });
//    }
//
//    protected void destoryWebview(WebView webView) {
//        if (webView != null) {
//            webView.clearHistory();
//            webView.loadUrl("about:blank");
//            webView.stopLoading();
//            webView.setWebChromeClient(null);
//            webView.setWebViewClient(null);
//            webView.destroy();
//            webView = null;
//        }
//    }
//
//
//    private Dialog dialog;
//
//    @SuppressLint("JavascriptInterface")
//    @TargetApi(14)
//    private void showPayDialog() {
//        dialog = new Dialog(cordova.getActivity());
//
//        LinearLayout dialogRootView = new LinearLayout(cordova.getActivity());
//        dialogRootView.setOrientation(LinearLayout.VERTICAL);
//        int screenWidth = ((WindowManager) cordova.getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
//        dialog.setContentView(dialogRootView);
//        try {
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        dialog.getWindow().setLayout((ViewGroup.LayoutParams.MATCH_PARENT), ViewGroup.LayoutParams.MATCH_PARENT);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK && !mToPayActivity) {
//                    toFinish(RESULT_USER_CANCLE);
//                }
//                return mToPayActivity;
//            }
//        });
//
//        layoutPage(dialogRootView, screenWidth, cordova.getActivity());
//
//        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
//        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示
//
//        WebSettings mWebSettings = mWebView.getSettings();
//        mWebSettings.setSupportZoom(true);
//        mWebSettings.setLoadWithOverviewMode(true);
//        mWebSettings.setUseWideViewPort(true);
//        mWebSettings.setDefaultTextEncodingName("utf-8");
//        mWebSettings.setLoadsImagesAutomatically(true);
//        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//
//        mWebSettings.setAllowFileAccess(true);
//        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//
//        //调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
//        mWebSettings.setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(new InsertObj(), "Android");
//        saveData(mWebSettings);
//        newWin(mWebSettings);
//        mWebView.setWebChromeClient(webChromeClient);
//        mWebView.setWebViewClient(new WebViewClient() {
//
//            /**
//             * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
//             */
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                aLiPay(url);
//                return true;
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                toFinish(RESULT_ERROR_INIT);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                mFlLoadingRoot.setVisibility(View.GONE);
//                parentframeLayout.setVisibility(View.GONE);
//            }
//        });
//        dialog.show();
//        cordova.getThreadPool().execute(new Runnable() {
//            @Override
//            public void run() {
//                if (checkParams()) {
//                    getOutNetIp();
//                }
//            }
//        });
//    }
//
//    /**
//     * 编写页面
//     *
//     * @param dialogRootView
//     * @param screenWidth
//     * @param activity
//     */
//    private void layoutPage(LinearLayout dialogRootView, int screenWidth, Activity activity) {
//        FrameLayout titleRoot = new FrameLayout(activity);
//        FrameLayout.LayoutParams titleRootParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenWidth / 8);
//        titleRoot.setLayoutParams(titleRootParams);
//        dialogRootView.addView(titleRoot);
//        titleRoot.setBackgroundColor(Color.WHITE);
//
//        ImageView ivBack = new ImageView(activity);
//        FrameLayout.LayoutParams ivBackParams = new FrameLayout.LayoutParams(screenWidth / 8, screenWidth / 8);
//        ivBack.setLayoutParams(ivBackParams);
//        titleRoot.addView(ivBack);
//        int paddleft = screenWidth / 19;
//        int paddTop = screenWidth / 24;
//        ivBack.setPadding(paddleft, paddTop, paddleft, paddTop);
//        ivBack.setImageBitmap(getBackBitmap(screenWidth / 8 - paddleft * 2, screenWidth / 8 - paddTop * 2));
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toFinish(RESULT_USER_CANCLE);
//            }
//        });
//
//        TextView tvTitle = new TextView(activity);
//        FrameLayout.LayoutParams tvTitleParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        tvTitleParams.gravity = Gravity.CENTER;
//        tvTitle.setLayoutParams(tvTitleParams);
//        titleRoot.addView(tvTitle);
//        tvTitle.setTextColor(Color.parseColor("#333333"));
//        tvTitle.setTextSize(dip2px(activity, 5.8f));
//        try {
//            JSONObject object = new JSONObject(mParmas);
//            String title = object.optString("title");
//            if (TextUtils.isEmpty(title)) {
//                tvTitle.setText("收银台");
//            } else {
//                tvTitle.setText(title);
//            }
//        } catch (JSONException e) {
//            tvTitle.setText("收银台");
//        }
//
//        View divide = new View(activity);
//        LinearLayout.LayoutParams divideParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(activity, 1.0f));
//        divide.setLayoutParams(divideParams);
//        dialogRootView.addView(divide);
//        divide.setBackgroundColor(Color.parseColor("#cccccc"));
//
//        FrameLayout frameLayout = new FrameLayout(activity);
//        FrameLayout.LayoutParams bottomRootparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        frameLayout.setLayoutParams(bottomRootparams);
//        dialogRootView.addView(frameLayout);
//
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        mWebView = new WebView(activity.getApplicationContext());
//        mWebView.setLayoutParams(params);
//        frameLayout.addView(mWebView);
////        ===================
//        parentframeLayout = new FrameLayout(activity);
//        FrameLayout.LayoutParams parnetparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        parentframeLayout.setLayoutParams(parnetparams);
//        frameLayout.addView(parentframeLayout);
//        parentframeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
////        =======
//        mFlLoadingRoot = new FrameLayout(activity);
//        FrameLayout.LayoutParams loadingRootParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        loadingRootParams.gravity = Gravity.CENTER;
//        mFlLoadingRoot.setLayoutParams(loadingRootParams);
//        parentframeLayout.addView(mFlLoadingRoot);
//        int padding = screenWidth / 30;
//        mFlLoadingRoot.setPadding(padding, padding, padding, padding);
//        mFlLoadingRoot.setBackgroundDrawable(getRoundRectDrawable(padding / 4));
//
//        mLoadingView = new LoadingImageView(activity);
//        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        mLoadingView.setLayoutParams(imageParams);
//        mFlLoadingRoot.addView(mLoadingView);
////        frameLayout.addView(parentframeLayout);
//    }
//
//    private Bitmap getBackBitmap(int w, int h) {
//        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setDither(true);
//        paint.setStrokeCap(Paint.Cap.BUTT);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setColor(Color.parseColor("#333333"));
//        paint.setStrokeWidth(dip2px(cordova.getActivity(), 1.2f));
//        canvas.drawLine(w, 0, 0, h / 2, paint);
//        canvas.drawLine(0, h / 2, w, h, paint);
//        return bitmap;
//    }
//
//    /**
//     * 得到矩形(圆角)
//     */
//    private Drawable getRoundRectDrawable(int radius) {
//        GradientDrawable gd = new GradientDrawable();//创建drawable
//        gd.setSize(15, 15);
//        gd.setColor(Color.parseColor("#aa000000"));
//        float[] radii = {radius, radius, radius, radius, radius, radius, radius, radius};
//        gd.setCornerRadii(radii);
//        gd.setGradientType(GradientDrawable.RECTANGLE);
//        return gd;
//    }
//
//    /**
//     * 多窗口的问题
//     */
//    private void newWin(WebSettings mWebSettings) {
//        //html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
//        //然后 复写 WebChromeClient的onCreateWindow方法
//        mWebSettings.setSupportMultipleWindows(false);
//        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//    }
//
//    /**
//     * HTML5数据存储
//     */
//    @TargetApi(14)
//    private void saveData(WebSettings mWebSettings) {
//        //有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
//        mWebSettings.setDomStorageEnabled(true);
//        mWebSettings.setDatabaseEnabled(true);
//        mWebSettings.setAppCacheEnabled(true);
//        String appCachePath = cordova.getActivity().getApplicationContext().getCacheDir().getAbsolutePath();
//        mWebSettings.setAppCachePath(appCachePath);
//    }
//
//
//    @TargetApi(14)
//    private void aLiPay(String url) {
//        mFlLoadingRoot.setVisibility(View.VISIBLE);
//        parentframeLayout.setVisibility(View.VISIBLE);
//        mPayWebview = new WebView(cordova.getActivity().getApplicationContext());
//        mPayWebview.setHorizontalScrollBarEnabled(false);//水平不显示
//        mPayWebview.setVerticalScrollBarEnabled(false); //垂直不显示
//
//        WebSettings mWebSettings = mPayWebview.getSettings();
//        mWebSettings.setSupportZoom(true);
//        mWebSettings.setLoadWithOverviewMode(true);
//        mWebSettings.setUseWideViewPort(true);
//        mWebSettings.setDefaultTextEncodingName("utf-8");
//        mWebSettings.setLoadsImagesAutomatically(true);
//        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//
//        mWebSettings.setAllowFileAccess(true);
//        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//
//        //调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
//        mWebSettings.setJavaScriptEnabled(true);
//        mPayWebview.addJavascriptInterface(new InsertObj(), "Android");
//
//        mPayWebview.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                toFinish(RESULT_ERROR_INIT);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (url.startsWith("weixin://")) {
//                    try {
//                        mToPayActivity = true;
//                        cordova.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
////                        mFlLoadingRoot.setVisibility(View.GONE);
////                        parentframeLayout.setVisibility(View.GONE);
//                        return true;
//                    } catch (Exception e) {
//                        // 防止手机没有安装处理某个 scheme 开头的 url 的 APP 导致 crash
//                        toFinish(NO_WEICHAT_APP);
//                        return true;
//                    }
//                } else if (url.startsWith("alipays://") || url.startsWith("alipay")) {
//                    try {
//                        mToPayActivity = true;
//                        cordova.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
////                        mFlLoadingRoot.setVisibility(View.GONE);
////                        parentframeLayout.setVisibility(View.GONE);
//                        return true;
//                    } catch (Exception e) {
//                        // 防止手机没有安装处理某个 scheme 开头的 url 的 APP 导致 crash
//                        // 启动支付宝 App 失败，会自行跳转支付宝网页支付
//                        toFinish(NO_ALI_APP);
//                        return true;
//                    }
//                }
//
//                // 处理微信 H5 支付跳转时验证请求头 referer 失效
//                // 验证不通过会出现“商家参数格式有误，请联系商家解决”
//                if (url.contains("wx.tenpay.com")) {
//                    // 申请微信 H5 支付时填写的域名
//                    // 比如经常用来测试网络连通性的 http://www.baidu.com
//                    String referer = "http://pay.wdyedu.com";
//                    // 兼容 Android 4.4.3 和 4.4.4 两个系统版本设置 referer 无效的问题
//                    if (("4.4.3".equals(android.os.Build.VERSION.RELEASE))
//                            || ("4.4.4".equals(android.os.Build.VERSION.RELEASE))) {
//                        view.loadDataWithBaseURL(referer, "<script>window.location.href=\"" + url + "\";</script>",
//                                "text/html", "utf-8", null);
//                        // 修改标记位状态，避免循环调用
//                        // 再次进入微信H5支付流程时记得重置状态 firstVisitWXH5PayUrl = true
//                        return true;
//                        // 返回 false 由系统 WebView 自己处理该 url
//                    } else {
//                        // HashMap 指定容量初始化，避免不必要的内存消耗
//                        HashMap<String, String> map = new HashMap<>(1);
//                        map.put("Referer", referer);
//                        view.loadUrl(url, map);
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//        mPayWebview.loadUrl(url);
//    }
//
//
//    WebChromeClient webChromeClient = new WebChromeClient() {
//        @Override
//        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
//            transport.setWebView(view);
//            resultMsg.sendToTarget();
//            return true;
//        }
//    };
//
//
//    class InsertObj extends Object {
//        //给html提供的方法，js中可以通过：var str = window.jsObj.HtmlcallJava(); 获取到
//        @JavascriptInterface
//        public String HtmlcallJava() {
//            return "Html call Java";
//        }
//
//        //给html提供的有参函数 ： window.jsObj.HtmlcallJava2("IT-homer blog");
//        @JavascriptInterface
//        public String HtmlcallJava2(final String param) {
//            return "Html call Java : " + param;
//        }
//
//        //Html给我们提供的函数
//        @JavascriptInterface
//        public void JavacallHtml() {
//            cordova.getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    //这里是调用方法
//                }
//            });
//        }
//
//        //JS需要调用的方法
//        @JavascriptInterface
//        public void detail(final String arg) {
//            cordova.getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                }
//            });
//        }
//
//        //JS需要调用的方法
//        @JavascriptInterface
//        public void callHandler(final String arg) {
//            cordova.getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });
//        }
//    }
//
//
//    private void getOutNetIp() {
//        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return GetNetIp();
//            }
//        });
//        new Thread(task).start();
//        String s = null;
//        try {
//            s = task.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mIp = s;
//        makeOrder();
//    }
//
//    /**
//     * 获取外网IP地址
//     *
//     * @return
//     */
//    private String GetNetIp() {
//        String IP = "";
//        try {
//            String address = "https://www.taobao.com/help/getip.php";
//            URL url = new URL(address);
//            HttpURLConnection connection = (HttpURLConnection) url
//                    .openConnection();
//            connection.setUseCaches(false);
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36"); //设置浏览器ua 保证不出现503
//
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                InputStream in = connection.getInputStream();
//
//                // 将流转化为字符串
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(in));
//
//                String tmpString = "";
//                StringBuilder retJSON = new StringBuilder();
//                while ((tmpString = reader.readLine()) != null) {
//                    retJSON.append(tmpString + "\n");
//                }
//                try {
//                    if (TextUtils.isEmpty(retJSON)) {
//                        IP = "";
//                    } else {
//                        IP = retJSON.substring(retJSON.indexOf("ip:") + 4, retJSON.length() - 4);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    IP = "";
//                }
//            } else {
//                IP = "";
//            }
//        } catch (Exception e) {
//            IP = "";
//        }
//        return IP;
//    }
//
//    /**
//     * HmacSHA256类型签名
//     *
//     * @param map
//     * @return
//     * @throws UnsupportedEncodingException
//     * @throws NoSuchAlgorithmException
//     * @throws InvalidKeyException
//     */
//    private String paySignDesposit(Map<String, String> map, String access_key, String secretKey) {
//        Map<String, String> params = new LinkedHashMap<>();
//        Set<String> set = map.keySet();
//        for (String string : set) {
//            if (!TextUtils.isEmpty(map.get(string))) {
//                params.put(string, String.valueOf(map.get(string)));
//            }
//        }
//        String string1 = createSign(params);
//        String stringSignTemp = null;
//        stringSignTemp = string1 + "&access_key=" + access_key;
//        String md5 = md5Decode(stringSignTemp).toUpperCase();
//        Mac sha256_HMAC = null;
//        try {
//            sha256_HMAC = Mac.getInstance("HmacSHA256");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
//        try {
//            sha256_HMAC.init(secret_key);
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        try {
//            return byteArrayToHexString(sha256_HMAC.doFinal(md5.getBytes("utf-8"))).toLowerCase();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//
//    private String md5Decode(String content) {
//        byte[] hash;
//        try {
//            hash = MessageDigest.getInstance("MD5").digest(content.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//            toFinish(RESULT_ERROR_INIT);
//            return "";
//        }
//        //对生成的16字节数组进行补零操作
//        StringBuilder hex = new StringBuilder(hash.length * 2);
//        for (byte b : hash) {
//            if ((b & 0xFF) < 0x10) {
//                hex.append("0");
//            }
//            hex.append(Integer.toHexString(b & 0xFF));
//        }
//        return hex.toString();
//    }
//
//
//    /**
//     * 将加密后的字节数组转换成字符串
//     *
//     * @param b 字节数组
//     * @return 字符串
//     */
//    private String byteArrayToHexString(byte[] b) {
//        StringBuilder hs = new StringBuilder();
//        String stmp;
//        for (int n = 0; b != null && n < b.length; n++) {
//            stmp = Integer.toHexString(b[n] & 0XFF);
//            if (stmp.length() == 1)
//                hs.append('0');
//            hs.append(stmp);
//        }
//        return hs.toString().toLowerCase();
//    }
//
//    /**
//     * 构造package
//     *
//     * @param params
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    private String createSign(Map<String, String> params) {
//        Set<String> keysSet = params.keySet();
//        Object[] keys = keysSet.toArray();
//        Arrays.sort(keys);
//        StringBuffer temp = new StringBuffer();
//        boolean first = true;
//        for (Object key : keys) {
//            if (first) {
//                first = false;
//            } else {
//                temp.append("&");
//            }
//            temp.append(key.toString()).append("=");
////            Object value = params.get(key);
////            String valueString = "";
////            if (null != value) {
////                valueString = value.toString();
////            }
//            try {
//                encode = URLEncoder.encode(params.get(key), "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            String typeencode = encodeType(encode);
//            temp.append(typeencode);
//        }
//        return temp.toString();
//    }
//
//    private String postRequset(final String url, final Map<String, String> map) {
//        final StringBuilder sb = new StringBuilder();
//        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                HttpURLConnection connection = null;
//                BufferedReader reader = null;
//                try {
//                    URL requestUrl = new URL(url);
//                    connection = (HttpURLConnection) requestUrl.openConnection();
//                    connection.setRequestMethod("POST");
//                    connection.setConnectTimeout(8000);//链接超时
//                    connection.setReadTimeout(8000);//读取超时
//                    //发送post请求必须设置
//                    connection.setDoOutput(true);
//                    connection.setDoInput(true);
//                    connection.setUseCaches(false);
//                    connection.setInstanceFollowRedirects(true);
//                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                    StringBuilder request = new StringBuilder();
//                    String s = map.toString();
//                    Object[] objects = map.keySet().toArray();
//                    int length = objects.length;
//                    for (int i = 0; i < length; i++) {
//                        String key = (String) objects[i];
//                        if (i == length - 1) {
//                            request.append(key + "=" + URLEncoder.encode(map.get(key), "UTF-8").replace(" ", "20%"));
//                        } else {
//                            request.append(key + "=" + URLEncoder.encode(map.get(key), "UTF-8").replace(" ", "20%") + "&");
//                        }
//                    }
//                    out.writeBytes(request.toString());//写入请求参数
//                    out.flush();
//                    out.close();
//                    if (connection.getResponseCode() == 200) {
//                        InputStream in = connection.getInputStream();
//                        reader = new BufferedReader(new InputStreamReader(in));
//                        String line;
//                        while ((line = reader.readLine()) != null) {
//                            sb.append(line);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (reader != null) {
//                        reader.close();//关闭流
//                    }
//                    if (connection != null) {
//                        connection.disconnect();//断开连接，释放资源
//                    }
//                }
//                return sb.toString();
//            }
//        });
//        new Thread(task).start();
//        String s = null;
//        try {
//            s = task.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return s;
//    }
//
//
//    private int dip2px(Context context, float dpValue) {
//        float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }
//
//    private String encodeType(String type) {
//        if(type.contains("*"))
//        {
//            type = type.replace("*", "%2A");
//        }
//        else if(type.contains("+"))
//        {
//            type = type.replace("+", "%20");
//        }
//        else if(type.contains("%7E"))
//        {
//            type = type.replace("%7E", "~");
//        }
//        else if(type.contains(" "))
//        {
//            type = type.replace(" ", "%20");
//        }
//        return type;
//    }


}
