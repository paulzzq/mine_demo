package com.zzq.paul_tools.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zzq.paul_tools.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhuzaiqing
 * @describe tablelayout的使用
 * @time 2018/11/30 9:41
 */
public class TableLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    List<String> tableList = new ArrayList<>();
    @BindView(R.id.select_tv)
    TextView selectTv;

    private String select = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        ButterKnife.bind(this);
        initData();
        setTabs(tabLayout, getLayoutInflater(), tableList);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                if (tableList.size() == 5) {
                select = selectTv.getText().toString();//原来的
                    Log.e("eee","--1选中："+tab.getPosition());
                    selectTv.setText(tableList.get(tab.getPosition()));
                    tableList.remove(tab.getPosition());
                    tabLayout.removeAllViews();
                    tableList.add(select);
                    setTabs(tabLayout, getLayoutInflater(), tableList);
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("eee","--onTabUnselected选中："+tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("eee","--onTabReselected选中："+tab.getPosition());
//                selectTv.setText(tableList.get(tab.getPosition()));
//                tableList.remove(tab.getPosition());
//                  tabLayout.removeTabAt(tab.getPosition());
//                tabLayout.removeAllTabs();
//                tabLayout.removeView(tabLayout.getChildAt(tab.getPosition()));
//                setTabs(tabLayout, getLayoutInflater(), tableList);
            }
        });

    }

    private void initData() {
        tableList.add("关注");
        tableList.add("推荐");
        tableList.add("视频");
        tableList.add("深圳");
        tableList.add("热点");
//        tableList.add("精品课");
//        tableList.add("图片");
//        tableList.add("娱乐");
//        tableList.add("懂车帝");
//        tableList.add("健康");
//        tableList.add("娱乐");
//        tableList.add("体育");
//        tableList.add("特卖");
//        tableList.add("旅游");
//        tableList.add("美食");
    }


    /**
     * 设置也卡显示效果
     *
     * @param tabLayout
     * @param inflater
     * @param list
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu, null);
            // 使用自定义视图，目的是为了便于修改，也可使用自带的视图
            tab.setCustomView(view);
            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(list.get(i));
            tabLayout.addTab(tab);
        }
    }

    private void addTabs(TabLayout tabLayout, LayoutInflater inflater, String title, int position) {
        TabLayout.Tab tab = tabLayout.newTab();
        View view = inflater.inflate(R.layout.item_main_menu, null);
        // 使用自定义视图，目的是为了便于修改，也可使用自带的视图
        tab.setCustomView(view);
        TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
        tvTitle.setText(title);
        tabLayout.addTab(tab, position);
    }
}
