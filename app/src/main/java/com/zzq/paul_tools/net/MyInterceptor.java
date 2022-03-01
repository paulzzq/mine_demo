package com.zzq.paul_tools.net;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2018/11/1 13:43
 */

public class MyInterceptor implements Interceptor {

    private final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            requestBody = addParamsToFormBody((FormBody) requestBody);
        } else if (requestBody instanceof MultipartBody) {
            requestBody = addParamsToMultipartBody((MultipartBody) requestBody);
        }

        if (null != requestBody) {
            request = request.newBuilder()
                    .url(request.url())
                    .method(request.method(), requestBody)
                    .build();
        }

        //打印请求参数、请求结果
        String body = printRequest(request);
        return printResponse(chain.proceed(request), body);

    }


    /**
     * 为MultipartBody类型请求体添加参数
     *
     * @param body
     * @return
     */
    private MultipartBody addParamsToMultipartBody(MultipartBody body) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("system", "0");

        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addPart(body.part(i));
        }

        return builder.build();
    }

    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @return
     */

    private FormBody addParamsToFormBody(FormBody body) {

        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("system", "0");
//        builder.add("token", "");
//        builder.add("deviceToken", "fdfdfdfdfdfdfdf");

        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
        }

        return builder.build();
    }


    private String printRequest(Request request) {
        RequestBody requestBody = request.body();
        String body = null;
        if (requestBody != null) {
            //post请求
            Buffer buffer = new Buffer();
            try {
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                if (charset != null) {
                    body = buffer.readString(charset);
                }

                Log.e("retrofitRequest", String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %sbody：%s",
                        request.method(), request.url(), request.headers(), body));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            //get请求
            Log.e("retrofitRequest", String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %sbody：%s",
                    request.method(), request.url(), request.headers(), body));
        }
        return body;
    }

    private Response printResponse(Response response, String body) {
        long startNs = System.nanoTime();
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        BufferedSource source = null;
        if (responseBody != null) {
            source = responseBody.source();
        }
        if (source != null) {
            try {
                source.request(Long.MAX_VALUE); // Buffer the entire body.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Buffer buffer = null;
        if (source != null) {
            buffer = source.buffer();
        }

        Charset charset = UTF8;
        MediaType contentType = null;
        if (responseBody != null) {
            contentType = responseBody.contentType();
        }
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }

        String rBody = null;
        if (charset != null) {
            rBody = buffer.clone().readString(charset);
        }

        Log.e("retrofitResponse", String.format("收到响应 %s%s %ss\n请求url：%s\n请求body：%s\n响应body：%s",
                response.code(), response.message(), tookMs, response.request().url(), body, rBody));

        //处理异常error0002
//        disposeException(rBody);

        return response;
    }
}
