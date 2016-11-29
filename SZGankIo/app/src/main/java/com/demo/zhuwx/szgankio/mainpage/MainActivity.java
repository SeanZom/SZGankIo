package com.demo.zhuwx.szgankio.mainpage;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.demo.zhuwx.szgankio.R;
import com.demo.zhuwx.szgankio.base.BaseActivity;
import com.demo.zhuwx.szgankio.mainpage.ui.TopicListFragment;
import com.demo.zhuwx.szgankio.mainpage.ui.adapter.MainPageViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private MainPageViewPagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        if (mViewPager.getAdapter() == null) {
            List<TopicListFragment> fragments = new ArrayList<>();
            for (int i = 0; i < 3; i ++) {
                fragments.add(TopicListFragment.newInstance(i));
            }
            mAdapter = new MainPageViewPagerAdapter(getSupportFragmentManager(), fragments, getResources().getStringArray(R.array.tab_title));
            mViewPager.setAdapter(mAdapter);
        }
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setCurrentItem(0, false);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setScrollPosition(0, 0, true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        return true;
    }
}
