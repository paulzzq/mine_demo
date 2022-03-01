package com.zzq.paul_tools.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2019/4/28 19:05
 */
public class StickHeaderScrollViewAdapter extends RecyclerView.Adapter<StickHeaderScrollViewAdapter.TextViewHolder> {
    private String TAG = StickHeaderScrollViewAdapter.class.getSimpleName();
    ArrayList<String> data;
    public StickHeaderScrollViewAdapter(){

    }

    public StickHeaderScrollViewAdapter(ArrayList<String> data){
        this.data = data;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        TextView tv = (TextView) holder.itemView;
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));

        String val = data == null? "TextView " + position : data.get(position);
        tv.setText(val);
        Log.d(TAG,"onBindViewHolder " + position);
    }


    @Override
    public int getItemCount() {
        return data == null ?100: data.size();
    }




    public static class TextViewHolder extends RecyclerView.ViewHolder{

        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }
}
