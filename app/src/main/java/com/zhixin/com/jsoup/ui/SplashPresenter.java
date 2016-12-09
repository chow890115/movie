package com.zhixin.com.jsoup.ui;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.data.SplashBean;
import com.zhixin.com.jsoup.network.SubscribeCall;

/**
 * Created by zhangstar on 2016/12/8.
 */

public class SplashPresenter extends BasePresenterImpl<ISplashView<SplashBean>> {
    private ISplashModel model;

    public SplashPresenter() {
        model = new SplashModel();
    }

    public void getSplashImage() {
        addSubscription(model.getSplashImage().subscribe(new SubscribeCall<>(view)));
    }
}
