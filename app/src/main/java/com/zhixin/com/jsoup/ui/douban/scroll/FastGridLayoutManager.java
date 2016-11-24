package com.zhixin.com.jsoup.ui.douban.scroll;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zhangstar on 2016/11/24.
 */

public class FastGridLayoutManager extends GridLayoutManager {

    public FastGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FastGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public FastGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            protected int calculateTimeForScrolling(int dx) {
                if (dx > 2000) {
                    dx = 1500;
                }
                return super.calculateTimeForScrolling(dx);
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
//        super.smoothScrollToPosition(recyclerView, state, position);
    }
}
