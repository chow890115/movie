package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.network.HttpUtil;
import com.zhixin.com.jsoup.tools.GlobalParams;

import rx.Observable;

/**
 * Created by zhangstar on 2016/11/14.
 */

public class MovieDetailModel {

    public Observable<MovieDetailBean> getMovieDetail(int id) {

        return HttpUtil.getApiService(GlobalParams.DOUBAN).getMovieDetail(id);
    }
}
