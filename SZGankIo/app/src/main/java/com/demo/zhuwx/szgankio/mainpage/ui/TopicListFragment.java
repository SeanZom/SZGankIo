package com.demo.zhuwx.szgankio.mainpage.ui;

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
import com.demo.zhuwx.szgankio.base.BaseFragment;
import com.demo.zhuwx.szgankio.common.DataLoadType;
import com.demo.zhuwx.szgankio.data.TopicEntity;
import com.demo.zhuwx.szgankio.mainpage.TopicListContract;
import com.demo.zhuwx.szgankio.mainpage.presenter.TopicListPresenterImpl;
import com.demo.zhuwx.szgankio.mainpage.ui.adapter.TopicListAdapter;

import java.util.List;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class TopicListFragment extends BaseFragment<TopicListContract.Presenter> implements TopicListContract.View {

    public static final String TOPIC_ANDROID = "Android";
    public static final String TOPIC_IOS = "iOS";
    public static final String TOPIC_FRONT_ENDS = "前端";
    public static final String TOPIC_TYPE = "topic_type";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private TopicListContract.Presenter mPresenter;

    private TopicListAdapter mListAdapter;

    private String mCurrentTopic;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mCurrentTopic = getTopicStr(getArguments().getInt(TOPIC_TYPE));
        }
    }

    public static TopicListFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(TOPIC_TYPE, position);
        TopicListFragment topicListFragment = new TopicListFragment();
        topicListFragment.setArguments(args);
        return topicListFragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main_page;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getHoldingActivity(), LinearLayout.VERTICAL));
        mRecyclerView.getLayoutManager().setAutoMeasureEnabled(true);

        mPresenter = new TopicListPresenterImpl(this, mCurrentTopic);
    }


    private String getTopicStr(int position) {
        switch (position) {
            case 0:
                return TopicListFragment.TOPIC_ANDROID;
            case 1:
                return TopicListFragment.TOPIC_IOS;
            default:
                return TopicListFragment.TOPIC_FRONT_ENDS;
        }
    }

    @Override
    public void startLoading() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void endLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void updateTopicList(List<TopicEntity> data, String errorMsg, @DataLoadType.DataLoadTypeChecker int type) {
        if (mListAdapter == null) {
            initList(data);
        }

        switch (type) {
            case DataLoadType.TYPE_REFRESH_SUCCESS:
                mListAdapter.replaceData(data);
                break;
            case DataLoadType.TYPE_REFRESH_FAIL:
                toast(errorMsg);
                //TODO
                break;
            case DataLoadType.TYPE_LOAD_MORE_SUCCESS:
                //TODO
                break;
            case DataLoadType.TYPE_LOAD_MORE_FAIL:
                //TODO
                break;
        }
    }

    private void initList(List<TopicEntity> data) {
        mListAdapter = new TopicListAdapter(getHoldingActivity(), data);

        mRecyclerView.setAdapter(mListAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData();
            }
        });
    }
}
