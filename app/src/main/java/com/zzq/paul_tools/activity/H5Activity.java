package com.zzq.paul_tools.activity;

import android.content.Context;
import android.content.Intent;


/**
 * Description:  通用H5界面
 * Author:       张丹枫
 * CreateDate:   2019/4/8 16:05
 */
public class H5Activity extends BaseWebViewActivity {


    /**
     * @param title 标题文字
     * @param url   链接url
     */
    public static void launchActivity(Context activity, String title, String url) {
        Intent intent = new Intent(activity, H5Activity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    protected void initData() {
        setTitle(getIntent().getStringExtra("title"));
//        setWebView("https://api.xzytest.cn/v1/knowledge/091055794daa4ed5b1403b28af0ff107");
        setWebView("http://wx.xzytest.cn/hefei/siteList");
//        setWebView("https://fw.614.link/hefei/siteList");
//        setWebView("https://wx.xzytest.cn/activity/test5.html");
//        setWebView("http://i.waimai.meituan.com/city");
//        setWebView("https://app.ah12301.com/page/page1.html");

    }

    @Override
    protected void initJs() {
//        getAgentWeb().getJsInterfaceHolder().addJavaObject("teacherapp_android", new BaseInterfaceWeb(this));
    }

//    @Override
//    protected BasePresenter initPresenter() {
//        return null;
//    }

}
