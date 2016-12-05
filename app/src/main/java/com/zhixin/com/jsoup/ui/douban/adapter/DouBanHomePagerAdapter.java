package com.zhixin.com.jsoup.ui.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhixin.com.jsoup.base.fragment.BaseFragment;

import java.util.List;

/**
 * Created by zhangstar on 2016/11/25.
 */

public class DouBanHomePagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;
    private FragmentManager mFragmentManager;
    private List<String> titles;

    public DouBanHomePagerAdapter(FragmentManager fm) {
        super(fm);

        mFragmentManager = fm;
    }

    public void setData(List<BaseFragment> fragmentList, List<String> titles) {
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
