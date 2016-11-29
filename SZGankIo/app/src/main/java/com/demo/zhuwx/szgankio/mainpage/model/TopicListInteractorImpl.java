package com.demo.zhuwx.szgankio.mainpage.model;

import com.demo.zhuwx.szgankio.api.Networks;
import com.demo.zhuwx.szgankio.api.utils.HttpResultFunc;
import com.demo.zhuwx.szgankio.base.BaseSubscriber;
import com.demo.zhuwx.szgankio.callback.RequestCallback;
import com.demo.zhuwx.szgankio.data.TopicEntity;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/29
 *         Description :
 */

public class TopicListInteractorImpl implements TopicListInteractor<List<TopicEntity>> {
    @Override
    public Subscription requestTopicList(RequestCallback<List<TopicEntity>> callback, String type, int page) {

        return Networks.getInstance().getTopicApi()
                .getTopics(type, page)
                .map(new HttpResultFunc<List<TopicEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(callback));
    }
}
