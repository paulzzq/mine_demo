package com.zzq.design_model.simple_factory_model;

/**
 * @author zhuzaiqing
 * @describe 加法运算
 * @time 2020/7/2 14:15
 */
public class OperationAdd extends Operation {
    @Override
    public Double getResult() throws Exception {
        return getNumber1()+getNumber2();
    }
}
