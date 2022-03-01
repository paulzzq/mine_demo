package com.zzq.paul_tools.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhuzaiqing
 * @describe 添加头部和尾部
 * @time 2018/11/6 14:16
 */
public class NormalAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    enum ITEM_TYPE {HEADER, FOOTER, NORMAL}

    private RecyclerView.Adapter mAdapter;
    private View mHeaderView;
    private View mFooterView;

    public NormalAdapterWrapper(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.HEADER.ordinal();
        } else if (position == mAdapter.getItemCount() + 1) {
            return ITEM_TYPE.FOOTER.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position == 0) {
            return;
        } else if (position == mAdapter.getItemCount() + 1) {
            return;
        } else {
            mAdapter.onBindViewHolder(((NormalAdapter.ViewHolder) holder), position - 1);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("eee","---------position:"+position);
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER.ordinal()) {
            return new RecyclerView.ViewHolder(mHeaderView) {
            };
        } else if (viewType == ITEM_TYPE.FOOTER.ordinal()) {
            return new RecyclerView.ViewHolder(mFooterView) {
            };
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    public void addHeaderView(View view) {
        this.mHeaderView = view;
    }

    public void addFooterView(View view) {
        this.mFooterView = view;
    }


}
