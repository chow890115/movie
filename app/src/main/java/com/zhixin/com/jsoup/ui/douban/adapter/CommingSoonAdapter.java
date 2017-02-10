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
 * Created by zhangstar on 2017/2/9.
 */

public class CommingSoonAdapter extends BaseAdapter<Subject> {

    public CommingSoonAdapter(Context context, List<Subject> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(BaseViewHolder holder, Subject data) {
        ImageView iv = holder.getView(R.id.commingsoon_item_iv);
        TextView tv = holder.getView(R.id.item_commingsoon_textView);
        tv.setText(data.getTitle());
        if (data.getImages().getLarge() != null && !data.getImages().getLarge().equals(""))
            Picasso.with(mContext).load(data.getImages().getLarge()).config(Bitmap.Config.RGB_565)
                    .error(R.mipmap.image_error).placeholder(R.mipmap.image_loading).into(iv);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_commingsoon_recycler;
    }
}
