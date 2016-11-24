package com.zhixin.com.jsoup.ui.douban.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.adapter.DoubanMoview250Adapter;
import com.zhixin.com.jsoup.base.adapter.BaseAdapter;
import com.zhixin.com.jsoup.base.adapter.BaseViewHolder;
import com.zhixin.com.jsoup.base.fragment.BaseMvpFrgament;
import com.zhixin.com.jsoup.data.Douban250Bean;
import com.zhixin.com.jsoup.data.Douban250SubjectsBean;
import com.zhixin.com.jsoup.tools.GlobalParams;
import com.zhixin.com.jsoup.ui.douban.activity.MovieDetailActivity;
import com.zhixin.com.jsoup.ui.douban.presenter.DouBan250Presenter;
import com.zhixin.com.jsoup.ui.douban.scroll.FastGridLayoutManager;
import com.zhixin.com.jsoup.ui.douban.view.DoubanView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangwenxing on 2016/11/9.
 */

public class DouBanMovie250Fragment extends BaseMvpFrgament<DoubanView, DouBan250Presenter> implements DoubanView<Douban250Bean>, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.douban_250_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.douban_250_recyclerview)
    RecyclerView mRecyclerView;
    private DoubanMoview250Adapter adapter;
    private boolean isLoadMore;
    private int start = 0;

    @OnClick(R.id.fab)
    void fabonclick() {
        if (mRecyclerView != null && mRecyclerView.getAdapter() != null) {
            mRecyclerView.smoothScrollToPosition(0);
//            mRecyclerView.scrollTo(0, 0);
        }
    }

    @Override
    public int initFragmantLayout() {
        return R.layout.fragment_douban;
    }

    @Override
    protected void initView(View view) {
        initListen();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                initData();
            }
        });
    }

    private void initListen() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.getDoubanMovie250(start);
    }

    @Override
    public void onSuccess(Douban250Bean bean) {
        if (isLoadMore) {
            adapter.isLoading(false);
            adapter.setLoadMoreData(bean.getSubjects());
            if (bean.getSubjects().size() == 0 || bean.getSubjects() == null) {
                //数据请求完毕 已加载全部 在这设置加载完成的画面
            } else {
                start += bean.getCount();
            }
        } else {
            //刷新请求
            if (adapter == null) {
                initRecyclerViewLayoutManager();
                newAdapter(bean.getSubjects());
                mRecyclerView.setAdapter(adapter);
            } else {
                adapter.setNewData(bean.getSubjects());
            }
            mSwipeRefreshLayout.setRefreshing(false);
            start += bean.getCount();
        }
    }

    private void newAdapter(List<Douban250SubjectsBean> list) {
        adapter = new DoubanMoview250Adapter(context, list, true);
        adapter.setLoadingView(R.layout.recycler_foot_item);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListeners<Douban250SubjectsBean>() {
            @Override
            public void onItemClick(BaseViewHolder viewHolder, Douban250SubjectsBean data) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(GlobalParams.MOVIE_DETAIL, data.getId());
                context.startActivity(intent);
            }
        });
        adapter.setOnLoadMoreListener(new BaseAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                isLoadMore = true;
                adapter.isLoading(true);
                initData();
            }
        });
    }


    private void initRecyclerViewLayoutManager() {
        final FastGridLayoutManager gridLayoutManager = new FastGridLayoutManager(context, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    mFab.show();
                }
                if (dy < 0)
                    mFab.hide();
            }
        });
    }

    @Override
    public void onError() {
        if (isLoadMore) {

        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected DouBan250Presenter initPresenter() {
        return new DouBan250Presenter();
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        start = 0;
        initData();
    }
}
