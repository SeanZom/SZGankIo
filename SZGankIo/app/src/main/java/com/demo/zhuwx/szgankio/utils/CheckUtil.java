package com.demo.zhuwx.szgankio.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/29
 *         Description :
 */

public class CheckUtil {


    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if(reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {

                    return true;
                }
            }
        }
        return false;
    }
}
