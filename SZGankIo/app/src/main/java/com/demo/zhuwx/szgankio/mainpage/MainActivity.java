package com.demo.zhuwx.szgankio.mainpage;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.demo.zhuwx.szgankio.R;
import com.demo.zhuwx.szgankio.basic.BaseActivity;

public class MainActivity extends BaseActivity implements MainPageContract.View{


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

        mAdapter = new MainPageViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.tab_title));
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        return true;
    }
}
