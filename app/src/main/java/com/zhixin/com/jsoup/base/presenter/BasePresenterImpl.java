package com.zhixin.com.jsoup.base.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangwenxing on 2016/10/28.
 */

public abstract class BasePresenterImpl<V> {
    public V view;
    private CompositeSubscription mCompositeSubscription;

    public void attach(V view) {
        this.view = view;
    }

    public void addSubscription(Subscription mSubscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(mSubscription);
    }

    ;

    public void onDestroy() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }
}
