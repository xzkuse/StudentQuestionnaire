/*
 * Copyright (c) 2018. Beyondsoft Corporation.
 * All Rights Reserved.
 *
 * BEYONDSOFT CONFIDENTIALITY
 *
 * The information in this file is confidential and privileged from Beyondsoft Corporation,
 * which is intended only for use solely by Beyondsoft Corporation.
 * Disclosure, copying, distribution, or use of the contents of this file by persons other than Beyondsoft Corporation
 * is strictly prohibited and may violate applicable laws.
 */
package com.mallcloud.utils.weight.logs;

import android.util.Log;

/**
 * @author xzk
 * @data 2019/5/23
 * @email xiezhengkun@beyondsoft.com
 * @remark  log打印库
 */
public class LogTag {

    public static final String LOG_TAG = "-----";
    public static boolean DEBUG = true;

    private LogTag() {
        throw new IllegalStateException("you can't instantiate me!");
    }


    public static boolean isDebug() {
        return DEBUG;
    }

    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }


    public static final void d(String log) {
        if (DEBUG) {
            Log.d(getTag(), getLog(log));
        }

    }

    public static final void d(String tag, String log) {
        if (DEBUG) {
            Log.d(tag, getLog(log));
        }

    }

    public static final void e(String log) {
        if (DEBUG) {
            Log.e(getTag(), getLog(log));
        }

    }

    public static final void e(String tag, String log) {
        if (DEBUG) {
            Log.e(tag, getLog(log));
        }

    }

    public static final void i(String log) {
        if (DEBUG) {
            Log.i(getTag(), getLog(log));
        }

    }

    public static final void i(String tag, String log) {
        if (DEBUG) {
            Log.i(tag, getLog(log));
        }

    }

    public static final void v(String log) {
        if (DEBUG) {
            Log.v(getTag(), getLog(log));
        }

    }

    public static final void v(String tag, String log) {
        if (DEBUG) {
            Log.v(tag, getLog(log));
        }

    }

    public static final void w(String log) {
        if (DEBUG) {
            Log.w(getTag(), getLog(log));
        }

    }

    public static final void w(String tag, String log) {
        if (DEBUG) {
            Log.w(tag, getLog(log));
        }

    }
    // 自定义Tag的前缀，可以是作者名
   public static String customTagPrefix ="LogTag";

    private static String getTag(){
        return customTagPrefix;
    }

    private static String getLog(String log){
        String print = print(log);
        return print;
    }

    /**
     * 由于是按照层级采集的打印定位，所以千万不要动这个方法名和内部叠加数字
     * 这里用来实现点击跳转到对应代码行的需求
     *
     * @param content
     * @return
     */
    public static String print(String content) {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        int poi=0;
        for (int i = 0; i < traceElements.length; i++) {
            if (traceElements != null && traceElements.length > i) {
                StackTraceElement traceElement = traceElements[i];
                String methodName = traceElement.getMethodName();
                if(methodName.equals("print")){
                    //当前方法名，千万不要动
                    poi=i+3;
                    break;
                }
            }
        }
        StringBuilder taskName = new StringBuilder();
        if (traceElements != null && traceElements.length > poi) {
            StackTraceElement traceElement = traceElements[poi];
            taskName.append(traceElement.getMethodName());
            taskName.append("(").append(traceElement.getFileName()).append(":").append(traceElement.getLineNumber()).append(")");
        }
        taskName.append(content);
        return taskName.toString();
    }
}
