package com.zzq.paul_tools.net;

import android.support.annotation.NonNull;

import com.zzq.paul_tools.constants.UrlConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhuzaiqing
 * @describe  Retrofit管理类
 * @time 2018/11/1 13:45
 */

public class ApiManager {

    private static final int DEFAULT_TIMEOUT = 5;

    private static ApiManager apiManager;
    private OkHttpClient mOkHttpClient;

    public static ApiManager getInstance() {
        if (null == apiManager) {
            synchronized (ApiManager.class) {
                if (null == apiManager) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }


    public <T> T getApiService(@NonNull Class<T> service) {
        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new MyInterceptor())
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstants.BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }


}
