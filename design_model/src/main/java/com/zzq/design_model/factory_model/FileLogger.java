package com.zzq.design_model.factory_model;

/**
 * @author zhuzaiqing
 * @describe 具体的产品
 * @time 2020/7/2 16:04
 */
public class FileLogger implements Logger {
    @Override
    public void writeLog() {
        //具体工厂需要做的事情，此处省略
        System.out.println("文件日志记录");
    }
}
