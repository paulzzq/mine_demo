package com.zzq.design_model.decorator_model;

import java.math.BigDecimal;

/**
 * @author zhuzaiqing
 * @describe 低咖啡因咖啡类（一种具体的饮料）
 * @time 2020/7/7 10:49
 */
public class Decaf  extends Beverage{
    /**
     * 说明他是Decaf饮料
     */
    public Decaf() {
        description = "Decaf";
    }

    /**
     * 实现cost方法，用来返回Decaf（低咖啡因咖啡）的价格
     *
     * @return
     */
    @Override
    public BigDecimal cost() {
        return new BigDecimal("4.00");
    }
}
