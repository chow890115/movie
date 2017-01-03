package com.zhixin.com.jsoup.ui.douban.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.ui.douban.entity.Subject;

import java.util.List;

/**
 * Created by zhangstar on 2016/12/30.
 */

public class TheatersAdapter extends BaseAdapter<Subject> {

    public TheatersAdapter(Context context, List<Subject> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(BaseViewHolder holder, Subject data) {
        TextView title = holder.getView(R.id.item_theater_textView);
        ImageView image = holder.getView(R.id.item_theaters_imageView);
        title.setText(data.getTitle());
        if (data.getImages().getLarge() != null && !data.getImages().getLarge().equals(""))
            Picasso.with(mContext).load(data.getImages().getLarge()).error(R.mipmap.image_error).placeholder(R.mipmap.image_loading)
                    .config(Bitmap.Config.RGB_565).into(image);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_theaters;
    }
}
