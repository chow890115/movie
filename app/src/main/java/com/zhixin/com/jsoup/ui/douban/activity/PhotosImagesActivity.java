package com.zhixin.com.jsoup.ui.douban.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.activity.BaseActivity;
import com.zhixin.com.jsoup.ui.douban.adapter.PhotosImagePageAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangstar on 2017/2/10.
 */

public class PhotosImagesActivity extends BaseActivity {
    @BindView(R.id.normal_toolBar)
    Toolbar mNormalToolBar;
    @BindView(R.id.photos_viewpager)
    ViewPager mPhotosViewpager;
    @BindView(R.id.photos_count_tv)
    TextView mPhotosCountTv;
    int position;
    List<String> images;

    @Override
    public void initData() {
        position = this.getIntent().getExtras().getInt("position", 0);
        images = this.getIntent().getStringArrayListExtra("photos");
        mPhotosCountTv.setText(String.format("%s/%s", position + 1, images.size()));
        PhotosImagePageAdapter adapter = new PhotosImagePageAdapter(this, images);
        mPhotosViewpager.setAdapter(adapter);
        mPhotosViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pagePosition) {
                mPhotosCountTv.setText(String.format("%s/%s", pagePosition + 1, images.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPhotosViewpager.setCurrentItem(position);
    }

    @Override
    public void initView() {
        setSupportActionBar(mNormalToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("电影剧照");
        mPhotosViewpager.setOffscreenPageLimit(3);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_photos_images;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}
