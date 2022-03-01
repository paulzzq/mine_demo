package com.zzq.design_model;


import com.zzq.design_model.build_model.Computer;
import com.zzq.design_model.decorator_model.Beverage;
import com.zzq.design_model.decorator_model.DarkRoast;
import com.zzq.design_model.decorator_model.Decaf;
import com.zzq.design_model.decorator_model.Espresso;
import com.zzq.design_model.decorator_model.Mocha;
import com.zzq.design_model.decorator_model.Soy;
import com.zzq.design_model.decorator_model.Whip;
import com.zzq.design_model.facade_model.ComputerBean;
import com.zzq.design_model.factory_model.FileLoggerFactory;
import com.zzq.design_model.factory_model.Logger;
import com.zzq.design_model.factory_model.LoggerFactory;
import com.zzq.design_model.observer_model.Observable;
import com.zzq.design_model.observer_model.Subject;
import com.zzq.design_model.proxy_model.IUserDao;
import com.zzq.design_model.proxy_model.ProxyFactory;
import com.zzq.design_model.proxy_model.UserDao;
import com.zzq.design_model.simple_factory_model.OperationFactory;

/**
 * @author zhuzaiqing
 * @describe 程序启动，24中设计模式练习
 * @time 2020/7/2 10:14
 */
public class Main {
    public static void main(String[] args) {
        //1.单列模式测试  instance（饿汉式、懒汉式、双重校验锁，静态内部类 、枚举）-------------------
//        Subject.getInstance();
// ==========================================================================

        //2.观察者模式 observer--------------
//        Subject.getInstance().add(new Observable());
//        Subject.getInstance().notifyObserver("马化腾给你发红包了");
//        Subject.getInstance().remove();
//==========================================================================

        //3.简单工厂模式-------------------------------
//        OperationFactory.getInstance().Addpend(1,2);
//==========================================================================

        //4.工厂模式 factory---------------------------------
//        FileLoggerFactory factory = null;//可引入配置文件和反射机制实现
//        try {
//            factory = (FileLoggerFactory)(Class.forName("com.zzq.design_model.factory_model.FileLoggerFactory")).newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Logger logger = factory.createLogger();
//        logger.writeLog();
//==========================================================================

        //5.代理模式  Proxy（静态代理、动态代理--也叫JDK代理、Cglib代理）
        // 在业务中使用动态代理，一般是为了给需要实现的方法添加预处理或者添加后续操作，
        // 但是不干预实现类的正常业务，把一些基本业务和主要的业务逻辑分离---------------------------------
        // 目标对象
//        IUserDao target = new UserDao();
//        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
//        System.out.println(target.getClass());
//        // 给目标对象，创建代理对象
//        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
//        // class $Proxy0   内存中动态生成的代理对象
//        System.out.println(proxy.getClass());
//        // 执行方法   【代理对象】
//        proxy.save();
//        proxy.getMsg();
//==========================================================================

        //6.建造者模式(Builder Pattern)  将一个复杂对象的构建与它的表示分离，
        // 使得同样的构建过程可以创建不同的表示----------------------
//        new Computer.CreateBuild().setCpu("cpu").setBoard("主板").setHd("hd").build().Show();
        //==========================================================================

        //7.门面模式 ：他隐藏了系统的复杂性，并向客户端提供了一个可以访问系统的接口。
        // 这种类型的设计模式属于结构性模式。为子系统中的一组接口提供了一个统一的访问接口，这个接口使得子系统更容易被访问或者使用
//        ComputerBean computer = new ComputerBean();
//        computer.start();
//        computer.shutDown();

        //8.装饰者模式  :动态的给一个对象附加额外的功能，因此它也是子类化的一种替代方法。该设计模式在JDK中广泛运用
        //订一杯Espresso(2.00)，不需要调料，打印出它的描述与价钱。
        Beverage beverage = new Espresso();
        System.out.println("Description: " + beverage.getDescription() + " $" + beverage.cost());

        //制造出一个DarkRoast(3.00)对象,用Mocha(0.2)装饰它,用第二个Mocha(0.2)装饰它,用Whip(0.4)装饰它，打印出它的描述与价钱。
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println("Description: " + beverage2.getDescription() + " $" + beverage2.cost());

        //再来一杯调料为豆浆(Soy 0.3)、摩卡(Mocha 0.2)、奶泡(Whip 0.4)的Decaf（低咖啡因咖啡 4.00），打印出它的描述与价钱。
        Beverage beverage3 = new Decaf();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println("Description: " + beverage3.getDescription() + " $" + beverage3.cost());
        //==========================================================================


        //9.适配器模式






    }
}
