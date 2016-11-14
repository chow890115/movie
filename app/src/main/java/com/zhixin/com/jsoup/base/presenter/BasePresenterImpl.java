package com.zhixin.com.jsoup.base.presenter;

import rx.Subscription;

/**
 * Created by zhangwenxing on 2016/10/28.
 */

public abstract class BasePresenterImpl<V>  {
    public V view;
    public Subscription mSubscription;

    public void attach(V view) {
        this.view = view;
    }

    public void onDestroy() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
