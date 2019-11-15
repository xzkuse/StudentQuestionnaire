package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/15
 * @email xiezhengkun@beyondsoft.com
 * @remark  循环页面
 */
//创建集合对象
var tempList = listOf(1,2,3)

fun whileList(){
    //开始循环
    for(item: Int in tempList){
        //打印item
        print(item)
    }

    //获取循环的索引
    for(i in tempList.indices){
        print(tempList[i])
    }


    println("-------while使用--------")
    var x =5
    while (x >0){
        println(x--)
    }

    println("-------------   do while 使用     ----------------")

    var y = 5
    do{
        println(y--)
    }while (y>0)

}

/**
 * 流程控制语句
 */
fun controller(){
    for (i in 1..10){
        if(i == 3){
            continue   //跳出当前进入下一次循环
        }

        print(i)

        if(i>5) break  //跳出循环
    }


//      循环标签设计
    loop@ for(i in 1..100){
        for(j in 1..100){
            //当j == 30 的时候，打破循环，进入到 loop 下去执行
            if(j==30) break@loop
        }
    }

    loop@ for(i in 1..100){
        for(j in 1..30){
            //当大于30 的时候  进入到下一个loop对应的循环
            if(j>30) continue@loop
        }
    }

    return//返回整个函数执行
}

//高级循环指定
fun whileForOther(){

    //正常循环
    for(i in 1..4)  //打印结果为1234
    { print(i) }

    for(i in 4 downTo 1){
        print(i)  //打印结果为4321
    }

    for (i in 1..4 step 2){
        print(i)//指定步长  打印结果为  1 3
    }

    for (i in 4 downTo 1 step 2){
        print(i)  //倒叙指定步长  42
    }

    //循环中不要最后一个范围区间 使用 until
    for(i in 1 until 10){
        print(i)  //打印  1 23456789 不含10
    }

}