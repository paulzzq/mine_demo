package com.zzq.paul_tools.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.zzq.paul_tools.adapter.NormalAdapterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuzaiqing
 * @describe 自定义带头部和尾部的recycleView
 * @time 2018/11/20 17:42
 */
public class CustomerRecycleView extends RecyclerView {
    NormalAdapterWrapper recycleAdapter;
    Adapter mAdapter;
    private List<View> mHeaderViewList = new ArrayList<>();
    private List<View> mFooterViewList = new ArrayList<>();

    public CustomerRecycleView(Context context) {
        super(context);
    }

    public CustomerRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mAdapter != null) {
            mAdapter = null;
        }
        this.mAdapter = adapter;
        recycleAdapter = new NormalAdapterWrapper(adapter);

        if (mHeaderViewList.size() > 0) {
            for (View view : mHeaderViewList) {
                recycleAdapter.addHeaderView(view);
            }
        }
        if (mFooterViewList.size() > 0) {
            for (View view : mFooterViewList) {
                recycleAdapter.addFooterView(view);
            }
        }
        super.setAdapter(recycleAdapter);
    }


    public void addHeaderView(View view) {
        mHeaderViewList.add(view);
    }


    public void addFooterView(View view) {
        mFooterViewList.add(view);
    }

}
