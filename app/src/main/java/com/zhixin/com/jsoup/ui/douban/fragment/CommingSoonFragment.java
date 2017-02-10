package com.zhixin.com.jsoup.ui.douban.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.base.fragment.BaseMvpFrgament;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.tools.RecyclerViewMarginDecoration;
import com.zhixin.com.jsoup.ui.douban.activity.MovieDetailActivity;
import com.zhixin.com.jsoup.ui.douban.adapter.CommingSoonAdapter;
import com.zhixin.com.jsoup.ui.douban.entity.Movie;
import com.zhixin.com.jsoup.ui.douban.entity.Subject;
import com.zhixin.com.jsoup.ui.douban.presenter.CommingSoonPresenter;
import com.zhixin.com.jsoup.ui.douban.view.ICommingSoonView;

import butterknife.BindView;

/**
 * 即将上映
 * Created by zhangstar on 2017/2/9.
 */

public class CommingSoonFragment extends BaseMvpFrgament<ICommingSoonView, CommingSoonPresenter> implements ICommingSoonView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    private int start;
    private boolean isLoadMore = false;

    private CommingSoonAdapter adapter;

    @Override
    protected CommingSoonPresenter initPresenter() {
        return new CommingSoonPresenter();
    }

    @Override
    public int initFragmantLayout() {
        return R.layout.fragment_commingsoon;
    }

    @Override
    protected void initView(View view) {
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setRefreshing(true);
        initData();
    }

    @Override
    protected void initData() {
        mPresenter.getCommingSoonData(start);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(Movie movie) {
        mSwipeRefresh.setRefreshing(false);
        if (movie.getSubjects().isEmpty())
            return;
        if (isLoadMore) {
            adapter.isLoading(false);
            adapter.setLoadMoreData(movie.getSubjects());
        } else if (mRecyclerView.getAdapter() == null) {
            adapter = new CommingSoonAdapter(context, movie.getSubjects(), true);
            setLayoutManagerInRecyclerview();
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(movie.getSubjects());
        }
        start += movie.getCount();
        if (start >= movie.getTotal()) {
            adapter.isEnd(true);
            adapter.setLoadEndView(R.layout.load_end_layout);
        }


    }

    public void setLayoutManagerInRecyclerview() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//可防止Item切换
        mRecyclerView.setLayoutManager(layoutManager);
        adapter.setLoadingView(R.layout.recycler_foot_item);
        mRecyclerView.addItemDecoration(new RecyclerViewMarginDecoration(10));
        adapter.setOnLoadMoreListener(new BaseAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                isLoadMore = true;
                adapter.isLoading(true);
                initData();
            }
        });
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListeners<Subject>() {
            @Override
            public void onItemClick(BaseViewHolder viewHolder, Subject data, int position) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(GlobalParams.MOVIE_DETAIL, data.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        if (adapter != null)
            adapter.isEnd(false);
        start = 0;
        initData();
    }
}
