package com.mallcloud.utils

import android.view.View

/**
 * @author xzk
 * @data 2019/11/16
 * @email xiezhengkun@beyondsoft.com
 * @remark  kotlin的数据类与密封类
 */
//Kotlin 可以创建一个只包含数据的类，关键字为 data：
data class UserB(val name: String, val age: Int)

//主构造函数至少包含一个参数。
//所有的主构造函数的参数必须标识为val 或者 var ;
//数据类不可以声明为 abstract, open, sealed 或者 inner;
//数据类不能继承其他类 (但是可以实现接口)。

//密封类用来表示受限的类继承结构：当一个值为有限几种的类型, 而不能有任何其他类型时。
// 在某种意义上，他们是枚举类的扩展：枚举类型的值集合 也是受限的，但每个枚举常量只存在一个实例，
// 而密封类 的一个子类可以有可包含状态的多个实例。
//
//声明一个密封类，使用 sealed 修饰类，密封类可以有子类，但是所有的子类都必须要内嵌在密封类中。
//
//sealed 不能修饰 interface ,abstract class(会报 warning,但是不会出现编译错误)

//我的理解密封类就是一种专门用来配合 when 语句使用的类，举个例子，假如在 Android 中我们有一个 view，我们现在想通过 when 语句设置针对 view 进行两种操作：显示和隐藏，那么就可以这样做：
//密封类 写法
sealed class UiOp {
    object Show: UiOp()
    object Hide: UiOp()
}
fun execute(view: View, op: UiOp) = when (op) {
    UiOp.Show -> view.visibility = View.VISIBLE
    UiOp.Hide -> view.visibility = View.GONE
}