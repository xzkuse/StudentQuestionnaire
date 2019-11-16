package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/16
 * @email xiezhengkun@beyondsoft.com
 * @remark
 */
//kotlin 的接口实例   interface  多个方法，多个实现
interface OnInterfaceA{
    fun A()
    open fun B()
}

interface  OnInterfaceB{
    fun A()
    open fun B()
}
//定义接口及方法
//实现接口
class  OnImplAB :OnInterfaceA,OnInterfaceB{
    //强制实现a b 接口的内容   智能实现一个 A B 方法  不需要open 关键字也可以重写
    override fun A() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun B() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}