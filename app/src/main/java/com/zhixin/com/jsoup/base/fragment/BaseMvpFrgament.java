package com.zhixin.com.jsoup.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;

/**
 * Created by zhangwenxing on 2016/10/28.
 */

public abstract class BaseMvpFrgament<V, P extends BasePresenterImpl> extends BaseFragment {
    public P mPresenter;
    protected abstract P initPresenter();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
