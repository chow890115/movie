package com.zhixin.com.jsoup.ui.douban.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.activity.BaseMvpActivity;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.ui.douban.presenter.MovieDetailPresnter;
import com.zhixin.com.jsoup.ui.douban.presenter.MovieDetailView;

import butterknife.BindView;

public class MovieDetailActivity extends BaseMvpActivity<MovieDetailView, MovieDetailPresnter> implements MovieDetailView {

    @BindView(R.id.head_iv)
    ImageView mHeadIv;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.collaps_tool_bar)
    CollapsingToolbarLayout mCollapsToolBar;

    @Override
    public void initData() {
        int id = getIntent().getExtras().getInt(GlobalParams.MOVIE_DETAIL, 0);
        presenter.getDetailModel(id);
    }

    @Override
    public void initView() {
        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_movie_detail;
    }


    @Override
    public void onError() {

    }

    @Override
    protected MovieDetailPresnter initPresenter() {
        return new MovieDetailPresnter();
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


    @Override
    public void onSuccess(MovieDetailBean bean) {
        mCollapsToolBar.setTitle(bean.getTitle());
        Picasso.with(this).load(bean.getImages().getLarge()).error(R.mipmap.test).into(mHeadIv);
    }
}
