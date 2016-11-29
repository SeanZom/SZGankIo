package com.demo.zhuwx.szgankio.mainpage.model;

import com.demo.zhuwx.szgankio.callback.RequestCallback;

import rx.Subscription;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/29
 *         Description :
 */

public interface TopicListInteractor<T> {

    Subscription requestTopicList(RequestCallback<T> callback, String type, int page);
}
