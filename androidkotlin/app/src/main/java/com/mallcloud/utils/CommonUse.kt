package com.mallcloud.utils

import android.util.Log

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark  使用common对象  使用Log对象，证明，java和kotlin是无缝衔接的
 */
class CommonUse{

    //限定为字符串类型，然后获取类名   不可变参数
    val lagTag:String = javaClass.name;

    /**
     * 定义当前为方法
     *
     * 传参也是限定数据类型的
     */
    fun useCompaionData(bg:String ):String{//限定返回类型
        //重新赋值则编译不通过
//        lagTag = "lagTag";

        //直接获取伴生对象里的值
        Log.d(lagTag,CommonConstat.inName);
        //使用伴生对象的方法来获取对象
        Log.d(lagTag , CommonConstat.create().name);

        //获取对象
        val constat = CommonConstat()
        //使用对象属性
        Log.d(lagTag , constat.name);

        return "制作返回值";
    }
}