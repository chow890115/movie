package com.zhixin.com.jsoup.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.zhixin.com.jsoup.BuildConfig;
import com.zhixin.com.jsoup.base.application.BaseApplication;
import com.zhixin.com.jsoup.tools.GlobalParams;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络工具封装类
 * Created by zhangwenxing on 16/9/7.
 */
public class HttpUtil {
    public static final int CONNECT_TIMEOUT = 15;
    public static final int WRITE_TIMEOUT = 15;
    public static final int READ_TIMEOUT = 15;


    static final Observable.Transformer mTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    };


    //Retrofit异常处理
    public static void resolveError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
        } else if (e instanceof SocketTimeoutException) {
            Toast.makeText(BaseApplication.getInstance(), "网络连接超时", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(BaseApplication.getInstance(), "网络连接超时", Toast.LENGTH_SHORT).show();
        } else if (e instanceof UnknownHostException) {
            Toast.makeText(BaseApplication.getInstance(), "网络异常，请检查网络是否开启！", Toast.LENGTH_SHORT).show();
        }
    }

    //Rxjava操作符封装
    public static <T> Observable.Transformer<T, T> composeResponse() {
        return (Observable.Transformer<T, T>) mTransformer;
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createOkHttpBuilder().build())
                .build();
        return retrofit.create(serviceClass);
    }

    public static OkHttpClient.Builder createOkHttpBuilder() {
        File httpCacheDirectory = new File(BaseApplication.getInstance().getExternalCacheDir(), "responses");
        //设置缓存 10M
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheInterceptor())
                .cache(cache)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //
                .addInterceptor(mTokenInterceptor);
//        //debug模式打印log
        if (BuildConfig.LOG_DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.interceptors().add(httpLoggingInterceptor);
        }

        return okHttpClient;
    }

    /**
     * 判断网络是否可用
     *
     * @param context Context对象
     */
    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }

    public static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isNetworkReachable(BaseApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response1;
            Response response = chain.proceed(request);

            if (isNetworkReachable(BaseApplication.getInstance())) {
                int maxAge = 60 * 60 * 24 * 7;
                // 有网络时 设置缓存超时时间1个小时
                response1 = response.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "max-age=" + maxAge)
                        .build();
            } else {
                // 无网络时，设置超时为2周
                int maxStale = 60 * 60 * 24 * 14;
                response1 = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "max-age=" + maxStale)
                        .build();
            }
            return response1;
        }
    }


    static Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request authorised = originalRequest.newBuilder()
                    .build();
            return chain.proceed(authorised);
        }
    };


    /**
     * 固定服务器
     *
     * @return API
     */
    public static APIService getApiService() {
        return getApiService(GlobalParams.BASE_URL);
    }

    /**
     * 任意指定服务器
     *
     * @param baseUrl 服务器URL
     * @return API
     */
    public static APIService getApiService(String baseUrl) {
        return createService(APIService.class, baseUrl);
    }


}
