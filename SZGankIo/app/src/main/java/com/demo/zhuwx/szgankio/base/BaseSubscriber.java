package com.demo.zhuwx.szgankio.base;

import com.demo.zhuwx.szgankio.SZGankIo;
import com.demo.zhuwx.szgankio.callback.RequestCallback;
import com.demo.zhuwx.szgankio.utils.CheckUtil;
import com.google.gson.JsonParseException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

import static com.demo.zhuwx.szgankio.utils.CheckUtil.checkNotNull;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/29
 *         Description :
 */

public class BaseSubscriber<T> extends Subscriber<T> {

    private RequestCallback<T> mRequestCallback;


    public BaseSubscriber(RequestCallback<T> requestCallback) {
        mRequestCallback = checkNotNull(requestCallback);
    }

    @Override
    public void onStart() {
        super.onStart();
        checkNotNull(mRequestCallback);
        mRequestCallback.beforeRequest();
    }

    @Override
    public void onCompleted() {
        checkNotNull(mRequestCallback);
        mRequestCallback.requestComplete();
    }

    @Override
    public void onError(Throwable e) {
        checkNotNull(mRequestCallback);
        String errorMsg = null;
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                case 403:
                    errorMsg = "没有权限访问此链接！";
                    break;
                case 504:
                    if (!CheckUtil.isConnected(SZGankIo.getAppContext())) {
                        errorMsg = "没有联网哦！";
                    } else {
                        errorMsg = "网络连接超时！";
                    }
                    break;
                default:
                    errorMsg = ((HttpException) e).message();
                    break;
            }
        } else if (e instanceof UnknownHostException) {
            errorMsg = "不知名主机！";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = "网络连接超时！";
        }else if (e instanceof JsonParseException){
            errorMsg = "未知异常！";
        }
        mRequestCallback.requestError(errorMsg);
    }

    @Override
    public void onNext(T t) {
        checkNotNull(mRequestCallback);
        mRequestCallback.requestSuccess(t);
    }
}
