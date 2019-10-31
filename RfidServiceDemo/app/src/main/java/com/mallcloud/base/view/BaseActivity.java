package com.mallcloud.base.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author xzk
 * @data 2019/10/28
 * @email xiezhengkun@beyondsoft.com
 * @remark
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceID());
        initializeData(savedInstanceState);
    }

    public abstract int getLayoutResourceID();

    public abstract void initializeData(Bundle savedInstanceState);

}
