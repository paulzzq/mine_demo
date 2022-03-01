package com.zzq.paul_tools.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzq.paul_tools.utils.GlideUtil;


/**
 * Description:  RecyclerView通用ViewHolder
 * Author:       张丹枫
 * CreateDate:   2019/3/15 10:38
 */
public class CommonRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private SparseArray<View> views;

    private OnItemClickListener onItemClickListener;
    private OnLongClickListener onLongClickListener;

    private CommonRecyclerHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.views = new SparseArray<>();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    /**
     * 取得一个RecyclerHolder对象
     *
     * @param context  上下文
     * @param itemView 子项
     * @return 返回一个RecyclerHolder对象
     */
    public static CommonRecyclerHolder getRecyclerHolder(Context context, View itemView) {
        return new CommonRecyclerHolder(context, itemView);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }

    public interface OnLongClickListener {
        void onLongClick(View view, int postion);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(v, getAdapterPosition());
            return true;
        }
        return false;
    }

    public SparseArray<View> getViews() {
        return this.views;
    }

    public View getItemView(){
        return itemView;
    }

    /**
     * 通过view的id获取对应的控件，如果没有则加入views中
     *
     * @param viewId 控件的id
     * @return 返回一个控件
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置字符串
     */
    public CommonRecyclerHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置图片
     */
    public CommonRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(drawableId);
        return this;
    }

    /**
     * 设置图片
     */
    public CommonRecyclerHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置网络图片
     */
    public CommonRecyclerHolder setImageByUrl(int viewId, String url) {
        //实现加载网络图片
        GlideUtil.loadImage(context, url, (ImageView) getView(viewId));
        return this;
    }
}
