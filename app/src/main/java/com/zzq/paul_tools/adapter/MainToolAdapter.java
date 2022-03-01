package com.zzq.paul_tools.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.bean.MainToolBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhuzaiqing
 * @describe 主界面adapter
 * @time 2018/10/31 15:26
 */
public class MainToolAdapter extends BaseAdapter {
    private Context context;
    private List<MainToolBean> list;

    public MainToolAdapter(Context context, List<MainToolBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MainToolBean bean = list.get(i);
        ViewHolder viewHolder;
        if (view == null) {
            view = LinearLayout.inflate(context, R.layout.adapter_item_main_tool, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.photoIv.setImageResource(bean.getBitmap());
        viewHolder.titleTv.setText(bean.getName());
        viewHolder.describeTv.setText(bean.getDescribe());
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.photo_iv)
        ImageView photoIv;
        @BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.describe_tv)
        TextView describeTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
