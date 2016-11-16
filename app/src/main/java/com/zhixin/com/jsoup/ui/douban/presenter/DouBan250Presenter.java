package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.data.Douban250Bean;
import com.zhixin.com.jsoup.model.IDouban250Model;
import com.zhixin.com.jsoup.model.impl.Douban250Model;
import com.zhixin.com.jsoup.network.HttpUtil;
import com.zhixin.com.jsoup.network.SubscribeCall;
import com.zhixin.com.jsoup.ui.douban.view.DoubanView;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public class DouBan250Presenter extends BasePresenterImpl<DoubanView<Douban250Bean>> {
    public IDouban250Model model;

    public DouBan250Presenter() {
        model = new Douban250Model();
    }

    public void getDoubanMovie250(int start) {
        addSubscription(model.getDoubanMovie250(start).compose(HttpUtil.<Douban250Bean>composeResponse()).subscribe(new SubscribeCall<>(view)));
    }

}
