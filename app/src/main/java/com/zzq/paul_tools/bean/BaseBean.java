package com.zzq.paul_tools.bean;

import java.io.Serializable;

/**
 * @author zhuzaiqing
 * @describe   数据解析返回基类
 * @time 2018/11/1 13:55
 */
public class BaseBean<T>  {
    private T data;
    private String rsCode ;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return rsCode;
    }

    public void setCode(String code) {
        this.rsCode = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
