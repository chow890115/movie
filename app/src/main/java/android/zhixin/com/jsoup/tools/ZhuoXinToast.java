package android.zhixin.com.jsoup.tools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;


/**
 * Created by zhangwenxing on 2016/10/12.
 */

public class ZhuoXinToast {
    private WindowManager mWindowManagr;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private Handler mHandler = new Handler();
    private ObjectAnimator animEnd, animStart;
    private static ZhuoXinToast mInstance = null;


    class ToastRunnable implements Runnable {
        @Override
        public void run() {
            if (mView != null) {
                toastAnimator(mView);
                if (mView.getParent() != null) {
                    mWindowManagr.removeView(mView);
                }
            }
            mWindowManagr.addView(mView, mLayoutParams);
        }
    }


    class ToastEnd implements Runnable {

        @Override
        public void run() {
            animEnd.start();
        }
    }

    ToastRunnable mToastRunnable = new ToastRunnable();
    ToastEnd mToastEnd = new ToastEnd();


    public static synchronized ZhuoXinToast getInstance() {
        if (mInstance == null) {
            mInstance = new ZhuoXinToast();
        }
        return mInstance;
    }

    private void initParams(Context context, String str) {
        if (mWindowManagr != null && mView != null) {
            mWindowManagr.removeView(mView);
            if (animEnd != null) {
                animStart.end();
                animEnd.end();
            }
        }
        mWindowManagr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mHandler.removeCallbacks(mToastEnd);
        mHandler.removeCallbacks(mToastRunnable);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        // 透明，不透明会出现重叠效果
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
//        mView = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
//        TextView toastView = (TextView) mView.findViewById(R.id.toast);
//        toastView.setText(str);
        // 位置属性
        mLayoutParams.gravity = Gravity.CENTER_HORIZONTAL + Gravity.BOTTOM;
        int px = dip2px(context, 50);
        mLayoutParams.y = px;
    }

    private void toastAnimator(View view) {
        animStart = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animEnd = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        animStart.setDuration(500);
        animEnd.setDuration(500);
        animStart.start();
        animStart.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHandler.postDelayed(mToastEnd, 500);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animEnd.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                hide();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void show(String str) {
//        initParams(BaseApplication.getInstance(), str);
        mHandler.post(mToastRunnable);
    }

    public void hide() {
        if (mView != null) {
            if (mView.getParent() != null) {
                mWindowManagr.removeView(mView);
                mView = null;
                mWindowManagr = null;
                mHandler.removeCallbacks(mToastEnd);
                mHandler.removeCallbacks(mToastRunnable);
            }
        }
    }

    /**
     * dp2px
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px2dp
     */
    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
