package com.zhixin.com.jsoup.ui.douban.activity;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.activity.BaseActivity;
import com.zhixin.com.jsoup.ui.douban.adapter.CastsImagesPageAdapter;

import java.util.List;

import butterknife.BindView;

import static com.zhixin.com.jsoup.R.id.viewpager;

public class CastsImagesActivity extends BaseActivity {

    @BindView(viewpager)
    ViewPager mViewpager;
    @BindView(R.id.count_tv)
    TextView mCountTv;

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        int position = getIntent().getExtras().getInt("position");
        final List<String> images = getIntent().getStringArrayListExtra("images");
        mCountTv.setText(String.format("%s/%s", position + 1, images.size()));
        CastsImagesPageAdapter adapter = new CastsImagesPageAdapter(CastsImagesActivity.this, images);
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pagePosition) {
                mCountTv.setText(String.format("%s/%s", pagePosition + 1, images.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewpager.setCurrentItem(position);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_casts_images;
    }

}
