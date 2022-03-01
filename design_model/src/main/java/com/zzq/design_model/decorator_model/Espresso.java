package com.zzq.design_model.decorator_model;

import java.math.BigDecimal;

/**
 * @author zhuzaiqing
 * @describe  浓缩咖啡类（一种具体饮料）
 * @time 2020/7/7 10:50
 */
public class Espresso extends Beverage {
    /**
     * 说明他是Espresso饮料
     */
    public Espresso() {
        description = "Espresso";
    }

    /**
     * 实现cost方法，用来返回Espresso（浓缩咖啡）的价格
     *
     * @return
     */
    @Override
    public BigDecimal cost() {
        return new BigDecimal("2.00");
    }
}
