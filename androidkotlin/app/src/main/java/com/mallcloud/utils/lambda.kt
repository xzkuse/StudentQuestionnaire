package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark  lambda表达式页
 */
//高阶函数
//高阶函数是将函数用作参数或返回值的函数。
//
//一个不错的示例是集合的函数式风格的 fold， 它接受一个初始累积值与一个接合函数，并通过将当前累积值与每个集合元素连续接合起来代入累积值来构建返回值：
fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

/**
 函数类型
 所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型：(A, B) -> C
 表示接受类型分别为 A 与 B 两个参数并返回一个 C 类型值的函数类型。
 参数类型列表可以为空，如 () -> A。Unit 返回类型不可省略。

 函数类型可以有一个额外的接收者类型，它在表示法中的点之前指定：
 类型 A.(B) -> C 表示可以在 A 的接收者对象上以一个 B 类型参数来调用并返回一个 C 类型值的函数。
 带有接收者的函数字面值通常与这些类型一起使用。

 挂起函数属于特殊种类的函数类型，它的表示法中有一个 suspend 修饰符 ，
 例如 suspend () -> Unit 或者 suspend A.(B) -> C。
        */

/**

使用函数字面值的代码块，采用以下形式之一：
        lambda 表达式: { a, b -> a + b },
        匿名函数: fun(s: String): Int { return s.toIntOrNull() ?: 0 }
        */

/**
 *
 * 方法名  传参   返回值类型   = 表示的是
fun compare(a: String, b: String): Boolean = a.length < b.length

Lambda 表达式的完整语法形式
val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
 */

/**
 *
 *
 * Lambda 表达式或者匿名函数（以及局部函数和对象表达式） 可以访问其 闭包 ，即在外部作用域中声明的变量。
 * 在 lambda 表达式中可以修改闭包中捕获的变量：
        var sum = 0
        ints.filter { it > 0 }.forEach {
        sum += it
        }
        print(sum)
    */