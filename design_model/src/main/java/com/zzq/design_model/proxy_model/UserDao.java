package com.zzq.design_model.proxy_model;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2020/7/3 10:37
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
    @Override
    public void getMsg(){
        System.out.println("---开始吧");
    }
}
