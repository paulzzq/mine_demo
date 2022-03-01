package com.zzq.design_model.simple_factory_model;

import com.zzq.design_model.observer_model.Subject;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/7/2 14:22
 */
public class OperationFactory {
    public Operation oper = null;


    public static OperationFactory factory;

    public static OperationFactory getInstance() {
        if (null == factory) {
            synchronized (OperationFactory.class) {
                factory = new OperationFactory();
            }
        }
        return factory;
    }

    public Operation createOperation(String operator) {
        //根据传入的运算符生产出相对应的运算对象
        switch (operator) {
            case "+":
                oper = new OperationAdd();
                break;
            case "-":
                oper = new OperationSub();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            default:
                break;
        }
        return oper;
    }

    /**
     * 加
     *
     * @param number1
     * @param number2
     * @throws Exception
     */
    public void Addpend(double number1, double number2)  {
        String add = "+";
        Operation Add = createOperation(add);
        Add.setNumber1(number1);
        Add.setNumber2(number2);
        Double result = null;
        try {
            result = Add.getResult();
            System.out.println("加法：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 减
     *
     * @param number1
     * @param number2
     * @throws Exception
     */
    public void Subtract(double number1, double number2)  {
        String sub = "-";
        Operation Sub = createOperation(sub);
        Sub.setNumber1(number1);
        Sub.setNumber2(number2);
        Double result = null;
        try {
            result = Sub.getResult();
            System.out.println("减法：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 乘
     *
     * @param number1
     * @param number2
     * @throws Exception
     */
    public void Multiply(double number1, double number2) {
        String mul = "*";
        Operation Mul = createOperation(mul);
        Mul.setNumber1(number1);
        Mul.setNumber2(number2);
        Double result = null;
        try {
            result = Mul.getResult();
            System.out.println("乘法：" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 除
     *
     * @param number1
     * @param number2
     * @throws Exception
     */
    public void Divide(double number1, double number2)  {
        String div = "/";
        Operation Div = createOperation(div);
        Div.setNumber1(number1);
        Div.setNumber2(number2);
        Double result = null;
        try {
            result = Div.getResult();
            System.out.println("除法：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
