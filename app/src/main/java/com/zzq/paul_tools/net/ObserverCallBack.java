package com.zzq.paul_tools.net;

import android.text.TextUtils;

import com.zzq.paul_tools.bean.BaseBean;
import com.zzq.paul_tools.utils.EgretLog;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author zhuzaiqing
 * @describe 订阅者回调类
 * @time 2018/11/1 13:53
 */
public abstract class ObserverCallBack<T> implements Observer<BaseBean<T>> {





    @Override
    public void onSubscribe(Disposable s) {
//        refreshTask = new RefreshTask();
//        if (timer == null) {
//            timer = new Timer();
//            timer.scheduleAtFixedRate(refreshTask, 0L, (long) 1000);
//        }
    }

    @Override
    public void onNext(BaseBean<T> t) {
        if (null != t.getCode()) {
            if (TextUtils.equals("10000", t.getCode())) {
                onSuccess(t);
            } else {
                onFailure(t);
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        //走这里不需要做任何处理
        EgretLog.getInstance().e(ObserverCallBack.class, "来了:" + t.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(BaseBean<T> t);

    public abstract void onFailure(BaseBean<T> t);

}
