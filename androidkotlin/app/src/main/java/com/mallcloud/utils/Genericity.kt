package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark  泛型操作
 */
class Genericity<T>(t:T){  //定义泛型及传参泛型
    var value = t;
}
/*
// Java
void copyAll(Collection<Object> to, Collection<String> from) {
  to.addAll(from);
  // ！！！对于这种简单声明的 addAll 将不能编译：
  // Collection<String> 不是 Collection<Object> 的子类型
}

型变，概念
默认的 kotlin泛型是能够支持 StringArr添加到ObjectArr中的

协变类型   Collection<? extends Object>
带 extends 限定（上界）的通配符类型使得类型是协变的（covariant）。

声明处型变
// Java
void demo(Source<String> strs) {
  Source<Object> objects = strs; // ！！！在 Java 中不允许
  // ……
}
在 Kotlin 中，有一种方法向编译器解释这种情况。这称为声明处型变：
我们可以标注 Source 的类型参数 T 来确保它仅从 Source<T> 成员中返回（生产），并从不被消费。 为此，我们提供 out 修饰符：

类型投影

星投影  ？？？  完全不理解是什么意思
Function<*, String> , 代表 Function<in Nothing, String> ;
Function<Int, *> , 代表 Function<Int, out Any?> ;
Function<, > , 代表 Function<in Nothing, out Any?> .


泛型函数
fun <T> singletonList(item: T): List<T> {
    // ……
}
val l = singletonList<Int>(1)


泛型约束
上界

类型擦除


 */