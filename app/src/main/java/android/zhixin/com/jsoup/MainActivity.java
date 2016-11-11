package android.zhixin.com.jsoup;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.zhixin.com.jsoup.base.activity.BaseActivity;
import android.zhixin.com.jsoup.base.fragment.BaseFragment;
import android.zhixin.com.jsoup.data.FQPhotoBean;
import android.zhixin.com.jsoup.tools.GlobalParams;
import android.zhixin.com.jsoup.ui.douban.DouBanMovie250Fragment;
import android.zhixin.com.jsoup.ui.douban.PersonalFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView mNavigationView;
    private Map<String, Fragment> mFragmentMap;
    private String currentType;

    @Override
    public void initData() {

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
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headView = mNavigationView.getHeaderView(0);
        ImageView imageView = (ImageView) headView.findViewById(R.id.nav_head_icon);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.navigation_douban_movie);
        mFragmentMap = new HashMap<>();
        beginReplace(new DouBanMovie250Fragment(), "豆瓣");
    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigtion_menu, menu);
        return true;
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
                beginReplace(new DouBanMovie250Fragment(), "豆瓣");
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
