package com.demo.zhuwx.szgankio.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected abstract int getLayoutId();

    protected abstract int getToolbarId();

    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Toolbar toolbar = (Toolbar) findViewById(getToolbarId());
        setSupportActionBar(toolbar);

        initView();
    }
}
