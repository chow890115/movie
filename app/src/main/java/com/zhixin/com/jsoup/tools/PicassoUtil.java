package com.zhixin.com.jsoup.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;

/**
 * Created by zhangstar on 2017/2/10.
 */

public class PicassoUtil {

    public static void intoImageView(Context context, String url, ImageView imageView) {
        if (url == null || url.equals(""))
            return;
        Picasso.with(context)
                .load(url)
                .error(R.mipmap.image_error)
                .placeholder(R.mipmap.image_loading)
                .config(Bitmap.Config.RGB_565)
                .into(imageView);

    }
}
