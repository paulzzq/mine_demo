package com.zzq.design_model.simple_factory_model;

/**
 * @author zhuzaiqing
 * @describe    具体产品角色：   减法运算
 * @time 2020/7/2 14:14
 */
public class OperationSub extends Operation {
    @Override
    public Double getResult() throws Exception {
        return getNumber1()-getNumber2();
    }
}
