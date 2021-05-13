package com.lwang.smilekotlinlan


/**
 * @Author lwang
 * @Date 2021/5/8 11:55
 * @Description 泛型
 */

class Box<T>(t: T) {
    var value = t
}


fun main() {

    val box: Box<Int> = Box<Int>(10)
    val boxInt = Box<Int>(11) //编译器会自己进行类型推断

    println(box.value)
    println(boxInt.value)


    val boxString = Box<String>("周杰伦")
    println(boxString.value)
}

