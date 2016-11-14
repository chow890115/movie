package com.zhixin.com.jsoup.model;

import rx.Observable;

/**
 * Created by zhangwenxing on 2016/10/28.
 */

public interface MainModel {
    Observable<String> requestPhoto(String url);
}
