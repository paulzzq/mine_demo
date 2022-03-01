package com.zzq.paul_tools.bean;

import java.io.Serializable;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2018/10/31 15:32
 */
public class MainToolBean implements Serializable {
    private String name;
    private int bitmap;
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBitmap() {
        return bitmap;
    }

    public void setBitmap(int bitmap) {
        this.bitmap = bitmap;
    }
}
