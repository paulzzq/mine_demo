package com.zzq.design_model.factory_model;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/7/2 16:13
 */
public class DatabaseLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        //连接数据库,代码省略
        //创建数据库日志记录器对象
        Logger logger= new DatabaseLogger();
        //初始化数据库日志记录器,代码省略
        return logger;
    }
}
