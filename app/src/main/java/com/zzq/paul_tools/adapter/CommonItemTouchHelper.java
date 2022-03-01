package com.zzq.paul_tools.adapter;

import android.app.Service;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.Collections;
import java.util.List;

/**
 * Description:  item拖拽
 * Author:       张丹枫
 * CreateDate:   2019/3/18 11:07
 * <p>
 * zhukai 做了修改
 */
public class CommonItemTouchHelper extends ItemTouchHelper {


    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public CommonItemTouchHelper(Callback callback) {
        super(callback);
    }

    public abstract static class CallBack<T> extends ItemTouchHelper.Callback {

        private boolean canDrag = true;
        private boolean canSwipe = true;

        private int startPosition;
        private int endPosition;

        private int pressedColor = 0;
        private int normalColor = 0;

        private boolean shakeEnabled;

        private List<T> mList;

        public CallBack(List<T> list) {
            this.mList = list;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int dragFlags = 0;
            int swipeFlags = 0;
            if (layoutManager instanceof GridLayoutManager) {
                // 如果是Grid布局，则不能滑动，只能上下左右拖动
                dragFlags =
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipeFlags = 0;
            } else if (layoutManager instanceof LinearLayoutManager) {
                // 如果是纵向Linear布局，则能上下拖动，左右滑动
                if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else {
                    // 如果是横向Linear布局，则能左右拖动，上下滑动
                    swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
            }
            return makeMovementFlags(dragFlags, swipeFlags); //该方法指定可进行的操作
        }

        /**
         * 拖动时回调，在这里处理拖动事件
         *
         * @param viewHolder 被拖动的view
         * @param target     目标位置的view
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            if (!isItemMoveEnabled(recyclerView, viewHolder, target)
                    || null == mList) {
                return false;
            }
            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();
            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            endPosition = toPosition;

            Collections.swap(mList, fromPosition, toPosition);
            itemMoving(fromPosition, toPosition);

            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

            return true;
        }

        /**
         * 滑动时回调
         *
         * @param direction 回调方向
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        /**
         * 长按选中Item的时候开始调用
         *
         * @param viewHolder
         * @param actionState
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                View item = viewHolder.itemView;
                if (null == item) {
                    return;
                }
                item.setBackgroundColor(this.pressedColor);
                if (shakeEnabled) {
                    //获取系统震动服务
                    Vibrator vib = (Vibrator) item.getContext().getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                    vib.vibrate(70);
                }
                startPosition = viewHolder.getAdapterPosition();
            }
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                itemMoveBegin(startPosition);
            } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {
                itemMoveOver(endPosition);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        /**
         * 手指松开的时候还原
         *
         * @param recyclerView
         * @param viewHolder
         */
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            View item = viewHolder.itemView;
            if (null == item) {
                return;
            }
            item.setBackgroundColor(this.normalColor);
        }

        /**
         * 在这个回调中，如果返回true，表示可以触发长按拖动事件，false则表示不能
         */
        @Override
        public boolean isLongPressDragEnabled() {
            return canDrag;
        }

        /**
         * 在这个回调中，如果返回true，表示可以触发滑动事件，false表示不能
         */
        @Override
        public boolean isItemViewSwipeEnabled() {
            return canSwipe;
        }

        /**
         * 当前Item是否可以移动
         */
        public abstract boolean isItemMoveEnabled(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                                  RecyclerView.ViewHolder target);

        /**
         * 当前Item拖拽开始
         */
        public void itemMoveBegin(int startPosition) {

        }

        /**
         * 当前Item正在拖拽
         */
        public void itemMoving(int fromPosition, int toPosition) {

        }

        /**
         * 当前Item拖拽结束
         */
        public void itemMoveOver(int endPosition) {

        }


        public void setCanDragEnabled(boolean canDrag) {
            this.canDrag = canDrag;
        }

        public void setCanSwipeEnabled(boolean canSwipe) {
            this.canSwipe = canSwipe;
        }

        public void setCanNormalColor(int color) {
            this.normalColor = color;
        }

        public void setCanPressedColor(int color) {
            this.pressedColor = color;
        }

        public void setCanShakeEnabled(boolean enabled) {
            this.shakeEnabled = enabled;
        }
    }


}
