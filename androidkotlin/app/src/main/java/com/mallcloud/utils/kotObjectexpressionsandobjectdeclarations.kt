package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/16
 * @email xiezhengkun@beyondsoft.com
 * @remark  Kotlin 对象表达式和对象声明
 */
//Kotlin 用对象表达式和对象声明来实现创建一个对某个类做了轻微改动的类的对象，
// 且不需要去声明一个新的子类。


//匿名内部类
//通过对象表达式实现一个匿名内部类的对象用于方法的参数中：
//
//window.addMouseListener(object : MouseAdapter() {
//    override fun mouseClicked(e: MouseEvent) {
//        // ...
//    }
//    override fun mouseEntered(e: MouseEvent) {
//        // ...
//    }
//})

//通过对象表达式可以越过类的定义直接得到一个对象：
fun mainA( ) {
    //直接使用 object来定义一个临时类，来实现对象调用
    val site = object {
        var name: String = "菜鸟教程"
        var url: String = "www.runoob.com"
    }
    println(site.name)
    println(site.url)
}

//对象声明
//Kotlin 使用 object 关键字来声明一个对象。
//
//Kotlin 中我们可以方便的通过对象声明来获得一个单例。

object DataProviderManager {

    class DataProvider {

    }


    fun registerDataProvider(provider: DataProvider) {
        // ……
    }

//    val allDataProviders: Collection<DataProvider>
//        get(){
//            return DataProvider()
//        }
}

//引用该对象，我们直接使用其名称即可：
//DataProviderManager.registerDataProvider(……)

//伴生对象
//类内部的对象声明可以用 companion 关键字标记，
// 这样它就与外部类关联在一起，我们就可以直接通过外部类访问到对象的内部元素。
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}
val instance = MyClass.create()   // 访问到对象的内部元素

//对象表达式和对象声明之间的语义差异
    //对象表达式和对象声明之间有一个重要的语义差别：
    //
    //对象表达式是在使用他们的地方立即执行的
    //
    //对象声明是在第一次被访问到时延迟初始化的
    //
    //伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配