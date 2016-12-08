package com.zhixin.com.jsoup.ui.douban.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.data.MovieDetailBean;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhangstar on 2016/12/3.
 */

public class CastsImagesPageAdapter extends PagerAdapter {
    private List<MovieDetailBean.CastsBean> images;
    private Context context;
    private SparseArray<View> views;


    public CastsImagesPageAdapter(Context context, List<MovieDetailBean.CastsBean> images) {
        this.images = images;
        this.context = context;
        views = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_vp_image, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_vp_image);
            final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView);
            Picasso.with(context)
                    .load(images.get(position).getAvatars().getLarge())
                    .placeholder(R.mipmap.image_loading)
                    .config(Bitmap.Config.RGB_565)
                    .error(R.mipmap.image_error).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    photoViewAttacher.update();
                }

                @Override
                public void onError() {

                }
            });
            photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    Activity activity = (Activity) context;
                    activity.finish();
                }
            });
            views.put(position, view);

        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
