package com.zzq.paul_tools.bean;

import android.util.Log;

import com.just.agentweb.LogUtils;
import com.zzq.paul_tools.activity.ReflectActivity;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/6/28 11:10
 */
public class PersonBean {
    public void eat(){
        Log.e("eee","---开始吃饭");
    }
    public PersonBean(){
        Properties properties=new Properties();
        ClassLoader classLoader=PersonBean.class.getClassLoader();
        InputStream is=classLoader.getResourceAsStream("reflect.properties");
        try {
            properties.load(is);
            String className=properties.getProperty("className");
            String methodName=properties.getProperty("methodName");
            Class cls=Class.forName(className);
            Object obj=cls.newInstance();
            Method method=cls.getMethod(methodName);
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
