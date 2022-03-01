package com.zzq.design_model.observer_model;

import org.omg.CORBA.OBJ_ADAPTER;

import java.util.ArrayList;
import java.util.List;

import sun.security.jca.GetInstance;

/**
 * @author zhuzaiqing
 * @describe 被观察者
 * @time 2020/7/2 10:46
 */
public class Subject {
    List<Observer> list = new ArrayList<>();
    public static Subject subject;

    public static Subject getInstance() {
        if (null == subject) {
            synchronized (Subject.class) {
                subject = new Subject();
            }
        }
        return subject;
    }

    public void add(Observer observer) {
        list.add(observer);
    }

    public void remove() {
        list.clear();
    }

    public void notifyObserver(String msg) {
        for (Observer observer : list) {
            observer.getMessage(msg);
        }
    }
}
