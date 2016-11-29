package com.demo.zhuwx.szgankio.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {


    protected T mPresenter;
    protected View mFragmentRootView;

    private BaseActivity mActivity;

    // 标示当前Fragment所在的Activity是否可见
    private boolean mIsStop;

    protected abstract void initView(View rootView, Bundle savedInstanceState);

    protected abstract int getContentViewId();


    public BaseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mFragmentRootView) {
            mFragmentRootView = inflater.inflate(getContentViewId(), container, false);
            initView(mFragmentRootView, savedInstanceState);
        }

        return mFragmentRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public void onStop() {
        super.onStop();
        mIsStop = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsStop = false;
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewGroup parent = (ViewGroup) mFragmentRootView.getParent();
        if (null != parent) {
            parent.removeView(mFragmentRootView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(mActivity, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);

    }

    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }


    @Override
    public void toast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }
}
