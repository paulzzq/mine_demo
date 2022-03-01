package com.zzq.paul_tools.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fengjuan on 2017/9/29.
 */

public class BaseListBean<T> extends BaseRsCode{


    @SerializedName("list")
    @Expose
    private List<T> list;

    public BaseListBean() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
