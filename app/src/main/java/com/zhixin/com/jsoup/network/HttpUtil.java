package com.zhixin.com.jsoup.network;

import com.zhixin.com.jsoup.BuildConfig;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.tools.ZhuoXinToast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

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
            ZhuoXinToast.getInstance().show("网络连接超时");
        } else if (e instanceof ConnectException) {
            ZhuoXinToast.getInstance().show("网络连接超时");
        } else if (e instanceof UnknownHostException) {
            ZhuoXinToast.getInstance().show("网络异常，请检查网络是否开启！");
        }
    }

    //Rxjava操作符封装
    public static <T> Observable.Transformer<T, T> composeResponse() {
        return (Observable.Transformer<T, T>) mTransformer;
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createOkHttpBuilder().build())
                .build();
        return retrofit.create(serviceClass);
    }

    public static OkHttpClient.Builder createOkHttpBuilder() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
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
