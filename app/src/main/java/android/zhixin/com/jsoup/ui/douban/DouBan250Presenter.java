package android.zhixin.com.jsoup.ui.douban;

import android.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import android.zhixin.com.jsoup.data.Douban250Bean;
import android.zhixin.com.jsoup.model.IDouban250Model;
import android.zhixin.com.jsoup.model.impl.Douban250Model;
import android.zhixin.com.jsoup.network.HttpUtil;

import rx.Subscriber;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public class DouBan250Presenter extends BasePresenterImpl<DoubanView> {
    public IDouban250Model model;

    public DouBan250Presenter() {
        model = new Douban250Model();
    }

    public void getDoubanMovie250(int start) {
        mSubscription =model.getDoubanMovie250(start).compose(HttpUtil.<Douban250Bean>composeResponse()).subscribe(new Subscriber<Douban250Bean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.onError();
            }

            @Override
            public void onNext(Douban250Bean bean) {
                view.onSuccess(bean);
            }
        });
    }


}
