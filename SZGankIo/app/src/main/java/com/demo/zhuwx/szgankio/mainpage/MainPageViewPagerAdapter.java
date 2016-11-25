package com.demo.zhuwx.szgankio.mainpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class MainPageViewPagerAdapter extends FragmentPagerAdapter{


    private String[] mTabTitles;

    public MainPageViewPagerAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.mTabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return MainPageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
