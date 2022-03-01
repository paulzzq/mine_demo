package com.zzq.paul_tools.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuzaiqing
 * @describe 万能adapter
 * @time 2018/11/6 9:18
 */
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.ViewHolder> {
    private List<T> mDatas;

    public QuickAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    public abstract int getLayoutId(int viewType);

    public abstract int getItemViewType(int position);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void addNewItem() {
//        if(mDatas == null) {
//            mDatas = new ArrayList<>();
//    }
//
//    mDatas.add(0, );
//    notifyItemInserted(0);
    }

    public void deleteItem() {
        if (mDatas == null || mDatas.isEmpty()) {
            return;
        }
        mDatas.remove(0);
        notifyItemRemoved(0);
    }


    public abstract void convert(ViewHolder holder, T data, int position);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private View mConvertView;

        private ViewHolder(View v) {
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static ViewHolder get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new ViewHolder(convertView);
        }


        public <T extends View> T getView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T) v;
        }

        public void setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
        }

        public void setDrawble(int id, int value) {
            ImageView view = getView(id);
            view.setImageResource(value);
        }
    }
}

