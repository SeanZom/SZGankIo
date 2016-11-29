package com.demo.zhuwx.szgankio.mainpage.presenter;

import com.demo.zhuwx.szgankio.base.BasePresenterImpl;
import com.demo.zhuwx.szgankio.common.DataLoadType;
import com.demo.zhuwx.szgankio.data.TopicEntity;
import com.demo.zhuwx.szgankio.mainpage.TopicListContract;
import com.demo.zhuwx.szgankio.mainpage.model.TopicListInteractor;
import com.demo.zhuwx.szgankio.mainpage.model.TopicListInteractorImpl;

import java.util.List;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class TopicListPresenterImpl extends BasePresenterImpl<TopicListContract.View, List<TopicEntity>> implements TopicListContract.Presenter{

    private TopicListInteractor<List<TopicEntity>> mTopicListInteractor;

    private String mCurrentTopic;
    private int mCurrentPage;

    private boolean mIsRefresh = true;
    private boolean mHasInit;


    public TopicListPresenterImpl(TopicListContract.View view, String currentTopic) {
        super(view);
        mTopicListInteractor = new TopicListInteractorImpl();
        mSubscription = mTopicListInteractor.requestTopicList(this, currentTopic, mCurrentPage);
        mCurrentTopic = currentTopic;
    }

    @Override
    public void beforeRequest() {
        if (!mHasInit) {
            mHasInit = true;
            mView.startLoading();
        }
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.updateTopicList(null, msg, mIsRefresh ? DataLoadType.TYPE_REFRESH_FAIL : DataLoadType.TYPE_LOAD_MORE_FAIL);

    }

    @Override
    public void requestSuccess(List<TopicEntity> data) {
        if (data != null) {
            mCurrentPage += 1;
        }
        mView.updateTopicList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);
    }

    @Override
    public void refreshData() {
        mCurrentPage = 1;
        mIsRefresh = true;
        mSubscription = mTopicListInteractor.requestTopicList(this, mCurrentTopic, mCurrentPage);
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        mSubscription = mTopicListInteractor.requestTopicList(this, mCurrentTopic, mCurrentPage);
    }
}
