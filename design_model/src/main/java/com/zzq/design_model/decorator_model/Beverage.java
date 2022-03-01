package com.zzq.design_model.decorator_model;

import java.math.BigDecimal;

/**
 * @author zhuzaiqing
 * @describe  饮料抽象类
 * @time 2020/7/7 10:48
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    /**
     * cost方法是用来返回饮料的价钱（需在具体类中自己实现）
     *
     * @return
     */
    public abstract BigDecimal cost();
}
