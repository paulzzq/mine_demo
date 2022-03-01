package com.zzq.paul_tools.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzq.paul_tools.utils.GlideUtil;

/**
 * Description:  通用的ListView适配器Holder
 * Author:       张丹枫
 * CreateDate:   2019/3/27 15:38
 */
public class CommonListViewHolder {

    private SparseArray<View> mViews = new SparseArray<>();
    private Context mContext;
    private int position;
    private int layRes;
    private View itemView;

    private CommonListViewHolder(Context context, ViewGroup parent, int layRes) {
        this.mContext = context;
        this.layRes = layRes;
        this.itemView = LayoutInflater.from(context).inflate(layRes, parent, false);
        this.itemView.setTag(this);
    }

    public static CommonListViewHolder bind(int position, View convertView, ViewGroup parent, int layRes, Context context) {
        CommonListViewHolder holder;
        if (convertView == null) {
            holder = new CommonListViewHolder(context, parent, layRes);
        } else {
            holder = (CommonListViewHolder) convertView.getTag();
            holder.itemView = convertView;
        }
        holder.position = position;

        return holder;
    }

    /**
     * 获取itemView
     */
    public View getItemView() {
        return itemView;
    }

    /**
     * 通过view的id获取对应的控件，如果没有则加入views中
     *
     * @param viewId 控件的id
     * @return 返回一个控件
     */
    public <T extends View> T getView(int viewId) {
        T t = (T) mViews.get(viewId);
        if (t == null) {
            t = itemView.findViewById(viewId);
            mViews.put(viewId, t);
        }
        return t;
    }

    /**
     * 设置字符串
     */
    public CommonListViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置图片
     */
    public CommonListViewHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(drawableId);
        return this;
    }

    /**
     * 设置图片
     */
    public CommonListViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置网络图片
     */
    public CommonListViewHolder setImageByUrl(int viewId, String url) {
        //实现加载网络图片
        GlideUtil.loadImage(mContext, url, (ImageView) getView(viewId));
        return this;
    }
}
