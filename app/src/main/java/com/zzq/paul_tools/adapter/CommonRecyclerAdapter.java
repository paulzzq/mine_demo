package com.zzq.paul_tools.adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzq.paul_tools.R;

import java.util.List;

/**
 * Description:  RecyclerView通用Adapter
 * Author:       张丹枫
 * CreateDate:   2019/3/15 10:37
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_NO_CONTENT = -1;//无内容的TYPE
    public static final int TYPE_HEADER = 0;  //Header的TYPE
    public static final int TYPE_FOOTER = 1;  //Footer的TYPE
    public static final int TYPE_CONTENT = 2;  //内容的TYPE

    protected Context context;//上下文
    protected List<T> mData;//数据源
    private int itemLayoutId;//布局id

    private RecyclerView recyclerView;
    private View noContentView;//缺省view
    private View mHeaderView;
    private View mFooterView;
    //缺省图
    private int noContentResid = 0;

    private OnItemClickListener listener;//点击事件监听器
    private OnItemLongClickListener longClickListener;//长按监听器

    /**
     * @param context      上下文
     * @param data         数据集
     * @param itemLayoutId item布局
     */
    public CommonRecyclerAdapter(Context context, List<T> data, int itemLayoutId) {
        this.context = context;
        this.mData = data;
        this.itemLayoutId = itemLayoutId;
    }

    /**
     * @param context        上下文
     * @param data           数据集
     * @param itemLayoutId   item布局
     * @param noContentResid 无数据缺省图片
     */
    public CommonRecyclerAdapter(Context context, List<T> data, int itemLayoutId, @DrawableRes int noContentResid) {
        this.context = context;
        this.mData = data;
        this.itemLayoutId = itemLayoutId;
        this.noContentResid = noContentResid;
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 定义长按事件接口回调
     */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
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

    protected T getItem(int position){
        return mData.get(position);
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
     * 插入一项
     */
    public void insert(T item, int position) {
        mData.add(position, item);
        notifyItemInserted(position);
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
            return new NoContentHolder(noContentView);
        }

        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return CommonRecyclerHolder.getRecyclerHolder(context, mHeaderView);
        }

        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return CommonRecyclerHolder.getRecyclerHolder(context, mFooterView);
        }

        View view = LayoutInflater.from(context).inflate(itemLayoutId, parent, false);
        CommonRecyclerHolder commonRecyclerHolder = CommonRecyclerHolder.getRecyclerHolder(context, view);

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
            ((NoContentHolder) holder).imageView.setImageResource(noContentResid);
            return;
        }

        if (getItemViewType(position) == TYPE_CONTENT) {

            //添加Header后position从-1开始
            if (mHeaderView != null) {
                position = position - 1;
            }

            bindView((CommonRecyclerHolder) holder, mData.get(position), position);
        }
    }
    protected int getDefItemViewType(int position) {
//        if (mMultiTypeDelegate != null) {
//            return mMultiTypeDelegate.getDefItemViewType(mData, position);
//        }
        return super.getItemViewType(position);
    }
    @Override
    public int getItemViewType(int position) {
        if (noContentResid != 0 && mData.size() == 0) {
            return TYPE_NO_CONTENT;
        }
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_CONTENT;
        }
        if (position == 0 && mHeaderView != null) {
            //mHeaderView不为空时第一个是HeaderView
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1 && mFooterView != null) {
            ////mFooterView不为空时最后一个是HeaderView
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
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
    public abstract void bindView(CommonRecyclerHolder holder, T item, int position);

}
