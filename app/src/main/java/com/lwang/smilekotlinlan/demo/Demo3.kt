package com.lwang.smilekotlinlan

/**
 * @Author lwang
 * @Date 2021/4/28 14:44
 * @Description 循环控制
 */

val items = listOf("周杰伦", "林俊杰", "王力宏")


fun main() {

    // for循环
    for (item in items) {
        println(item)
    }


    for (index in items.indices) {
        println("item 的 条目 at $index 的值为 is ${items[index]}")
    }


    // while循环
    var x = 0
    while (x > 0) {
        println("while循环: x 的值是 $x")
    }


    // do...while循环
    do {
        println("do...while循环: x 的值是 $x")
    } while (x > 0)


    // 返回和跳转
    for (i in 1..10) {
        if (i == 3) continue     // i 为 3 时跳过当前循环，继续下一次循环
        println(i)
        if (i > 5) break            // i 为 6 时 跳出循环
    }

}
