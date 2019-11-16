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
//类的委托即一个类中定义的方法实际是调用另一个类的对象的方法来实现的。
class Derived(b: Base) : Base by b
//通过by 关键字，将调用的过程省略了   叫 委托(毕竟方法名是一致的 A的名字，也就调用了 B的名字)
//大家都继承了  base   大家都有print方法   调用类，传入了Base
//继承base 还传入 base


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

//属性委托
//属性委托指的是一个类的某个属性值不是在类中直接进行定义，而是将其托付给一个代理类，
// 从而实现对该类的属性统一管理。
//
//属性委托语法格式：
//val/var <属性名>: <类型> by <表达式>

//延迟属性 Lazy
//lazy() 是一个函数, 接受一个 Lambda 表达式作为参数, 返回一个 Lazy <T> 实例的函数，返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的 lamda 表达式并记录结果， 后续调用 get() 只是返回记录的结果。

//可观察属性 Observable、
//observable 可以用于实现观察者模式。
//
//Delegates.observable() 函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler)。
//
//在属性赋值后会执行事件的响应器(handler)，它有三个参数：被赋值的属性、旧值和新值：

//把属性储存在映射中
//一个常见的用例是在一个映射（map）里存储属性的值。 这经常出现在像解析 JSON 或者做其他"动态"事情的应用中。 在这种情况下，你可以使用映射实例自身作为委托来实现委托属性。

//提供委托
//通过定义 provideDelegate 操作符，可以扩展创建属性实现所委托对象的逻辑。 如果 by 右侧所使用的对象将 provideDelegate 定义为成员或扩展函数，那么会调用该函数来 创建属性委托实例。
//
//provideDelegate 的一个可能的使用场景是在创建属性时（而不仅在其 getter 或 setter 中）检查属性一致性。
