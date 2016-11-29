package com.demo.zhuwx.szgankio.base;

import com.demo.zhuwx.szgankio.callback.RequestCallback;

import rx.Subscription;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/29
 *         Description :
 */

public class BasePresenterImpl<T extends BaseView, V> implements BasePresenter, RequestCallback<V>{


    protected Subscription mSubscription;
    protected T mView;

    public BasePresenterImpl(T view) {
        mView = view;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

    @Override
    public void beforeRequest() {
        mView.startLoading();
    }

    @Override
    public void requestError(String msg) {
        mView.endLoading();
    }

    @Override
    public void requestComplete() {
        mView.endLoading();
    }

    @Override
    public void requestSuccess(V data) {

    }
}
