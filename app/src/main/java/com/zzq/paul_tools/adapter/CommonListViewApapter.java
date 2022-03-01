package com.zzq.paul_tools.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Description:  通用的ListView适配器
 * Author:       张丹枫
 * CreateDate:   2019/3/27 15:38
 */
public abstract class CommonListViewApapter<T> extends BaseAdapter {

    private Context context;
    private List<T> datas;//数据源
    private int itemLayoutId;//布局id

    public CommonListViewApapter(Context context, List<T> datas, int itemLayoutId) {
        this.context = context;
        this.datas = datas;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonListViewHolder holder = CommonListViewHolder.bind(position, convertView, parent, itemLayoutId, parent.getContext());
        bindView(holder, datas.get(position), position);
        return holder.getItemView();
    }

    /**
     * 填充ListView适配器的方法，子类需要重写
     *
     * @param holder   ViewHolder
     * @param item     子项
     * @param position 位置
     */
    public abstract void bindView(CommonListViewHolder holder, T item, int position);
}
