package com.zhixin.com.jsoup.ui.douban.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.ui.douban.entity.Douban250SubjectsBean;

import java.util.List;

/**
 * Created by zhangwenxing on 2016/11/10.
 */

public class DoubanMoview250Adapter extends BaseAdapter<Douban250SubjectsBean> {


    public DoubanMoview250Adapter(Context context, List<Douban250SubjectsBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(BaseViewHolder holder, Douban250SubjectsBean data) {
        ImageView imageView = holder.getView(R.id.movie250_recycler_item_title_iv);
        TextView tv = holder.getView(R.id.movie250_recycler_item_title_tv);
        RatingBar ratingBar = holder.getView(R.id.moview_250_ratingbar);
        ratingBar.setRating(data.getRating().getAverage() / 2);
        tv.setText(data.getTitle());
        Picasso.with(mContext).load(data.getImages().getLarge()).error(R.mipmap.image_error).into(imageView);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.movie250_recycler_item;
    }

}
