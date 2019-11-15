package com.mallcloud.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @author xzk
 * @data 2019/11/15
 * @email xiezhengkun@beyondsoft.com
 * @remark  示例，创建三个构造函数的自定义view
 */
class SelfView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {


    constructor(context: Context?) : this(context, null, 0) {

    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, null, 0) {

    }
}