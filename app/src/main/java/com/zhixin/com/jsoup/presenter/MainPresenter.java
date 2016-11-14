package com.zhixin.com.jsoup.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.model.MainModel;
import com.zhixin.com.jsoup.model.impl.MainModelImpl;

import rx.Subscriber;

/**
 * model请求数据返回给P
 * Created by zhangwenxing on 2016/10/28.
 */

public class MainPresenter extends BasePresenterImpl{
    private MainModel model;

    public MainPresenter() {
        model = new MainModelImpl();
    }

    public void getFQPhotoDataList(String photoUrl) {
        mSubscription = model.requestPhoto(photoUrl).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
//                view.onSuccess();
            }
        });
    }


}
