package android.zhixin.com.jsoup.network;


import android.zhixin.com.jsoup.data.Douban250Bean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络接口api
 */

public interface APIService {
    @GET("/page/{page}")
    Observable<String> getFCPhoto(@Path("page") int page);

    @GET("/v2/movie/top250")
    Observable<Douban250Bean> getDouban250Data(@Query("count") int count, @Query("start") int start);
}