package com.zhixin.com.jsoup.ui.douban.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.data.MovieDetailBean;

import java.util.List;

/**
 * Created by zhangstar on 2016/11/25.
 */

public class MovieDetailCastsAdapter extends BaseAdapter<MovieDetailBean.CastsBean> {

    public MovieDetailCastsAdapter(Context context, List<MovieDetailBean.CastsBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(BaseViewHolder holder, MovieDetailBean.CastsBean data) {
        ImageView imageView = holder.getView(R.id.moview_detail_recyclerview_item_imageview);
        Picasso.with(mContext).load(data.getAvatars().getMedium()).error(R.mipmap.ic_launcher).config(Bitmap.Config.RGB_565).into(imageView);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.movie_detail_recycler_item;
    }
}
