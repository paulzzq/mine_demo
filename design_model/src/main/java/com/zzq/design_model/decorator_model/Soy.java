package com.zzq.design_model.decorator_model;

import java.math.BigDecimal;

/**
 * @author zhuzaiqing
 * @describe  豆浆调料类（继承自CondimentDecorator））
 * @time 2020/7/7 10:51
 */
public class Soy extends CondimentDecorator {
    /**
     * 用一个实例变量记录饮料，也就是被装饰者
     */
    Beverage beverage;

    /**
     * 构造器初始化饮料变量
     *
     * @param beverage
     */
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * 在原来饮料的基础上添加上Soy描述（原来的饮料加入Soy调料，被Soy调料装饰）
     *
     * @return
     */
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy";
    }

    /**
     * 在原来饮料的基础上加上Soy的价格（原来的饮料加入Soy调料，被Soy调料装饰）
     *
     * @return
     */
    @Override
    public BigDecimal cost() {
        return new BigDecimal("0.3").add(beverage.cost());
    }
}
