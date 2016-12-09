package com.zhixin.com.jsoup.ui.douban.model.impl;

import com.zhixin.com.jsoup.network.RetrofitUtil;
import com.zhixin.com.jsoup.network.TransformerUtil;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.ui.douban.entity.FilmBean;
import com.zhixin.com.jsoup.ui.douban.model.IFilmModel;

import rx.Observable;

/**
 * Created by zhangstar on 2016/12/7.
 */

public class FilmModel implements IFilmModel {
    @Override
    public Observable<FilmBean> getFilmDetailInfo(String id) {
        return RetrofitUtil.getApiService(GlobalParams.DOUBAN).getFilmDetailInfo(id).compose(TransformerUtil.<FilmBean>SchedulersCompose());
    }
}
