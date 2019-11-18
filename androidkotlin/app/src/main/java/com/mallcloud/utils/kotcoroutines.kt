package com.mallcloud.utils

import kotlinx.coroutines.*
/**
 * @author xzk
 * @data 2019/11/16
 * @email xiezhengkun@beyondsoft.com
 * @remark  协程方面代码（多线程）   需要导入  coroutlines  库
 */
fun main() {

    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }
    println("Hello,") // 协程已在等待时主线程还在继续
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活

    runBlocking {     // 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
    }
}

//这个示例可以使用更合乎惯用法的方式重写，使用 runBlocking 来包装 main 函数的执行：
//fun main() = runBlocking<Unit> { // 开始执行主协程
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,") // 主协程在这里会立即执行
//    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
//}

//等待一个子线程完成
suspend fun waitChildJoin(){
    runBlocking {  }
    val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // 等待直到子协程执行结束
}

//结构化并发
//。如果协程中的代码挂起了会怎么样（例如，我们错误地延迟了太长时间），如果我们启动了太多的协程并导致内存不足会怎么样？ 必须手动保持对所有已启动协程的引用并 join 之很容易出错。
//我们可以在代码中使用结构化并发。
// 我们可以在执行操作所在的指定作用域内启动协程， 而不是像通常使用线程（线程总是全局的）那样在 GlobalScope 中启动。
//fun main() = runBlocking { // this: CoroutineScope
//    launch { // 在 runBlocking 作用域中启动一个新协程
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//}
//以上示例，作用域为一个方法，方法运行完毕，则协程结束，结束操作由 runBlocking 方法执行

//上面这段话，我理解是为了控制协程的作用域，避免一些意外情况，导致协程后台挂起，无法销毁，占用内存
//用方法域和runBlocking来结合实现 协程稳定销毁的策略

//android中有个 viewModelScope 这个协程 我一般用这个 然后销毁啥的就不管了