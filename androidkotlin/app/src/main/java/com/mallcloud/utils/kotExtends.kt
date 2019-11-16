package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/16
 * @email xiezhengkun@beyondsoft.com
 * @remark kotlin 继承信息
 */
//任意类都有个隐藏继承的父类Any  equals  hashCode   toString

//使用 open关键字修饰的类 可以被继承
open class BaseB(p:Int)//定义基类

class Derviceds(p:Int):BaseB(p)  //继承  ：

open class PersonA{
    open fun study(){//允许被子类重写
        println("被重写的方法")
    }
}
//子类
class Students:PersonA(){
    override fun study() {
        println("重写一个方法")
    }
}
//如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，
// 使用super范型去选择性地调用父类的实现。