package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/16
 * @email xiezhengkun@beyondsoft.com
 * @remark
 */
//扩展类，之后还可以 扩展函数
//扩展函数定义形式：
//
//fun receiverType.functionName(params){
//    body
//}
//receiver  函数的接收者
//函数名   参数

class User (var name:String)
//扩展函数，可以调用 接收者的类的 属性
fun User.Prints(){
    print("用户名 $name")
}
//调用也是 对象.扩展函数
//支持传参，有返回值

//为已存在的类，在不修改内部结构的情况下，扩展函数

//伴生对象的扩展 todo
class MyClassA{
    companion object{
        //伴生对象内部，理解为静态类
    }
}

//伴生对象的扩展函数
fun MyClassA.Companion.foo(){
    println("伴生对象的扩展函数")
}
