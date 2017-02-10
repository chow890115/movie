package com.zhixin.com.jsoup.ui.douban.model;

import com.zhixin.com.jsoup.ui.douban.entity.Movie;

import rx.Observable;

/**
 * Created by zhangstar on 2017/2/9.
 */

public interface ICommingSoonModel {
    Observable<Movie> getCommingSoon(int start);
}
