package com.lwang.smilekotlinlan

/**
 * @Author lwang
 * @Date 2021/4/28 11:11
 * @Description 条件控制
 */

var aa: Int = 1
var bb: Int = 2


fun main() {

    // 传统用法
    var maxValue = aa
    if (aa < bb) maxValue = bb
    println(maxValue)


    // 使用 else
    var maxValueTwo: Int
    if (aa > bb) {
        maxValueTwo = aa
    } else {
        maxValueTwo = bb
    }
    println(maxValueTwo)


    // 作为表达式
    var maxValueThree = if (aa > bb) aa else bb
    println(maxValueThree)


    // 使用区间
    val x = 7
    val y = 9
    if (x in 1..8) {
        println("x 在此区间内")
    }


    when (x) {
        5, 8 -> println("x == 5 或者 x == 10")
        9 -> println("x == 9")
        in 1..10 -> println("x is in the range 在这个区间内")
        !in 10..20 -> println("x is outside the range 不在这个区间内")
        else -> {
            println("x 不是 5 也不是 9")
        }
    }


    val items = setOf("周杰伦", "林俊杰", "王力宏")
    when{
        "王力宏" in items-> println("jay在里面呢")
    }


}



