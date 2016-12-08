package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.network.SubscribeCall;
import com.zhixin.com.jsoup.network.TransformerUtil;
import com.zhixin.com.jsoup.ui.douban.entity.Douban250Bean;
import com.zhixin.com.jsoup.ui.douban.model.IDouban250Model;
import com.zhixin.com.jsoup.ui.douban.model.impl.Douban250Model;
import com.zhixin.com.jsoup.ui.douban.view.IDoubanView;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public class DouBan250Presenter extends BasePresenterImpl<IDoubanView<Douban250Bean>> {
    public IDouban250Model model;

    public DouBan250Presenter() {
        model = new Douban250Model();
    }

    public void getDoubanMovie250(int start) {
        addSubscription(model.getDoubanMovie250(start).compose(TransformerUtil.<Douban250Bean>SchedulersCompose()).subscribe(new SubscribeCall<>(view)));
    }

}
