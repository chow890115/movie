package com.zhixin.com.jsoup.ui;

import com.zhixin.com.jsoup.data.SplashBean;

import rx.Observable;

/**
 * Created by zhangstar on 2016/12/8.
 */

public interface ISplashModel {
    Observable<SplashBean> getSplashImage();
}
