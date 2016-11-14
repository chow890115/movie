package com.zhixin.com.jsoup.ui.douban.view;

import com.zhixin.com.jsoup.base.view.IBaseView;
import com.zhixin.com.jsoup.data.Douban250Bean;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public interface DoubanView extends IBaseView {
    void onSuccess(Douban250Bean bean);
}
