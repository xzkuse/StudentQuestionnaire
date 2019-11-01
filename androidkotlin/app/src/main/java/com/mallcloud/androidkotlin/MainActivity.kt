package com.mallcloud.androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//获取了引用的布局，比如  activity_main
import kotlinx.android.synthetic.main.activity_main.*

/**
 * kotlin首页
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtAllContent.setText("写个例子")

    }
}
