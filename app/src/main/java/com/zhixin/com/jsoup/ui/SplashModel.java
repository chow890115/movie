package com.zhixin.com.jsoup.ui;

import com.zhixin.com.jsoup.data.SplashBean;
import com.zhixin.com.jsoup.network.RetrofitUtil;
import com.zhixin.com.jsoup.network.TransformerUtil;
import com.zhixin.com.jsoup.tools.GlobalParams;

import rx.Observable;

/**
 * Created by zhangstar on 2016/12/8.
 */

public class SplashModel implements ISplashModel {
    @Override
    public Observable<SplashBean> getSplashImage() {
        return RetrofitUtil.getApiService(GlobalParams.ZHIHU_NEWS_URL).getSplashImage().compose(TransformerUtil.<SplashBean>SchedulersCompose());
    }
}
