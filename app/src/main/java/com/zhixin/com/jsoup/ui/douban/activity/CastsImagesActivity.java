package com.zhixin.com.jsoup.ui.douban.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.activity.BaseActivity;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.ui.douban.adapter.CastsImagesPageAdapter;

import java.util.List;

import butterknife.BindView;

import static com.zhixin.com.jsoup.R.id.viewpager;

public class CastsImagesActivity extends BaseActivity {

    @BindView(viewpager)
    ViewPager mViewpager;
    @BindView(R.id.count_tv)
    TextView mCountTv;
    @BindView(R.id.normal_toolBar)
    Toolbar mNormalToolBar;
    private int position;
    List<MovieDetailBean.CastsBean> castsList;

    @Override
    public void initData() {
        castsList = (List<MovieDetailBean.CastsBean>) getIntent().getExtras().getSerializable("casts");
        int position = getIntent().getExtras().getInt("position");
        mCountTv.setText(String.format("%s/%s", position + 1, castsList.size()));

        CastsImagesPageAdapter adapter = new CastsImagesPageAdapter(CastsImagesActivity.this, castsList);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pagePosition) {
                CastsImagesActivity.this.position = pagePosition;
                mCountTv.setText(String.format("%s/%s", pagePosition + 1, castsList.size()));
                getSupportActionBar().setTitle(castsList.get(pagePosition).getName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewpager.setCurrentItem(position);
    }

    @Override
    public void initView() {
        setSupportActionBar(mNormalToolBar);
        getSupportActionBar().setTitle("演员");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewpager.setOffscreenPageLimit(3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_casts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.activity_casts_detail:
                if (castsList != null && castsList.size() != 0) {
                    Intent intent = new Intent(CastsImagesActivity.this, FilmActivity.class);
                    MovieDetailBean.CastsBean casts = castsList.get(position);
                    intent.putExtra("casts", casts);
                    startActivity(intent);
                }
                break;
        }
        return true;
    }

    @Override
    public int initLayout() {
        return R.layout.activity_casts_images;
    }

}
