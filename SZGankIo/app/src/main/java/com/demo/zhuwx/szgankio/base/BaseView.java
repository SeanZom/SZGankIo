package com.demo.zhuwx.szgankio.base;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public interface BaseView {

    void toast(String msg);
    void startLoading();
    void endLoading();
}
