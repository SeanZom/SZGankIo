package com.demo.zhuwx.szgankio.basic;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
}
