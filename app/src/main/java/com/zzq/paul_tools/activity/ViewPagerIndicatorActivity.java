package com.zzq.paul_tools.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.fragment.TestFragment;
import com.zzq.paul_tools.view.SimpleViewpagerIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerIndicatorActivity extends AppCompatActivity {

    String[] pageTitles = {"刺激战场国服版", "绝地求生吃鸡了","东鹏特饮喝起来", "柚子", "橘子","栗子","橙子","桃子"};
    @BindView(R.id.indicator)
    SimpleViewpagerIndicator indicator;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_indicator);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager()));
        indicator = findViewById(R.id.indicator);
        indicator.setExpand(false)//设置tab宽度为包裹内容还是平分父控件剩余空间，默认值：false,包裹内容
                .setIndicatorWrapText(true)//设置indicator是与文字等宽还是与整个tab等宽，默认值：true,与文字等宽
                .setIndicatorColor(Color.parseColor("#ff3300"))//indicator颜色
                .setIndicatorHeight(4)//indicator高度
        //        .setShowUnderline(true, Color.parseColor("#dddddd"), 2)//设置是否展示underline，默认不展示
        //        .setShowDivider(true, Color.parseColor("#dddddd"), 10, 1)//设置是否展示分隔线，默认不展示
        //        .setTabTextSize(16)//文字大小
                .setTabTextColor(Color.parseColor("#ff999999"))//文字颜色
        //        .setTabTypeface(null)//字体
        //        .setTabTypefaceStyle(Typeface.NORMAL)//字体样式：粗体、斜体等
        //        .setTabBackgroundResId(0)//设置tab的背景
//                .setTabPadding(40)//设置tab的左右padding
        //        .setSelectedTabTextSize(20)//被选中的文字大小
                .setSelectedTabTextColor(Color.parseColor("#ff3300"));//被选中的文字颜色
        //        .setSelectedTabTypeface(null)
        //        .setSelectedTabTypefaceStyle(Typeface.BOLD)
        //        .setScrollOffset(120);//滚动偏移量
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class VpAdapter extends FragmentPagerAdapter {
        VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return new TestFragment();
        }
    }
}
