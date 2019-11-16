package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/16
 * @email xiezhengkun@beyondsoft.com
 * @remark
 */
//kot的枚举类型
enum class Color2{
    RED,BLACK,BLUE,GREEN,WHITE
}

//枚举类的初始化
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//枚举还支持以声明自己的匿名类及相应的方法、以及覆盖基类的方法。如：
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}