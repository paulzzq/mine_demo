package com.zzq.design_model.decorator_model;

import java.math.BigDecimal;

/**
 * @author zhuzaiqing
 * @describe 深焙咖啡类（一种具体的饮料）
 * @time 2020/7/7 10:49
 */
public class DarkRoast extends Beverage {

    /**
     * 说明他是DarkRoast饮料
     */
    public DarkRoast() {
        description = "DarkRoast";
    }

    /**
     * 实现cost方法，用来返回DarkRoast（深焙咖啡）的价格
     *
     * @return
     */
    @Override
    public BigDecimal cost() {
        return new BigDecimal("3.00");
    }
}
