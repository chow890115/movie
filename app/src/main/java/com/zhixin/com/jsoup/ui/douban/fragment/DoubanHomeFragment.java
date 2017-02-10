package com.zhixin.com.jsoup.ui.douban.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhixin.com.jsoup.R;
import com.zhixin.com.jsoup.base.fragment.BaseFragment;
import com.zhixin.com.jsoup.ui.douban.adapter.DouBanHomePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 豆瓣主页Frgament
 * Created by zhangstar on 2016/11/24.
 */

public class DoubanHomeFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private List<BaseFragment> fragmentList;

    @Override
    public int initFragmantLayout() {
        return R.layout.fragment_douban_home;
    }

    @Override
    protected void initView(View view) {
        fragmentList = new ArrayList<BaseFragment>();
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.setupWithViewPager(mViewpager);
        initData();
    }

    @Override
    protected void initData() {
        List<String> titles = new ArrayList<>();
        fragmentList.add(new TheatersFragment());
        fragmentList.add(new CommingSoonFragment());
        fragmentList.add(new DouBanMovie250Fragment());
        titles.add("正在热映");
        titles.add("即将上映");
        titles.add("Top250");
        DouBanHomePagerAdapter adpter = new DouBanHomePagerAdapter(getFragmentManager());
        adpter.setData(fragmentList, titles);
        mViewpager.setAdapter(adpter);
        mViewpager.setOffscreenPageLimit(fragmentList.size());
    }

}
