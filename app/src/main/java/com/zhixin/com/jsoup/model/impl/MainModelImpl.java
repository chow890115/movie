package com.zhixin.com.jsoup.model.impl;

import com.zhixin.com.jsoup.network.HttpUtil;
import com.zhixin.com.jsoup.model.MainModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhangwenxing on 2016/10/28.
 */

public class MainModelImpl implements MainModel{
    @Override
    public Observable<String> requestPhoto(final String url) {
        return    Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Document document = Jsoup.connect(url).get();
                    Elements listDiv = document.getElementsByAttributeValue("class", "portfolio-slideshow");
                    for (Element element : listDiv) {
                        Elements a = element.getElementsByTag("a");
                        Elements imgUrl = element.getElementsByTag("img");
                        String href = a.attr("href");
                        String src = imgUrl.attr("src");
                        String title = imgUrl.attr("alt");
                        subscriber.onNext(title);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).compose(HttpUtil.<String>composeResponse());
    }
}
