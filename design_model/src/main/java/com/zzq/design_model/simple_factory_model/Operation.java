package com.zzq.design_model.simple_factory_model;

/**
 * @author zhuzaiqing
 * @describe  抽象产品类   运算
 * @time 2020/7/2 14:13
 */
public abstract class Operation  {
    private double number1;
    private double number2;

    public double getNumber1() {
        return number1;
    }
    public void setNumber1(double number1) {
        this.number1 = number1;
    }
    public double getNumber2() {
        return number2;
    }
    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public  abstract Double getResult() throws Exception;
}
