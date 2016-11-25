package com.demo.zhuwx.szgankio.mainpage;

import com.demo.zhuwx.szgankio.api.utils.HttpResult;
import com.demo.zhuwx.szgankio.basic.BaseModel;
import com.demo.zhuwx.szgankio.basic.BasePresenter;
import com.demo.zhuwx.szgankio.basic.BaseView;
import com.demo.zhuwx.szgankio.data.TopicEntity;

import java.util.List;

import rx.Observable;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public interface MainPageContract {

    interface Model extends BaseModel {
        Observable<HttpResult<List<TopicEntity>>> getTopics(String topic, int page);
    }

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        void loadTopics(int pageIndex);
    }

}
