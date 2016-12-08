package com.zhixin.com.jsoup.ui.douban.model;

import com.zhixin.com.jsoup.ui.douban.entity.Douban250Bean;

import rx.Observable;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public interface IDouban250Model {
    Observable<Douban250Bean> getDoubanMovie250(int start);
}
