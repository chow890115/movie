package com.zhixin.com.jsoup.network;


import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.data.SplashBean;
import com.zhixin.com.jsoup.ui.douban.entity.FilmBean;
import com.zhixin.com.jsoup.ui.douban.entity.Movie;

import okhttp3.ResponseBody;
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

    //豆瓣250
    @GET("/v2/movie/top250")
    Observable<Movie> getDouban250Data(@Query("count") int count, @Query("start") int start);

    //正在热映
    @GET("v2/movie/in_theaters")
    Observable<Movie> getTheatersData(@Query("count") int count, @Query("start") int start);

    //某电影详情
    @GET("/v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") int id);

    //影人详细信息 http://api.douban.com//v2/movie/celebrity/1025182
    @GET("/v2/movie/celebrity/{id}")
    Observable<FilmBean> getFilmDetailInfo(@Path("id") String id);

    //splash图片  http://news-at.zhihu.com/api/5/start-image/1920*1080
    @GET("/api/5/start-image/1920*1080")
    Observable<SplashBean> getSplashImage();

    @GET("/app-debug.apk")
    Observable<ResponseBody> downLoad();

    //即将上映
    @GET("/v2/movie/coming_soon")
    Observable<Movie> commingSoon(@Query("count") int count, @Query("start") int start);
}