package com.zzq.paul_tools.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.bean.MainToolBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zhuzaiqing
 * @describe recycleview  adapter
 * @time 2018/11/5 18:05
 */
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.ViewHolder> {
    private List<MainToolBean> list;
    private OnclicListener mListener;

    public NormalAdapter(List<MainToolBean> list, OnclicListener mListener) {
        this.list = list;
        this.mListener = mListener;
    }

    public interface OnclicListener {
        void OnItemOnClick();

        void OnLongOnClick();
    }

    public void addNewItem() {
        if (list == null) {
            list = new ArrayList<>();
        }
        MainToolBean bean1 = new MainToolBean();
        bean1.setName("測試");
        bean1.setBitmap(R.mipmap.ic_launcher);
        list.add(0, bean1);
        ////更新数据集不是用adapter.notifyDataSetChanged()而是notifyItemInserted(position)与notifyItemRemoved(position) 否则没有动画效果。
        notifyItemInserted(0);
    }


    public void deleteItem() {
        if (list == null || list.isEmpty()) {
            return;
        }
        list.remove(2);
        notifyItemRemoved(2);
    }

    public void updateItem() {
        if (list == null || list.isEmpty()) {
            return;
        }
        list.get(0).setName("走起来额");
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_grid_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.photoIv.setImageResource(list.get(position).getBitmap());
        holder.titleTv.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnItemOnClick();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.OnLongOnClick();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_iv)
        ImageView photoIv;
        @BindView(R.id.title_tv)
        TextView titleTv;

        public ViewHolder(View v) {
            super(v);
            photoIv = v.findViewById(R.id.photo_iv);
            titleTv = v.findViewById(R.id.title_tv);
        }

    }
}
