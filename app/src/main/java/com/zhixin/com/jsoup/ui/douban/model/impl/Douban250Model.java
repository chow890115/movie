package com.zhixin.com.jsoup.ui.douban.model.impl;

import com.zhixin.com.jsoup.ui.douban.entity.Douban250Bean;
import com.zhixin.com.jsoup.ui.douban.model.IDouban250Model;
import com.zhixin.com.jsoup.network.RetrofitUtil;
import com.zhixin.com.jsoup.tools.GlobalParams;

import rx.Observable;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public class Douban250Model implements IDouban250Model {
    @Override
    public Observable<Douban250Bean> getDoubanMovie250(int start) {
        return RetrofitUtil.getApiService(GlobalParams.DOUBAN).getDouban250Data(20, start);
    }
}
