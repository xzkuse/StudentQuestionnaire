package com.mallcloud.utils

/**
 * @author xzk
 * @data 2019/11/1
 * @email xiezhengkun@beyondsoft.com
 * @remark  集合方面内容
 */
class ArrayCreate{
    //list  set  map
    fun createArr(){
        //创建集合
        val numbers = listOf("one", "two", "three", "four")
        //回去集合长度
        println("Number of elements: ${numbers.size}")
        //获取集合第二位数据
        println("Third element: ${numbers.get(2)}")
        println("Fourth element: ${numbers[3]}")
        //获取集合相等元素的下标
        println("Index of element \"two\" ${numbers.indexOf("two")}")
        //List 与数组（Array）非常相似。 但是，有一个重要的区别：数组的大小是在初始化时定义的，永远不会改变; 反之，List 没有预定义的大小；作为写操作的结果，可以更改 List 的大小：添加，更新或删除元素。
    }

    //在 Kotlin 中，List 的默认实现是 ArrayList，可以将其视为可调整大小的数组。

    //Set<T> 存储唯一的元素；它们的顺序通常是未定义的。

    fun setCreate(){
        //创建set集合
        val numbers = setOf(1, 2, 3, 4)
        println("Number of elements: ${numbers.size}")
        if (numbers.contains(1)) println("1 is in the set")

        val numbersBackwards = setOf(4, 3, 2, 1)
        println("The sets are equal: ${numbers == numbersBackwards}")

//        LinkedHashSet<String>()
//        hashSetOf<String>()
    }
    //Set的默认实现 - LinkedHashSet – 保留元素插入的顺序。
    // 因此，依赖于顺序的函数，例如 first() 或 last()，会在这些 set 上返回可预测的结果。

//另一种实现方式 – HashSet – 不声明元素的顺序，所以在它上面调用这些函数会返回不可预测的结果。但是，HashSet 只需要较少的内存来存储相同数量的元素。


//Map 存储 键-值 对（或 条目）；键是唯一的，但是不同的键可以与相同的值配对。Map 接口提供特定的函数进行通过键访问值、搜索键和值等操作。
    fun  createMap(){
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

        println("All keys: ${numbersMap.keys}")
        println("All values: ${numbersMap.values}")
        if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
        if (1 in numbersMap.values) println("The value 1 is in the map")
        if (numbersMap.containsValue(1)) println("The value 1 is in the map") // 同上


    //MutableMap 是一个具有写操作的 Map 接口，可以使用该接口添加一个新的键值对或更新给定键的值。
//        val numbersMap = mutableMapOf("one" to 1, "two" to 2)
//        numbersMap.put("three", 3)
//        numbersMap["one"] = 11
//
//        println(numbersMap)


    //迭代器遍历
        val numbersIterator = numbersMap.iterator()
        while (numbersIterator.hasNext()) {
            println(numbersIterator.next())
        }
//for循环遍历
    for (item in numbersIterator) {
        println(item)
    }

    val numbers = mutableListOf("one", "four", "four")

    val mutableListIterator = numbers.listIterator()

        mutableListIterator.next()
        mutableListIterator.add("two")
        mutableListIterator.next()
        mutableListIterator.set("three")
        println(numbers)
    }

}