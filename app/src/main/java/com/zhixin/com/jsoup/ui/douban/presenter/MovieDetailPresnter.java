package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.rx.SubscribeCall;
import com.zhixin.com.jsoup.rx.TransformerUtil;

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
                .compose(TransformerUtil.<MovieDetailBean>SchedulersCompose())
                .subscribe(new SubscribeCall<>(view)));
    }
}
