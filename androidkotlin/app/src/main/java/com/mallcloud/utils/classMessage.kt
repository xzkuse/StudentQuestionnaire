package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/15
 * @email xiezhengkun@beyondsoft.com
 * @remark  类相关消息
 */
class Runoob{//类名称
    //类内容

    var name:String="zzz" //定义一个 var  可变属性
    val url:String="zjaj"  //定义一个 val  不可变属性

    fun foo(){  //成员函数
        print("foo")
    }

    fun use(){
        //创建 类函数
        val emtryIn = Emtry() // 没有 new 关键字，创建对象

    }
}

class Emtry  //定义空类

//创建一个有构造函数的类
class Person constructor(firstName:String){
    /**
     * 创建次构造函数代理，并使用this 函数
     */
    constructor (name:String,age:Int):this(name){

    }


    //获取属性
    fun perAttrtits(){
        var runoob:Runoob = Runoob();
        runoob.name


    }

    var no:Int=100
    get()=field//后端变量
    set(value) {
        if(value <10){  //如果小于10 ，返回该值
            field = value
        }else{   //如果大于或等于10   返回 -1
            field = -1
        }
    }

    //代码块不允许被定义  增加 init就可以
    init{

    }
    //允许定义多个init代码块，按照从上往下的顺序执行
    init {

    }

}

//抽象类
open class BaseA{
    open fun f(){}
}
abstract class Dervice :BaseA(){
    //抽象类 抽象方法，增加override字段
    abstract override fun f()

//    override fun f() {
//        super.f()
//    }
}

//嵌套类
class Outer{ //外部类
    private val bar:Int = 1
    class Nested{//嵌套类
        fun foo() = 2
    }
}

fun opera(){
    val demo = Outer.Nested().foo()//调用格式 外部类，嵌套类，嵌套方法/属性
}

//内部类   内部类和嵌套类  还是不一样的
class Outer1 {
    private val bar: Int = 1
    var v = "成员属性"
    /**嵌套内部类**/
    inner class Inner {
        fun foo() = bar  // 访问外部类成员
        fun innerTest() {
            var o = this@Outer1 //获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}
fun opera2(args: Array<String>) {
    val demo = Outer1().Inner().foo()
    println(demo) //   1
    val demo2 = Outer1().Inner().innerTest()
    println(demo2)   // 内部类可以引用外部类的成员，例如：成员属性
}
//var demo = Outter.Nested()// 嵌套类，Outter后边没有括号
//var demo = Outter().Inner();// 内部类，Outter后边有括号