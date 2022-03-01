package com.zzq.paul_tools.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zzq.paul_tools.R;
import com.zzq.paul_tools.adapter.CommonViewPagerAdapter;
import com.zzq.paul_tools.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 滑动到顶部停留
 * @Author: zzq
 * @Date: 2020-11-04 17:41
 */
public class TopTitleActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_top_title;
    }

    @Override
    protected void init() {

//        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Log.e("eee","------加载更多");
                refreshLayout.finishLoadmore();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Log.e("eee","------刷新");
                refreshLayout.finishRefresh();

            }
        });
        fragmentList.add(new TestFragment());
        fragmentList.add(new TestFragment());
        fragmentList.add(new TestFragment());


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        CommonViewPagerAdapter vpAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), fragmentList, false);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(fragmentList.size());
        mViewPager.setAdapter(vpAdapter);


        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("签约订单查询"));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText("签约单完成"));
        mTabLayout.addTab(mTabLayout.newTab().setText("签约单到期"));

        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setText("签约订单查询");
        mTabLayout.getTabAt(1).setText("签约单完成");
        mTabLayout.getTabAt(2).setText("签约单到期");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}