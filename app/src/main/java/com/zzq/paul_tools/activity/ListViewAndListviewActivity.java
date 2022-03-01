package com.zzq.paul_tools.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.idisfkj.mylibrary.EnhanceRecyclerView;
import com.zzq.paul_tools.R;
import com.zzq.paul_tools.adapter.CommonListViewApapter;
import com.zzq.paul_tools.adapter.CommonListViewHolder;
import com.zzq.paul_tools.adapter.CommonRecyclerAdapter;
import com.zzq.paul_tools.adapter.CommonRecyclerHolder;
import com.zzq.paul_tools.adapter.CommonTypeRecyclerAdapter;
import com.zzq.paul_tools.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewAndListviewActivity extends BaseActivity {

    @BindView(R.id.data_rv)
    RecyclerView rv;


    @Override
    protected int getLayout() {
        return R.layout.activity_list_view_and_listview;
    }

    @Override
    protected void init() {
        initAdapter();
    }

    private void initAdapter() {


        final List<String> lists = new ArrayList<>();
        lists.add("苹果");
        lists.add("苹果");
        lists.add("苹果");
        lists.add("苹果");
        CommonTypeRecyclerAdapter tabAdapter = new CommonTypeRecyclerAdapter<String>(ListViewAndListviewActivity.this,
                lists, R.layout.item_test_layout,R.layout.item_test_layout2) {
            @Override
            public void bindView(CommonRecyclerHolder holder, Object item, int position) {
                Log.e("eee","-------------");

            }
            @Override
            public void bindSecondTypeView(CommonRecyclerHolder holder, String item, int position, int type) {
//                holder.setText(R.id.msg1, item);
//                MyListView recyclerView = holder.getView(R.id.rv);
//                final List<String> lists2 = new ArrayList<>();
//                lists2.add("梨子");
//                lists2.add("梨子");
//                CommonListViewApapter tabAdapter2 = new CommonListViewApapter<String>(ListViewAndListviewActivity.this, lists2, R.layout.item_second_layout) {
//                    @Override
//                    public void bindView(CommonListViewHolder holder, String item, int position) {
//                        holder.setText(R.id.msg, item);
//                        holder.getView(R.id.btn).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Log.e("eee", "----点击我了");
//                            }
//                        });
//                    }
//                };
//                recyclerView.setAdapter(tabAdapter2);
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(ListViewAndListviewActivity.this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(tabAdapter);
    }
}