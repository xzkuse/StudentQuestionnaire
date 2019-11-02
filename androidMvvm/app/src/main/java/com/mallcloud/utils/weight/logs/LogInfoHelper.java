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

/**
 * @author xzk
 * @data 2019/5/23
 * @email xiezhengkun@beyondsoft.com
 * @remark  打印帮助类，后期应该用得上
 */
public class LogInfoHelper {

    public static String customTagPrefix = "";  // 自定义Tag的前缀，可以是作者名

//    public static String getMsg(){
//        Log.d(TAG,new Exception().getStackTrace()[0].getMethodName()); //函数名
//        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getMethodName()); //函数名
//        Log.d(TAG, “”+Thread.currentThread().getStackTrace()[2].getLineNumber()); //行号
//        Log.d(TAG, Thread.currentThread().getStackTrace()[2].getFileName()); //文件名
//        //文件名+行号
//        Log.d(TAG, “[“+Thread.currentThread().getStackTrace()[2].getFileName()+”,”+Thread.currentThread().getStackTrace()[2].getLineNumber()+”]”);
//    }
//    private static StackTraceElement getCallerStackTraceElement() {
//        return Thread.currentThread().getStackTrace()[4];
//    }
//
//    public static String generateTag() {
//        StackTraceElement caller=getCallerStackTraceElement();
//        String tag = "%s.%s(Line:%d)"; // 占位符
//        String callerClazzName = caller.getClassName(); // 获取到类名
//        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
//        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber()); // 替换
//        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
//        return tag;
//    }

    //    private static String getContent(String msg, int place,Object... args) {
    //        try {
    //            String sourceLinks = getNameFromTrace(Thread.currentThread().getStackTrace(), place);
    //            return sourceLinks + String.format(msg, args);
    //        } catch (Throwable throwable) {
    //            return msg;
    //        }
    //    }
    //    protected static String getNameFromTrace(StackTraceElement[] traceElements, int place) {
    //        StringBuilder taskName = new StringBuilder();
    //        if (traceElements != null && traceElements.length > place) {
    //            StackTraceElement traceElement = traceElements[place];
    //            taskName.append(traceElement.getMethodName());
    //            taskName.append("(").append(traceElement.getFileName()).append(":").append(traceElement.getLineNumber()).append(")");
    //        }
    //        return taskName.toString();
    //    }
    //    private static StackTraceElement getCallerStackTraceElement() {
    //        return Thread.currentThread().getStackTrace()[4];
    //    }
    //
    //    public static String generateTag() {
    //        StackTraceElement caller=getCallerStackTraceElement();
    //        String tag = "%s.%s(Line:%d)"; // 占位符
    //        String callerClazzName = caller.getClassName(); // 获取到类名
    //        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
    //        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber()); // 替换
    //        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
    //        return tag;
    //    }


}
