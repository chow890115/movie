package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.network.HttpUtil;

import rx.Subscriber;

/**
 * Created by zhangstar on 2016/11/14.
 */

public class MovieDetailPresnter extends BasePresenterImpl<MovieDetailView> {
    private MovieDetailModel model;

    public MovieDetailPresnter() {
        model = new MovieDetailModel();
    }

    public void getDetailModel(int id) {
      mSubscription= model.getMovieDetail(id).compose(HttpUtil.<MovieDetailBean>composeResponse()).subscribe(new Subscriber<MovieDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.onError();
            }

            @Override
            public void onNext(MovieDetailBean movieDetailBean) {
                view.onSuccess(movieDetailBean);
            }
        });
    }
}
