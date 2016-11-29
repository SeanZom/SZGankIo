package com.demo.zhuwx.szgankio.mainpage.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demo.zhuwx.szgankio.mainpage.ui.TopicListFragment;

import java.util.List;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class MainPageViewPagerAdapter extends FragmentPagerAdapter{


    private List<TopicListFragment> mFragments;
    private String[] mTabTitles;

    public MainPageViewPagerAdapter(FragmentManager fm, List<TopicListFragment> fragments, String[]tabTitles) {
        super(fm);
        mFragments = fragments;
        this.mTabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }

}
