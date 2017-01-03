package com.zhixin.com.jsoup.ui.douban.model.impl;

import com.zhixin.com.jsoup.network.RetrofitUtil;
import com.zhixin.com.jsoup.rx.TransformerUtil;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.ui.douban.entity.Movie;
import com.zhixin.com.jsoup.ui.douban.model.TheatersModel;

import rx.Observable;

/**
 * Created by zhangstar on 2016/12/30.
 */

public class TheatersModelImpl implements TheatersModel {
    @Override
    public Observable<Movie> getTheatersData(int start) {
        return RetrofitUtil.getApiService(GlobalParams.DOUBAN).getTheatersData(20, start).compose(TransformerUtil.<Movie>SchedulersCompose());
    }
}
