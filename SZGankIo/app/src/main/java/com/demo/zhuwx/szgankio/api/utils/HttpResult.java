package com.demo.zhuwx.szgankio.api.utils;

import com.google.gson.annotations.SerializedName;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class HttpResult<T> {

    @SerializedName("error")
    private boolean isResultError;
    @SerializedName("results")
    private T data;


    public boolean isResultError() {
        return isResultError;
    }

    public void setResultError(boolean resultError) {
        isResultError = resultError;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
