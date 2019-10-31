package com.mallcloud.rfiddemo_c.activity;

import android.app.Application;
import android.content.Context;

/*
 * Created by skye
 * on 2018/9/18
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    /**
     * 获取context
     * @return
     */
    public static Context getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}
