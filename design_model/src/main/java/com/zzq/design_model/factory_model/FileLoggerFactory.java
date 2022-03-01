package com.zzq.design_model.factory_model;

/**
 * @author zhuzaiqing
 * @describe 具体工厂
 * @time 2020/7/2 16:18
 */
public class FileLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        // 创建文件日志记录器对象
        Logger logger= new FileLogger();
        //创建文件,代码省略

        return logger;
    }
}
