package com.zzq.paul_tools.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.bean.Check;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @author zhuzaiqing
 * @describe 简单测试反射，编写简单的框架
 * @time 2020/6/28 11:03
 */
public class ReflectActivity extends BaseActivity {
    @Check(name = "我很好")
    public String name;


    @Override
    protected int getLayout() {
        return R.layout.activity_reflect;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void init() {
        Class<ReflectActivity> cls = ReflectActivity.class;
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Check.class)) {
                //开始取值
                Check check = field.getAnnotation(Check.class);
                Log.e("eee", "----:" + check.name());
            }
        }

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                Log.e("eee", "----开始发射数据");
//                emitter.onNext(1);
//            }
//        }).flatMap(new Function<Integer, Observable<Integer>>() {
//            @Override
//            public Observable<Integer> apply(Integer integer) throws Exception {
//                return Observable.just(1).delay(3, TimeUnit.SECONDS);
//            }
//        }).observeOn(Schedulers.io()).
//                flatMap(new Function<Integer, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Integer s) throws Exception {
//                        return Observable.just(s + 3);
//                    }
//                }).subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object s) throws Exception {
//                        Log.e("eee", "请求结果：" + s.toString());
//                    }
//                });


    }
}
