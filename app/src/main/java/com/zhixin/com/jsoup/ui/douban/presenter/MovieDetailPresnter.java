package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.network.HttpUtil;
import com.zhixin.com.jsoup.network.SubscribeCall;

/**
 * Created by zhangstar on 2016/11/14.
 */

public class MovieDetailPresnter extends BasePresenterImpl<MovieDetailView<MovieDetailBean>> {
    private MovieDetailModel model;

    public MovieDetailPresnter() {
        model = new MovieDetailModel();
    }

    public void getDetailModel(int id) {
        addSubscription(model.getMovieDetail(id)
                .compose(HttpUtil.<MovieDetailBean>composeResponse())
                .subscribe(new SubscribeCall<>(view)));
    }
}
