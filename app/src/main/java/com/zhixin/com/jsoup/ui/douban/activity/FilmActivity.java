package com.zhixin.com.jsoup.ui.douban.activity;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.activity.BaseMvpActivity;
import com.zhixin.com.jsoup.data.MovieDetailBean;
import com.zhixin.com.jsoup.ui.douban.entity.FilmBean;
import com.zhixin.com.jsoup.ui.douban.presenter.FilmPresenter;
import com.zhixin.com.jsoup.ui.douban.view.IFilmView;

import butterknife.BindView;

public class FilmActivity extends BaseMvpActivity<IFilmView<FilmBean>, FilmPresenter> implements IFilmView<FilmBean> {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.film_iv)
    ImageView mFilmIv;
    @BindView(R.id.file_info_tv)
    TextView mFileInfoTv;

    @Override
    protected FilmPresenter initPresenter() {
        return new FilmPresenter();
    }

    @Override
    public void initData() {
        MovieDetailBean.CastsBean bean = (MovieDetailBean.CastsBean) getIntent().getExtras().getSerializable("casts");
        getSupportActionBar().setTitle(bean.getName());
        presenter.getFilmDetailInfo(bean.getId());
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_film;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(FilmBean filmBean) {
        if (filmBean.getAvatars().getLarge() != null && !filmBean.getAvatars().equals(""))
            Picasso.with(this).load(filmBean.getAvatars().getLarge()).placeholder(R.mipmap.image_loading).error(R.mipmap.image_error).config(Bitmap.Config.RGB_565).into(mFilmIv);
        StringBuffer sb = new StringBuffer();
        sb.append("英文名：" + filmBean.getName_en() + "\n");
        sb.append("出生地:" + filmBean.getBorn_place() + "\n");
        mFileInfoTv.setText(sb.toString());
    }

}
