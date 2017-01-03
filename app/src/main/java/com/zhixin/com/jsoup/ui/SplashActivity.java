package com.zhixin.com.jsoup.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhixin.com.jsoup.MainActivity;
import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.activity.BaseMvpActivity;
import com.zhixin.com.jsoup.data.SplashBean;
import com.zhixin.com.jsoup.tools.NetWorkUtil;

import butterknife.BindView;

public class SplashActivity extends BaseMvpActivity<ISplashView<SplashBean>, SplashPresenter> implements ISplashView<SplashBean> {

    @BindView(R.id.image_view)
    ImageView mImageView;

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void initData() {
        if (NetWorkUtil.isConnected(this)) {
            presenter.getSplashImage();
            return;
        }

        mImageView.setImageResource(R.mipmap.original_splash);
        goToMain();
    }

    @Override
    public void initView() {

    }

    @Override
    public int initLayout() {
        return R.layout.activity_splash;
    }


    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(SplashBean splashBean) {
        if (!splashBean.getImg().equals("") && splashBean.getImg() != null) {
            Picasso.with(this).load(splashBean.getImg()).error(R.mipmap.original_splash).config(Bitmap.Config.RGB_565).into(mImageView);
            goToMain();
        } else {
            mImageView.setImageResource(R.mipmap.original_splash);
        }
    }

    private void goToMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                finish();
            }
        }, 3000);
    }

}
