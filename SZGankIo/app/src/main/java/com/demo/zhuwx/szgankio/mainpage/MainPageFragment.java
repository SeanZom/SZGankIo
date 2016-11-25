package com.demo.zhuwx.szgankio.mainpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.demo.zhuwx.szgankio.R;
import com.demo.zhuwx.szgankio.basic.BaseFragment;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class MainPageFragment extends BaseFragment implements MainPageContract.View, SwipeRefreshLayout.OnRefreshListener{

    public static final String TOPIC_ANDROID = "Android";
    public static final String TOPIC_IOS = "iOS";
    public static final String TOPIC_FRONT_ENDS = "前端";
    public static final String TOPIC_TYPE = "topic_type";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private MainPagePresenter mPresenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getHoldingActivity(), LinearLayout.HORIZONTAL));
        mRecyclerView.getLayoutManager().setAutoMeasureEnabled(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_page;
    }

    public static MainPageFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(TOPIC_TYPE, position);
        MainPageFragment mainPageFragment = new MainPageFragment();
        mainPageFragment.setArguments(args);
        return mainPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mPresenter = new MainPagePresenter(getArguments().getInt(TOPIC_TYPE));
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onRefresh() {

    }
}
