package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark  委托  委托模式已经证明是实现继承的一个很好的替代方式， 而 Kotlin 可以零样板代码地原生支持它。
 */
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

//解释
//Derived 的超类型列表中的 by{: .keyword }-子句表示 b 将会在 Derived 中内部存储。
// 并且编译器将生成转发给 b 的所有 Base 的方法。

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
}

//为了避免继承之后，大量的修改，导致后期维护非常困难，发明了委托


//interface Base {
//    fun printMessage()
//    fun printMessageLine()
//}
//
//class BaseImpl(val x: Int) : Base {
//    override fun printMessage() { print(x) }
//    override fun printMessageLine() { println(x) }
//}
//
//class Derived(b: Base) : Base by b {
//    override fun printMessage() { print("abc") }
//}
//
//fun main() {
//    val b = BaseImpl(10)
//    Derived(b).printMessage()
//    Derived(b).printMessageLine()
//}