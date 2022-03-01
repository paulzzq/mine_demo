package com.zzq.design_model.decorator_model;

import java.math.BigDecimal;

/**
 * @author zhuzaiqing
 * @describe 摩卡调料类（继承自CondimentDecorator）
 * @time 2020/7/7 10:51
 */
public class Mocha extends CondimentDecorator {
    /**
     * 用一个实例变量记录饮料，也就是被装饰者
     */
    Beverage beverage;

    /**
     * 构造器初始化饮料变量
     *
     * @param beverage
     */
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * 在原来饮料的基础上添加上Mocha描述（原来的饮料加入Mocha调料，被Mocha调料装饰）
     *
     * @return
     */
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    /**
     * 在原来饮料的基础上加上Mocha的价格（原来的饮料加入Mocha调料，被Mocha调料装饰）
     *
     * @return
     */
    @Override
    public BigDecimal cost() {
        return new BigDecimal("0.2").add(beverage.cost());
    }
}
