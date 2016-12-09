package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.rx.SubscribeCall;
import com.zhixin.com.jsoup.ui.douban.entity.FilmBean;
import com.zhixin.com.jsoup.ui.douban.model.IFilmModel;
import com.zhixin.com.jsoup.ui.douban.model.impl.FilmModel;
import com.zhixin.com.jsoup.ui.douban.view.IFilmView;

/**
 * Created by zhangstar on 2016/12/7.
 */

public class FilmPresenter extends BasePresenterImpl<IFilmView<FilmBean>> {
    IFilmModel model;

    public FilmPresenter() {
        model = new FilmModel();
    }

    public void getFilmDetailInfo(String id) {
        addSubscription(model.getFilmDetailInfo(id).subscribe(new SubscribeCall<>(view)));
    }
}
