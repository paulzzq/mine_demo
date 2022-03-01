package com.zzq.design_model.factory_model;

/**
 * @author zhuzaiqing
 * @describe 具体的工厂
 * @time 2020/7/2 16:03
 */
public class DatabaseLogger implements Logger {
    @Override
    public void writeLog() {
        //具体工厂需要做的事情，此处省略
        System.out.println("数据库日志记录");
    }
}
