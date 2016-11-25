package com.demo.zhuwx.szgankio.mainpage;

import com.demo.zhuwx.szgankio.api.Networks;
import com.demo.zhuwx.szgankio.api.utils.HttpResult;
import com.demo.zhuwx.szgankio.data.TopicEntity;

import java.util.List;

import rx.Observable;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class MainPageModel implements MainPageContract.Model {


    @Override
    public Observable<HttpResult<List<TopicEntity>>> getTopics(String topic, int page) {
        return Networks.getInstance().getTopicApi().getTopics(topic, page);
    }
}
