package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.rx.SubscribeCall;
import com.zhixin.com.jsoup.ui.douban.model.ICommingSoonModel;
import com.zhixin.com.jsoup.ui.douban.model.impl.CommingSoonModelImpl;
import com.zhixin.com.jsoup.ui.douban.view.ICommingSoonView;

/**
 * Created by zhangstar on 2017/2/9.
 */

public class CommingSoonPresenter extends BasePresenterImpl<ICommingSoonView> {
    private ICommingSoonModel mICommingSoonModel;

    public CommingSoonPresenter() {
        mICommingSoonModel = new CommingSoonModelImpl();
    }

    public void getCommingSoonData(int start) {
        addSubscription(mICommingSoonModel.getCommingSoon(start).subscribe(new SubscribeCall<>(view)));
    }
}
