package com.demo.zhuwx.szgankio.callback;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/29
 *         Description :
 */

public interface RequestCallback<T> {

    void beforeRequest();

    void requestError(String msg);

    void requestComplete();

    void requestSuccess(T data);
}
