package com.mallcloud.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.mallcloud.base.RfidApp;
import com.mallcloud.rfidservicedemo.R;


/**
 * Created by cuipf on 2017/11/20.
 * Toast统一管理类
 */

public class ToastUtil {

    private static Toast mToast = null;//全局唯一的Toast

    /**
     * 取消Toast显示
     */
    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param resId   资源ID:getResources().getString(R.string.xxxxxx);
     */
    public static void showShort(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(String resource) {
        if (mToast == null) {
            mToast = Toast.makeText(RfidApp.getInstance(), resource, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resource);
        }
        mToast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showLong(String resource) {
        if (mToast == null) {
            mToast = Toast.makeText(RfidApp.getInstance(), resource, Toast.LENGTH_LONG);
        } else {
            mToast.setText(resource);
        }
        mToast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(int resource) {
        if (mToast == null) {
            mToast = Toast.makeText(RfidApp.getInstance(), resource, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resource);
        }
        mToast.show();

    }

//    /**
//     * 长时间显示Toast
//     *
//     * @param context
//     * @param message
//     */
//    public static void showLong(CharSequence message) {
//
//        try {
//            if (mToast == null) {
//                mToast = Toast.makeText(MainApp.getInstance(), message, Toast.LENGTH_SHORT);
//            }
//            View toastView = LayoutInflater.from(MainApp.getInstance()).inflate(R.layout.toast_layout, null);
//            TextView titleTv = toastView.findViewById(R.id.toast_title);
//            titleTv.setVisibility(View.GONE);
//            TextView text = toastView.findViewById(R.id.toast_text);
//            text.setText(message);
//            mToast.setView(toastView);
//            mToast.setGravity(Gravity.CENTER, 0, 0);
//            mToast.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (mToast == null) {
            mToast = Toast.makeText(RfidApp.getInstance(), message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
        }
        mToast.show();
//        try {
//            if (mToast == null) {
//                mToast = Toast.makeText(MainApp.getInstance(), message, Toast.LENGTH_SHORT);
//            }
//            View toastView = LayoutInflater.from(MainApp.getInstance()).inflate(R.layout.toast_layout, null);
//            TextView titleTv = toastView.findViewById(R.id.toast_title);
//            titleTv.setVisibility(View.GONE);
//            TextView text = toastView.findViewById(R.id.toast_text);
//            text.setText(message);
//            mToast.setView(toastView);
//            mToast.setGravity(Gravity.CENTER, 0, 0);
//            mToast.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param resId   资源ID:getResources().getString(R.string.xxxxxx);
     */
    public static void showLong(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(RfidApp.getInstance(), resId, Toast.LENGTH_LONG);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }
    /**
     * 长时间显示Toast
     *
     * @param resId   资源ID:getResources().getString(R.string.xxxxxx);
     */
    public static void showLong(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(RfidApp.getInstance(), resId, Toast.LENGTH_LONG);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration 单位:毫秒
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, duration);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param resId    资源ID:getResources().getString(R.string.xxxxxx);
     * @param duration 单位:毫秒
     */
    public static void show(Context context, int resId, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, duration);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    /**
     * 自定义Toast的View
     *
     * @param context
     * @param message
     * @param duration 单位:毫秒
     * @param view     显示自己的View
     */
    public static void customToastView(Context context, CharSequence message, int duration, View view) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, duration);
        } else {
            mToast.setText(message);
        }
        if (view != null) {
            mToast.setView(view);
        }
        mToast.show();
    }

    /**
     * 自定义Toast的位置
     *
     * @param context
     * @param message
     * @param duration 单位:毫秒
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void customToastGravity(Context context, CharSequence message, int duration, int gravity, int xOffset,
                                          int yOffset) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, duration);
        } else {
            mToast.setText(message);
        }
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.show();
    }

    /**
     * 自定义带图片和文字的Toast，最终的效果就是上面是图片，下面是文字
     *
     * @param context
     * @param message
     * @param iconResId 图片的资源id,如:R.drawable.icon
     * @param duration
     */
    public static void showToastWithImageAndText(Context context, CharSequence message, int iconResId, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, duration);
        }
        View toastView = LayoutInflater.from(RfidApp.getInstance()).inflate(R.layout.toast_layout, null);
        TextView titleTv = toastView.findViewById(R.id.toast_title);
        titleTv.setVisibility(View.GONE);
        TextView text = toastView.findViewById(R.id.toast_text);
        text.setText(message);
        mToast.setView(toastView);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * @param context
     * @param title
     * @param message
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void customToastAll(Context context, CharSequence title, CharSequence message, int gravity,
                                      int xOffset, int yOffset) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
        }
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView titleTv = toastView.findViewById(R.id.toast_title);
        TextView text = toastView.findViewById(R.id.toast_text);
        titleTv.setText(title);
        text.setText(message);
        mToast.setView(toastView);
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.show();
    }

    /**
     * 自定义Toast,针对类型resId
     *
     * @param context
     * @param resId
     * @param duration
     * @param view             :应该是一个布局，布局中包含了自己设置好的内容
     * @param isGravity        true,表示后面的三个布局参数生效,false,表示不生效
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @param isMargin         true,表示后面的两个参数生效，false,表示不生效
     * @param horizontalMargin
     * @param verticalMargin
     */
    public static void customToastAll(Context context, int resId, int duration, View view, boolean isGravity,
                                      int gravity, int xOffset, int yOffset, boolean isMargin, float horizontalMargin,
                                      float verticalMargin) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, duration);
        } else {
            mToast.setText(resId);
        }
        if (view != null) {
            mToast.setView(view);
        }
        if (isMargin) {
            mToast.setMargin(horizontalMargin, verticalMargin);
        }
        if (isGravity) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
        mToast.show();
    }


}
