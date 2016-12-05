package com.zhixin.com.jsoup.ui.douban.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.activity.BaseMvpActivity;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.ui.douban.adapter.MovieDetailCastsAdapter;
import com.zhixin.com.jsoup.ui.douban.presenter.MovieDetailPresnter;
import com.zhixin.com.jsoup.ui.douban.presenter.MovieDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MovieDetailActivity extends BaseMvpActivity<MovieDetailView, MovieDetailPresnter> implements MovieDetailView<MovieDetailBean> {

    @BindView(R.id.head_iv)
    ImageView mHeadIv;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.collaps_tool_bar)
    CollapsingToolbarLayout mCollapsToolBar;
    @BindView(R.id.moview_detail_info_tv)
    TextView mMoviewDetailInfoTv;
    @BindView(R.id.moview_detail_ratingbar)
    RatingBar mMoviewDetailRatingbar;
    @BindView(R.id.moview_rating_tv)
    TextView mMoviewRatingTv;
    @BindView(R.id.moview_detail_rating_count_tv)
    TextView mMoviewDetailRatingCountTv;
    @BindView(R.id.activity_movie_detail)
    CoordinatorLayout mActivityMovieDetail;
    @BindView(R.id.horizontal_recyclerview)
    RecyclerView mHorizontalRecyclerview;

    @Override
    public void initData() {
        int id = getIntent().getExtras().getInt(GlobalParams.MOVIE_DETAIL, 0);
        presenter.getDetailModel(id);
    }

    @Override
    public void initView() {
        initToolBar();
        mHorizontalRecyclerview.setNestedScrollingEnabled(false);
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
    public void onSuccess(final MovieDetailBean bean) {
        mCollapsToolBar.setTitle(bean.getTitle());
        Picasso.with(this).load(bean.getImages().getLarge()).error(R.mipmap.image_error).into(mHeadIv);
        setMovieDetailInfo(bean);
        mMoviewRatingTv.setText(String.format("%s分", bean.getRating().getAverage()));
        mMoviewDetailRatingbar.setRating((float) (bean.getRating().getAverage() / 2));
        mMoviewDetailRatingCountTv.setText(String.format("%s人评分", bean.getRatings_count()));
        mHorizontalRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MovieDetailCastsAdapter adapter = new MovieDetailCastsAdapter(this, bean.getCasts(), false);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListeners<MovieDetailBean.CastsBean>() {
            @Override
            public void onItemClick(BaseViewHolder viewHolder, MovieDetailBean.CastsBean data, int position) {
                List<MovieDetailBean.CastsBean> beanList = bean.getCasts();
                List<String> castsImages = new ArrayList<String>();
                for (MovieDetailBean.CastsBean castsBean : beanList) {
                    castsImages.add(castsBean.getAvatars().getLarge());
                }
                Intent intent = new Intent(MovieDetailActivity.this, CastsImagesActivity.class);
                intent.putStringArrayListExtra("images", (ArrayList<String>) castsImages);
                intent.putExtra("position", position);
                MovieDetailActivity.this.startActivity(intent);
            }
        });
        mHorizontalRecyclerview.setAdapter(adapter);
    }

    private void setMovieDetailInfo(MovieDetailBean bean) {
        StringBuffer sb = new StringBuffer();
        sb.append(bean.getYear());
        List<String> genres = bean.getGenres();
        for (String str : genres) {
            sb.append("/" + str);
        }
        sb.append("\n原名：" + bean.getOriginal_title());
        sb.append("\n制片国家/地区：" + bean.getCountries().get(0));
        sb.append("\n导演：" + bean.getDirectors().get(0).getName());

        mMoviewDetailInfoTv.setText(sb.toString());
    }

}
