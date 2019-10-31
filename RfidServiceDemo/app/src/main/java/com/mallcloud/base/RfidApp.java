package com.mallcloud.base;

import android.app.Application;

/**
 * @author xzk
 * @data 2019/10/28
 * @email xiezhengkun@beyondsoft.com
 * @remark
 */
public class RfidApp extends Application {
    private static RfidApp mInstance;
    /**
     * 获取context
     * @return
     */
    public static RfidApp getInstance() {
        if (mInstance == null) {
            mInstance = new RfidApp();
        }
        return mInstance;
    }
}
