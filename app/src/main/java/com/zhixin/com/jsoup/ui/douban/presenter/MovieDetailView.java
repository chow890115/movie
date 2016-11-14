package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.view.IBaseView;
import com.zhixin.com.jsoup.data.MovieDetailBean;

/**
 * Created by zhangwenxing on 2016/11/14.
 */

public interface MovieDetailView extends IBaseView {
    void onSuccess(MovieDetailBean bean);
}
