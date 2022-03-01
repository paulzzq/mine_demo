package com.zzq.paul_tools.activity;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zzq.paul_tools.R;
import com.zzq.paul_tools.bean.Article;
import com.zzq.paul_tools.bean.BaseBean;
import com.zzq.paul_tools.bean.BaseListBean;
import com.zzq.paul_tools.bean.CountDetailBean;
import com.zzq.paul_tools.bean.HomeDataBean;
import com.zzq.paul_tools.net.ApiManager;
import com.zzq.paul_tools.net.ApiService;
import com.zzq.paul_tools.net.ObserverCallBack;
import com.zzq.paul_tools.utils.EgretLog;
import com.zzq.paul_tools.view.ProgressDialog;
import com.zzq.paul_tools.view.TitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends BaseActivity {
    public static final String TAG = "RxJavaActivity";
    @BindView(R.id.title)
    TitleView title;
    @BindView(R.id.get_btn)
    Button getBtn;
    @BindView(R.id.post_btn)
    Button postBtn;
    @BindView(R.id.result_tv)
    TextView resultTv;

    private String name;
    private ProgressDialog mDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected void init() {
        mDialog = new ProgressDialog(this, "请稍候...");
        initData();
        initView();
//        testRxjava1();
//        testRxjava2();
//        testRxjava3();
        testRxjava4();
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();//增加部分
                testThreadPool();
                Looper.loop();//增加部分
            }
        }.start();
    }

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    /**
     * 测试线程池多线程并发
     */
    private void testThreadPool() {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(5);
            List<String> resultList = new ArrayList<>();
            Log.e("eee", "------任务开始启动");
            disPatchAsyncTask(countDownLatch, resultList);
            countDownLatch.await();
            Log.e("eee", "------任务执行结束");
        } catch (Exception e) {
        }
    }

    /**
     * 并发任务
     *
     * @param taskLatch
     * @param resultList
     */
    private void disPatchAsyncTask(final CountDownLatch taskLatch, List<String> resultList) {
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e("eee", "--------开始任务");
                    getStory(taskLatch);
                }
            });
        }

    }

    private void testRxjava4() {
        Observable.just(1, 2, 3, 8)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.e(TAG, "accept: reduce : " + integer + "\n");
            }
        });


    }

    private void testRxjava3() {
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.e(TAG, "zip : accept : " + s + "\n");
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(1);
                    Log.e(TAG, "Integer emit : 1 \n");
                    e.onNext(2);
                    Log.e(TAG, "Integer emit : 2 \n");
                    e.onNext(3);
                    Log.e(TAG, "Integer emit : 3 \n");
                    e.onNext(4);
                    Log.e(TAG, "Integer emit : 4 \n");
                    e.onNext(5);
                    Log.e(TAG, "Integer emit : 5 \n");
                }
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("A");
                    Log.e(TAG, "String emit : A \n");
                    e.onNext("B");
                    Log.e(TAG, "String emit : B \n");
                    e.onNext("C");
                    Log.e(TAG, "String emit : C \n");
                }
            }
        });
    }

    private void testRxjava2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "我是改变后的返回数据" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s + "\n");

            }
        });
    }

    private void testRxjava1() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                mRxOperatorsText.append("Observable emit 1" + "\n");
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
//                mRxOperatorsText.append("Observable emit 2" + "\n");
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
//                mRxOperatorsText.append("Observable emit 3" + "\n");
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
//                mRxOperatorsText.append("Observable emit 4" + "\n");
                Log.e(TAG, "Observable emit 4" + "\n");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                mRxOperatorsText.append("onSubscribe : " + d.isDisposed() + "\n");
                Log.e(TAG, "onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
//                mRxOperatorsText.append("onNext : value : " + integer + "\n");
                Log.e(TAG, "onNext : value : " + integer + "\n");
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
//                    mRxOperatorsText.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                    Log.e(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
//                mRxOperatorsText.append("onError : value : " + e.getMessage() + "\n");
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
//                mRxOperatorsText.append("onComplete" + "\n");
                Log.e(TAG, "onComplete" + "\n");
            }
        });
    }

    public void initData() {
        if (getIntent() != null) {
            name = getIntent().getStringExtra("data");
        }
    }

    public void initView() {
        title.mTitleCenterTV.setText(name);
        title.setLeftToBack(this);
        resultTv.setMovementMethod(ScrollingMovementMethod.getInstance());
//
    }

//    @OnClick({R.id.get_btn, R.id.post_btn})
//    void onclick(View v) {
//        switch (v.getId()) {
//            case R.id.get_btn:
//                getStory();
//                break;
//            case R.id.post_btn:
//                getCounts();
//                break;
//        }
//    }


    private int count = 0;

    /**
     * get请求测试
     */
    private void getStory(final CountDownLatch taskLatch) {
        String url = "https://api.xzytest.cn/api/public/interface/410100/souvenir";
        ApiManager.getInstance().getApiService(ApiService.class)
                .requestHomeData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverCallBack<HomeDataBean>() {
                    @Override
                    public void onSuccess(BaseBean<HomeDataBean> t) {
//                        getStory();
                        taskLatch.countDown();
                        Log.e("eee", "---------------------------处理完成");

                    }

                    @Override
                    public void onFailure(BaseBean<HomeDataBean> t) {

                    }
                });


    }

    public final String ARTICLE = "article";
    public final String KNOWLEDGE = "knowledge";

}
