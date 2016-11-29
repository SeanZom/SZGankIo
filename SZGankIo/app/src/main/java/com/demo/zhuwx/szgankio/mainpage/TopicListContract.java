package com.demo.zhuwx.szgankio.mainpage;

import com.demo.zhuwx.szgankio.base.BasePresenter;
import com.demo.zhuwx.szgankio.base.BaseView;
import com.demo.zhuwx.szgankio.common.DataLoadType;
import com.demo.zhuwx.szgankio.data.TopicEntity;

import java.util.List;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public interface TopicListContract {

    interface View extends BaseView {
        void updateTopicList(List<TopicEntity> data, String errorMsg, @DataLoadType.DataLoadTypeChecker int type);
    }

    interface Presenter extends BasePresenter {
        void refreshData();
        void loadMoreData();
    }

}
