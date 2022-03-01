package com.zzq.design_model.decorator_model;

/**
 * @author zhuzaiqing
 * @describe 调料装饰着抽象类
 * @time 2020/7/7 10:50
 */
public abstract class CondimentDecorator extends Beverage {
    /**
     * 所有的调料装饰者都必须重新实现getDescription()方法
     * 这样才能够用递归的方式来得到所选饮料的整体描述
     *
     * @return
     */
    public abstract String getDescription();
}
