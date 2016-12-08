package com.zhixin.com.jsoup.ui.douban.model;

import com.zhixin.com.jsoup.ui.douban.entity.FilmBean;

import rx.Observable;

/**
 * Created by zhangstar on 2016/12/7.
 */

public interface IFilmModel {
    Observable<FilmBean> getFilmDetailInfo(String id);
}
