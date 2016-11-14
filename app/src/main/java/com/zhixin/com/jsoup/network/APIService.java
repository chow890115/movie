package com.zhixin.com.jsoup.network;


import com.zhixin.com.jsoup.data.Douban250Bean;
import com.zhixin.com.jsoup.data.MovieDetailBean;

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

    @GET("/v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Query("id") int id);
}