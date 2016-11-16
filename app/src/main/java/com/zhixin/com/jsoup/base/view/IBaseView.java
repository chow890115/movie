package com.zhixin.com.jsoup.base.view;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public interface IBaseView<T> {
    void onError();

    void onSuccess(T t);
}
