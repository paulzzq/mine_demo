package com.zzq.design_model.simple_factory_model;

/**
 * @author zhuzaiqing
 * @describe  乘法运算
 * @time 2020/7/2 14:16
 */
public class OperationMul extends Operation {
    @Override
    public Double getResult() throws Exception {
        return getNumber1()*getNumber2();
    }
}
