package com.demo.zhuwx.szgankio.mainpage;

import com.demo.zhuwx.szgankio.api.utils.HttpResultFunc;
import com.demo.zhuwx.szgankio.data.TopicEntity;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class MainPagePresenter implements MainPageContract.Presenter{

    private MainPageModel mModel;
    private MainPageContract.View mView;

    private String mCurrentTopic;


    public MainPagePresenter(int position) {
        setCurrentTopic(position);
        mModel = new MainPageModel();
    }

    private void setCurrentTopic(int position) {
        switch (position) {
            case 0:
                mCurrentTopic = MainPageFragment.TOPIC_ANDROID;
                break;
            case 1:
                mCurrentTopic = MainPageFragment.TOPIC_IOS;
                break;
            default:
                mCurrentTopic = MainPageFragment.TOPIC_FRONT_ENDS;
                break;
        }
    }

    @Override
    public void attachView(MainPageContract.View view) {
        mView = view;
    }


    @Override
    public void loadTopics(int pageIndex) {
        mModel.getTopics(mCurrentTopic, pageIndex)
                .map(new HttpResultFunc<List<TopicEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<TopicEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<TopicEntity> topicEntities) {

                    }
                });

    }
}
