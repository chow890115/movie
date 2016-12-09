package com.zhixin.com.jsoup.ui.douban.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView nameTv = holder.getView(R.id.moview_detail_recyclerview_item_tv);
        if (data.getAvatars().getLarge() != null && !data.getAvatars().getLarge().equals(""))
            Picasso.with(mContext).load(data.getAvatars().getLarge()).placeholder(R.mipmap.image_loading).error(R.mipmap.image_error).config(Bitmap.Config.RGB_565).into(imageView);
        else
            imageView.setImageResource(R.mipmap.image_error);
        nameTv.setText(data.getName());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.movie_detail_recycler_item;
    }
}
