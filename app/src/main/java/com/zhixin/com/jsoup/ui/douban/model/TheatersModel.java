package com.zhixin.com.jsoup.ui.douban.model;

import com.zhixin.com.jsoup.ui.douban.entity.Movie;

import rx.Observable;

/**
 * Created by zhangstar on 2016/12/30.
 */

public interface TheatersModel {
    Observable<Movie> getTheatersData(int start);

}
