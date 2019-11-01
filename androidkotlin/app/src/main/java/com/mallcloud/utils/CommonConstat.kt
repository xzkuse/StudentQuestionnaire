package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark  常量页面  配置common
 */
class CommonConstat{

//    增加伴生对象  类似增加了静态方法
    companion object Factory{
    //创建伴生对象的方法
        fun create():CommonConstat = CommonConstat();

        var inName = "inName";

    }

    var name:String = "第一个名字，限定了类型";//不能改为例如整型，或者 浮点型
    var age=1;//不限定类型的时候，随便改赋值值类型

}