package com.zzq.paul_tools.bean;

import java.io.Serializable;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2018/11/13 17:55
 */

public class SpiderDataBean implements Serializable {
    private float x;
    private float y;
    private double value;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
