package com.zzq.paul_tools.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fengjuan on 2017/11/14.
 */

public class BaseRsCode {

    @SerializedName("rsCode")
    @Expose
    public int rsCode;
    @SerializedName("Msg")
    @Expose
    public String msg;

    @SerializedName("reason")
    @Expose
    public String reason;

    public int getRsCode() {
        return rsCode;
    }

    public void setRsCode(int rsCode) {
        this.rsCode = rsCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
