package com.zzq.design_model.simple_factory_model;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/7/2 14:18
 */
public class OperationDiv extends Operation {
    @Override
    public Double getResult() throws Exception {
        if(getNumber2()==0){
            throw new Exception("除数不能为'0'!");
        }
        return getNumber1()/getNumber2();
    }
}
