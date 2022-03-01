package com.zzq.paul_tools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.idisfkj.mylibrary.EnhanceRecyclerView;
import com.zzq.paul_tools.R;
import com.zzq.paul_tools.adapter.CommonRecyclerAdapter;
import com.zzq.paul_tools.adapter.CommonRecyclerHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestFragment extends Fragment {

    EnhanceRecyclerView rv;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        rv = view.findViewById(R.id.rv);
        initAdapter();
        return view;
    }

    private void initAdapter() {
        List<String> lists = new ArrayList<>();
        lists.add("苹果");
        lists.add("苹果");
        lists.add("苹果");
        lists.add("苹果");
        CommonRecyclerAdapter tabAdapter = new CommonRecyclerAdapter<String>(getContext(), lists, R.layout.item_test_layout) {
            @Override
            public void bindView(CommonRecyclerHolder holder, String item, int position) {
                holder.setText(R.id.title_tv, item);
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(tabAdapter);


        rv.setPullToRefreshListener(new com.idisfkj.mylibrary.EnhanceRecyclerView.PullToRefreshListener() {
            @Override
            public void onRefreshing() {
                Log.e("eee", "-----onRefreshing");
            }
        });
        rv.setLoadMoreListener(new EnhanceRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("eee", "-----onLoadMore");

            }
        });
    }


}