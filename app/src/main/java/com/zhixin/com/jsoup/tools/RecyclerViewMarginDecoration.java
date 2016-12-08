package com.zhixin.com.jsoup.tools;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhangstar on 2016/12/7.
 */

public class RecyclerViewMarginDecoration extends RecyclerView.ItemDecoration {
    int space;

    public RecyclerViewMarginDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.top = space;
        outRect.right = space;
        outRect.bottom = space;
    }
}
