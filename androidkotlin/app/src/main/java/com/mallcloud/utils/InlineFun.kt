package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark  内联函数   内联类允许去继承接口
 */
inline class InlineFun(val name:String){

//    init {
//        println(name)
//    }
    //内联类必须含有唯一的一个属性在主构造函数中初始化。

    //内联类支持普通类中的一些功能。特别是，内联类可以声明属性与函数：

    //然而，内联类的成员也有一些限制：
    //内联类不能含有 init 代码块
    //内联类不能含有幕后字段
    //因此，内联类只能含有简单的计算属性（不能含有延迟初始化/委托属性）

    //在 Gradle 中启用内联类
    //compileKotlin {
    //    kotlinOptions.freeCompilerArgs += ["-Xinline-classes"]
    //}
}