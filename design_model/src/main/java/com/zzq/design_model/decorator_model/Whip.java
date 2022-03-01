package com.zzq.design_model.decorator_model;

import java.math.BigDecimal;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/7/7 10:52
 */
public class Whip extends CondimentDecorator{
    /**
     * 用一个实例变量记录饮料，也就是被装饰者
     */
    Beverage beverage;

    /**
     * 构造器初始化饮料变量
     *
     * @param beverage
     */
    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * 在原来饮料的基础上添加上Whip描述（原来的饮料加入Whip调料，被Whip调料装饰）
     *
     * @return
     */
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Whip";
    }

    /**
     * 在原来饮料的基础上加上Whip的价格（原来的饮料加入Whip调料，被Whip调料装饰）
     *
     * @return
     */
    @Override
    public BigDecimal cost() {
        return new BigDecimal("0.4").add(beverage.cost());
    }
}
