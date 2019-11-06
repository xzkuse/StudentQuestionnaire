package com.mallcloud.androidmvvm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mallcloud.utils.DeadLockDemo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DeadLockDemo.deadLock();
    }
}
