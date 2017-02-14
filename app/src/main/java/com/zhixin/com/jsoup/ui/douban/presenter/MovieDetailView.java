package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.view.IBaseView;

import java.util.List;

/**
 * Created by zhangwenxing on 2016/11/14.
 */

public interface MovieDetailView<T> extends IBaseView<T> {

    void setPhotos(List<String> images);
}
