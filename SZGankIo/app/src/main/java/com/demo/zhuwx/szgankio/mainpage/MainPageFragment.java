package com.demo.zhuwx.szgankio.mainpage;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.demo.zhuwx.szgankio.R;
import com.demo.zhuwx.szgankio.basic.BaseFragment;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class MainPageFragment extends BaseFragment implements MainPageContract.View{



    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_page;
    }

    public static MainPageFragment newInstance() {
        return new MainPageFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);


    }
}
