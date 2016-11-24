package com.demo.zhuwx.szgankio.mainpage;

import com.demo.zhuwx.szgankio.basic.BaseModel;
import com.demo.zhuwx.szgankio.basic.BasePresenter;
import com.demo.zhuwx.szgankio.basic.BaseView;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public interface MainPageContract {

    interface Model extends BaseModel {
    }

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }

}
