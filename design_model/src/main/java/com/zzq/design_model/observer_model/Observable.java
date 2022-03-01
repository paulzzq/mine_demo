package com.zzq.design_model.observer_model;

import java.sql.SQLOutput;

/**
 * @author zhuzaiqing
 * @describe  具体观察者
 * @time 2020/7/2 10:44
 */
public class Observable implements Observer {
    @Override
    public void getMessage(String msg) {
        System.out.println("收到了推送消息："+msg);
    }
}
