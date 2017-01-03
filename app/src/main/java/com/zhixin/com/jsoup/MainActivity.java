package com.zhixin.com.jsoup;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.zhixin.com.jsoup.base.activity.BaseActivity;
import com.zhixin.com.jsoup.base.fragment.BaseFragment;
import com.zhixin.com.jsoup.data.FQPhotoBean;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.ui.douban.fragment.DoubanHomeFragment;
import com.zhixin.com.jsoup.ui.douban.fragment.PersonalFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private Map<String, Fragment> mFragmentMap;
    private String currentType;


    @Override
    public void initData() {
//        final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
//        mManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        android.util.Log.e("main", "main" + android.os.Process.myTid());
//        final FileDownloadCallBack callBack = new FileDownloadCallBack() {
//            @Override
//            public void updateProgress(long total, long progress) {
//                int totalInt = new Long(total).intValue();
//                int progressInt = new Long(progress).intValue();
//                android.util.Log.e("test", "updateProgress" + (int) ((double) progress / total * 100) + "%--totalInt--" + total);
//                android.util.Log.e("main", "FileDownloadCallBack" + android.os.Process.myTid());
//                android.util.Log.e("test", "updateProgress" + (int) (double) progress / total * 100);
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
//                builder.setSmallIcon(R.mipmap.ic_launcher);
//                //禁止用户点击删除按钮删除
//                builder.setAutoCancel(false);
//                //禁止滑动删除
//                builder.setOngoing(true);
//                //取消右上角的时间显示
//                builder.setShowWhen(false);
//                builder.setContentTitle("下载中..." + (int) ((double) progress / total * 100) + "%");
//                builder.setProgress(100, (int) ((double) progress / total * 100), false);
//                builder.setContentInfo((int) ((double) progress / total * 100) + "%");
//                builder.setOngoing(true);
//                builder.setShowWhen(false);
//                Notification notification = builder.build();
//                mManager.notify(0, notification);
//            }
//        };
//        callBack.setPathAndName((Tools.checkSDCard() ? Tools.getExternalStoragePath() + "/Download/" : Environment.getRootDirectory() + "/Download/"), "test.apk");
//
//        RetrofitUtil.getApiService(GlobalParams.ZHIXIN_URL).downLoad().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).doOnNext(new Action1<ResponseBody>() {
//            @Override
//            public void call(ResponseBody responseBody) {
//                android.util.Log.e("test", "doOnNext");
//                callBack.saveFile(responseBody);
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                android.util.Log.e("test", "onError");
//                RetrofitUtil.resolveError(e);
//            }
//
//            @Override
//            public void onNext(ResponseBody responseBody) {
//                android.util.Log.e("test", "onnext");
//            }
//        });
    }

    //爬取数据
    private void initJsoup() {
        final List<FQPhotoBean> beanList = new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<FQPhotoBean>>() {
            @Override
            public void call(Subscriber<? super List<FQPhotoBean>> subscriber) {
                try {
                    Document document = Jsoup.connect(GlobalParams.BASE_URL).get();
                    Elements listDiv = document.getElementsByAttributeValue("class", "portfolio-slideshow");
                    for (Element element : listDiv) {
                        Elements a = element.getElementsByTag("a");
                        Elements imgUrl = element.getElementsByTag("img");
                        FQPhotoBean bean = new FQPhotoBean();
                        String href = a.attr("href");
                        String src = imgUrl.attr("src");
                        String title = imgUrl.attr("alt");
                        bean.setHref(href);
                        bean.setSrc(src);
                        bean.setTitle(title);
                        beanList.add(bean);
                    }
                    subscriber.onNext(beanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FQPhotoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<FQPhotoBean> list) {

            }
        });
    }

    @Override
    public void initView() {
        setSupportActionBar(toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolBar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View headView = mNavigationView.getHeaderView(0);
        ImageView imageView = (ImageView) headView.findViewById(R.id.nav_head_icon);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.navigation_douban_movie);
        mFragmentMap = new HashMap<>();
        beginReplace(new DoubanHomeFragment(), "豆瓣");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.nav_head_icon:
                drawerLayout.closeDrawers();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_douban_movie:
                beginReplace(new DoubanHomeFragment(), "豆瓣");
                break;

            case R.id.navigation_item_intimity:
                beginReplace(new PersonalFragment(), "个人");
                break;

            case R.id.navigation_item_system:

                break;

            case R.id.navigation_item_about:

                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }

    // 使用hide show来切换Frgament
    public void beginReplace(BaseFragment fragment, String type) {
        if (!type.equals(currentType)) {
            replaceFragment(fragment, type, currentType);
            currentType = type;
        }
    }

    public void replaceFragment(BaseFragment fragment, String type, String lastType) {
        if (mFragmentMap.get(type) == null) {
            mFragmentMap.put(type, fragment);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_fragment_container, fragment);
            ft.commit();
        }
        if (mFragmentMap.get(lastType) != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(mFragmentMap.get(lastType));
            ft.show(mFragmentMap.get(type));
            ft.commit();
        }
    }

}
