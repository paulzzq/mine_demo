package com.zzq.paul_tools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.zzq.paul_tools.R;

import java.util.List;

/**
 * @Description: 多布局类型
 * @Author: zzq
 * @Date: 2021-04-14 17:21
 */
public abstract class CommonTypeRecyclerAdapter<T> extends CommonRecyclerAdapter {
    public static final int TYPE_NO_CONTENT = -1;//无内容的TYPE
    public static final int TYPE_HEADER = 0;  //Header的TYPE
    public static final int TYPE_FOOTER = 1;  //Footer的TYPE
    public static final int TYPE_SECOND_CONTENT = 3;  //内容的TYPE

    private int itemLayoutId1;//布局1id
    private int itemLayoutId2;//布局1id

    private RecyclerView recyclerView;
    private View noContentView;//缺省view
    private View mHeaderView;
    private View mFooterView;
    //缺省图
    private int noContentResid = 0;

    private CommonRecyclerAdapter.OnItemClickListener listener;//点击事件监听器
    private CommonRecyclerAdapter.OnItemLongClickListener longClickListener;//长按监听器

    /**
     * @param context      上下文
     * @param data         数据集
     * @param itemLayoutId item布局
     */
    public CommonTypeRecyclerAdapter(Context context, List<T> data, int itemLayoutId, int itemLayoutId2) {
        super(context,data,itemLayoutId);
        this.itemLayoutId1 = itemLayoutId2;
    }



    //在RecyclerView提供数据的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    /**
     * 定义点击事件接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public void setOnItemClickListener(CommonRecyclerAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 定义长按事件接口回调
     */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    public void setOnItemLongClickListener(CommonRecyclerAdapter.OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    /**
     * 添加HeaderView
     */
    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    /**
     * 移除HeaderView
     */
    public void removeHeaderView() {
        if (mHeaderView != null) {
            mHeaderView = null;
            notifyItemRemoved(0);
        }
    }

    /**
     * 添加FooterView
     */
    public void addFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * 移除FooterView
     */
    public void removeFooterView() {
        if (mFooterView != null) {
            mFooterView = null;
            notifyItemRemoved(getItemCount() - 1);
        }
    }

    /**
     * 获取HeaderView
     */
    public View getHeaderView() {
        return mHeaderView;
    }

    /**
     * 获取FooterView
     */
    public View getFooterView() {
        return mFooterView;
    }



    /**
     * 删除一项
     */
    public void delete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (noContentResid != 0 && viewType == TYPE_NO_CONTENT) {
            noContentView = LayoutInflater.from(context).inflate(R.layout.view_no_content_list, parent, false);
            return new CommonTypeRecyclerAdapter.NoContentHolder(noContentView);
        }

        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return CommonRecyclerHolder.getRecyclerHolder(context, mHeaderView);
        }

        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return CommonRecyclerHolder.getRecyclerHolder(context, mFooterView);
        }

        View view1 = LayoutInflater.from(context).inflate(itemLayoutId1, parent, false);
        CommonRecyclerHolder commonRecyclerHolder = CommonRecyclerHolder.getRecyclerHolder(context, view1);

        commonRecyclerHolder.setOnItemClickListener(new CommonRecyclerHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if (listener != null && view != null && recyclerView != null) {
                    listener.onItemClick(recyclerView, view, postion);
                }
            }
        });

        commonRecyclerHolder.setOnLongClickListener(new CommonRecyclerHolder.OnLongClickListener() {
            @Override
            public void onLongClick(View view, int postion) {
                if (longClickListener != null && view != null && recyclerView != null) {
                    longClickListener.onItemLongClick(recyclerView, view, postion);
                }
            }
        });
        return commonRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_NO_CONTENT) {
            ((CommonRecyclerAdapter.NoContentHolder) holder).imageView.setImageResource(noContentResid);
            return;
        }

        if (getItemViewType(position) == TYPE_SECOND_CONTENT) {
            //添加Header后position从-1开始
            if (mHeaderView != null) {
                position = position - 1;
            }
            bindSecondTypeView((CommonRecyclerHolder) holder, (T) getItem(position), position,getItemViewType(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (noContentResid != 0 && mData.size() == 0) {
            return TYPE_NO_CONTENT;
        }

        if (mHeaderView == null && mFooterView == null) {
            return TYPE_SECOND_CONTENT;
        }

        if (position == 0 && mHeaderView != null) {
            //mHeaderView不为空时第一个是HeaderView
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1 && mFooterView != null) {
            ////mFooterView不为空时最后一个是HeaderView
            return TYPE_FOOTER;
        }
        return TYPE_SECOND_CONTENT;
    }

    @Override
    public int getItemCount() {
        if (mData.size() == 0 && noContentResid != 0) {
            return 1;
        }
        if (mHeaderView == null && mFooterView == null) {
            return mData.size();
        }
        if (mHeaderView == null && mFooterView != null) {
            return mData.size() + 1;
        }
        if (mHeaderView != null && mFooterView == null) {
            return mData.size() + 1;
        }
        return mData.size() + 2;
    }

    class NoContentHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public NoContentHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.content_iv);
        }
    }

    /**
     * 填充RecyclerView适配器的方法，子类需要重写
     *
     * @param holder   ViewHolder
     * @param item     子项
     * @param position 位置
     */
    public abstract void bindSecondTypeView(CommonRecyclerHolder holder, T item, int position,int type);


}
