package com.zhixin.com.jsoup.ui.douban.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.base.fragment.BaseMvpFrgament;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.tools.RecyclerViewMarginDecoration;
import com.zhixin.com.jsoup.ui.douban.activity.MovieDetailActivity;
import com.zhixin.com.jsoup.ui.douban.adapter.TheatersAdapter;
import com.zhixin.com.jsoup.ui.douban.entity.Movie;
import com.zhixin.com.jsoup.ui.douban.entity.Subject;
import com.zhixin.com.jsoup.ui.douban.presenter.TheatersPresenter;
import com.zhixin.com.jsoup.ui.douban.view.ITheatersView;

import butterknife.BindView;

/**
 * Created by zhangstar on 2016/12/30.
 */

public class TheatersFragment extends BaseMvpFrgament<ITheatersView, TheatersPresenter> implements ITheatersView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    int start = 0;
    boolean isLoadMore = false;
    private TheatersAdapter adpter;

    @Override
    public TheatersPresenter initPresenter() {
        return new TheatersPresenter();
    }

    @Override
    public int initFragmantLayout() {
        return R.layout.fragment_theaters;
    }

    @Override
    protected void initView(View view) {
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setRefreshing(true);
        initData();
    }

    @Override
    protected void initData() {
        mPresenter.getTheaters(start);
    }

    @Override
    public void onError() {
        adpter.setLoadEndView(R.layout.load_failed_layout);
    }

    @Override
    public void onSuccess(Movie theaterses) {
        if (theaterses.getSubjects().isEmpty()) {
            return;
        }
        if (isLoadMore) {
            adpter.isLoading(false);
            adpter.setLoadMoreData(theaterses.getSubjects());
        } else {
            if (mRecyclerView.getAdapter() == null) {
                setNewAdapter(theaterses);
            } else {
                adpter.setNewData(theaterses.getSubjects());
            }
        }
        mSwipeRefresh.setRefreshing(false);
        start += theaterses.getCount();
        if (start >= theaterses.getTotal()) {
            adpter.isEnd(true);
            adpter.setLoadEndView(R.layout.load_end_layout);
        }

    }

    public void setNewAdapter(Movie theaterses) {
        GridLayoutManager layout = new GridLayoutManager(context, 3);
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.addItemDecoration(new RecyclerViewMarginDecoration(16));
        adpter = new TheatersAdapter(context, theaterses.getSubjects(), true);
        adpter.setLoadingView(R.layout.recycler_foot_item);
        adpter.setOnLoadMoreListener(new BaseAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                isLoadMore = true;
                adpter.isLoading(true);
                initData();
            }
        });
        adpter.setOnItemClickListener(new BaseAdapter.OnItemClickListeners<Subject>() {
            @Override
            public void onItemClick(BaseViewHolder viewHolder, Subject data, int position) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(GlobalParams.MOVIE_DETAIL, data.getId());
                context.startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adpter);
    }

    @Override
    public void onRefresh() {
        if (adpter != null)
            adpter.isEnd(false);
        start = 0;
        isLoadMore = false;
        initData();
    }
}
