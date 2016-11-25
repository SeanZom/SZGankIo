package com.demo.zhuwx.szgankio;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class SZGankIo extends Application{

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        initLog();
    }

    private void initLog() {
        if (BuildConfig.DEBUG)
            Logger.init(); // for debug, print all log
        else {
            Logger.init().logLevel(LogLevel.NONE);
        }
    }

    public static Context getAppContext() {
        return sContext;
    }
}
