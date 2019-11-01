package com.mallcloud.rfiddemo_c.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.mallcloud.rfiddemo_c.fragment.InventoryFragment;
import com.mallcloud.rfiddemo_c.fragment.SettingFragment;
import com.mallcloud.rfiddemo_c.fragment.WriteLockFragment;
import com.mallcloud.rfidservicedemo.R;
import com.supoin.rfidservice.sdk.ModuleController;

public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    private ImageButton ibRead;
    private ImageButton ibWrite;
    private ImageButton ibSet;
    private Toast toast;
    private Activity activity;
    private boolean turnFlag = false;
    private ModuleController moduleController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moduleController = ModuleController.getInstance(this,new ModuleController.DataListener(){
            @Override
            public void onConnect(boolean isSuccess) {
                super.onConnect(isSuccess);
                if (isSuccess) {
                    turnFlag = true;
                }
            }
        });


        //设置基础布局
        setContentView(R.layout.activity_base);
//        //隐藏状态栏和导航栏
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //默认启动时显示的页面
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, InventoryFragment.newInstance("1")).commit();
        //设置屏幕常亮
        keepScreenLongLight(this);
        //初始化view
        initView();
    }

    /*初始化view*/
    private void initView() {
        ibRead = findViewById(R.id.ib_read);
        ibWrite = findViewById(R.id.ib_write);
        ibSet = findViewById(R.id.ib_set);
        ibRead.setOnClickListener(this);
        ibWrite.setOnClickListener(this);
        ibSet.setOnClickListener(this);
        ibRead.setImageResource(R.drawable.rfid_scan_red);
        ibWrite.setImageResource(R.drawable.rfid_write_gray);
        ibSet.setImageResource(R.drawable.rfid_set_gray);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ib_read:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, InventoryFragment
                    .newInstance("1")).commit();
                ibRead.setImageResource(R.drawable.rfid_scan_red);
                ibWrite.setImageResource(R.drawable.rfid_write_gray);
                ibSet.setImageResource(R.drawable.rfid_set_gray);
                break;
            case R.id.ib_write:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, WriteLockFragment
                    .newInstance(2)).commit();
                ibRead.setImageResource(R.drawable.rfid_scan_gray);
                ibWrite.setImageResource(R.drawable.rfid_write_red);
                ibSet.setImageResource(R.drawable.rfid_set_gray);
                break;
            case R.id.ib_set:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, SettingFragment
                    .newInstance(3)).commit();
                ibRead.setImageResource(R.drawable.rfid_scan_gray);
                ibWrite.setImageResource(R.drawable.rfid_write_gray);
                ibSet.setImageResource(R.drawable.rfid_set_red);
                break;
        }
    }


    /**
     * 使屏幕常亮
     * 继承该Activity的页面也将常亮
     *
     * @param activity
     */
    public static void keepScreenLongLight(Activity activity) {
        boolean isOpenLight = true;
        Window window = activity.getWindow();
        if (isOpenLight) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * 显示长toast
     *
     * @param msg
     */
    public void toastLong(String msg) {
        if (null == toast) {
            toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(msg);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * 显示短toast
     *
     * @param msg
     */
    public void toastShort(String msg) {
        if (null == toast) {
            toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moduleController.close();
        Log.d("BasActivity", "moduleController.close()=======");
    }


    private int secretCodeNum = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_0){
            secretCodeNum++;
        }else{
            secretCodeNum = 0;
        }
        if(secretCodeNum == 4){
            secretCodeNum =0;
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.supoin.rfidservice", "com.supoin.rfidservice.activity.SKDActivity");
            intent.setComponent(componentName);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
