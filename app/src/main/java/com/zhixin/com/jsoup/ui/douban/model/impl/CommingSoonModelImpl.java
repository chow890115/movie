package com.zhixin.com.jsoup.ui.douban.model.impl;

import com.zhixin.com.jsoup.network.RetrofitUtil;
import com.zhixin.com.jsoup.rx.TransformerUtil;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.ui.douban.entity.Movie;
import com.zhixin.com.jsoup.ui.douban.model.ICommingSoonModel;

import rx.Observable;

/**
 * Created by zhangstar on 2017/2/9.
 */

public class CommingSoonModelImpl implements ICommingSoonModel {
    @Override
    public Observable<Movie> getCommingSoon(int start) {
        return RetrofitUtil.getApiService(GlobalParams.DOUBAN).commingSoon(20, start).compose(TransformerUtil.<Movie>SchedulersCompose());
    }
}
