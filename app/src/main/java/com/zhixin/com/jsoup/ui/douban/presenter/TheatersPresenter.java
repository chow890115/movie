package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.rx.SubscribeCall;
import com.zhixin.com.jsoup.ui.douban.model.TheatersModel;
import com.zhixin.com.jsoup.ui.douban.model.impl.TheatersModelImpl;
import com.zhixin.com.jsoup.ui.douban.view.ITheatersView;

/**
 * Created by zhangstar on 2016/12/30.
 */

public class TheatersPresenter extends BasePresenterImpl<ITheatersView> {
    TheatersModel model;

    public TheatersPresenter() {
        model = new TheatersModelImpl();
    }

    public void getTheaters(int start) {
        addSubscription(model.getTheatersData(start).subscribe(new SubscribeCall<>(view)));
    }
}
