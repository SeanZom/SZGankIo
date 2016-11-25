package com.demo.zhuwx.szgankio.api.utils;

import rx.functions.Func1;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {


    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.isResultError()) {
            throw new RuntimeException();
        }

        return httpResult.getData();
    }
}
