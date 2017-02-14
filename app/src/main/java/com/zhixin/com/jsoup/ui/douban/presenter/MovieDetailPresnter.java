package com.zhixin.com.jsoup.ui.douban.presenter;

import com.zhixin.com.jsoup.base.presenter.BasePresenterImpl;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.rx.SubscribeCall;
import com.zhixin.com.jsoup.rx.TransformerUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhangstar on 2016/11/14.
 */

public class MovieDetailPresnter extends BasePresenterImpl<MovieDetailView<MovieDetailBean>> {
    private MovieDetailModel model;

    //https://img3.doubanio.com/view/photo/photo/public/p2402466512.jpg
    //https://movie.douban.com/subject/2083735/trailer
    //https://movie.douban.com/subject/20471852/photos?type=S
    public MovieDetailPresnter() {
        model = new MovieDetailModel();
    }

    public void getDetailModel(int id) {
        jsoupMovie(id);
        addSubscription(model.getMovieDetail(id)
                .subscribe(new SubscribeCall<>(view)));
    }


    public void jsoupMovie(final int id) {

        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> photos = new ArrayList<String>();
                try {
                    Document document = Jsoup.connect("https://movie.douban.com/subject/" + id + "/photos?type=S&start=0&sortby=vote&size=a&subtype=o").get();
                    Elements photosTag = document.getElementsByAttributeValue("class", "poster-col4 clearfix");
                    Elements lis = photosTag.select("li");
                    for (int i = 0; i < lis.size(); i++) {
                        Element li = lis.get(i);
                        String id = li.attr("data-id");
                        if (id != null && !id.equals("")) {
                            String src = "https://img3.doubanio.com/view/photo/photo/public/p" + id + ".jpg";
                            photos.add(src);
                        }
                    }
                    subscriber.onNext(photos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).compose(TransformerUtil.<List<String>>SchedulersCompose()).subscribe(new SubscribeCall<>(view, new SubscribeCall.SimpleSubscribe<List<String>>() {
            @Override
            public void onNext(List<String> data) {
                view.setPhotos(data);
            }
        }));
    }

}
