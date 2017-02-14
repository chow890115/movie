package com.zhixin.com.jsoup.ui.douban.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.tools.PicassoUtil;

import java.util.List;

/**
 * Created by zhangstar on 2017/2/10.
 */

public class MovieDetailPhotosAdapter extends BaseAdapter<String> {
    public MovieDetailPhotosAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(BaseViewHolder holder, String data) {
        ImageView iv = holder.getView(R.id.movie_detail_photos_iv);
        PicassoUtil.intoImageView(mContext, data, iv);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_movie_detail_photos_recyclerview;
    }
}
