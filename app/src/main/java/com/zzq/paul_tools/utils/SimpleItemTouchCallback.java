package com.zzq.paul_tools.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zzq.paul_tools.adapter.NormalAdapter;
import com.zzq.paul_tools.bean.MainToolBean;

import java.util.Collections;
import java.util.List;

/**
 * @author zhuzaiqing
 * @describe   侧滑删除
 * @time 2018/11/6 14:44
 */
public class SimpleItemTouchCallback extends ItemTouchHelper.Callback {
    private NormalAdapter mAdapter;
    private List<MainToolBean> mData;

    public SimpleItemTouchCallback(NormalAdapter adapter, List<MainToolBean> data) {
        mAdapter = adapter;
        mData = data;

    }


    //设置支持的拖拽、滑动的方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //s上下拖拽
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
        //左->右和右->左滑动
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        Collections.swap(mData, from, to);
        mAdapter.notifyItemMoved(from, to);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        mData.remove(pos);
        mAdapter.notifyItemRemoved(pos);
    }

    //状态改变时回调
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            NormalAdapter.ViewHolder holder = (NormalAdapter.ViewHolder) viewHolder;
            holder.itemView.setBackgroundColor(0xffbcbcbc);
            //设置拖拽和侧滑时的背景色
        }
    }

    //拖拽或滑动完成之后调用，用来清除一些状态
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        NormalAdapter.ViewHolder holder = (NormalAdapter.ViewHolder) viewHolder;
        holder.itemView.setBackgroundColor(0xffeeeeee);
        //背景色还原
    }


}
