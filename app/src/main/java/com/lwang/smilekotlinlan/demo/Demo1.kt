package com.lwang.smilekotlinlan

/**
 * @Author lwang
 * @Date 2021/4/25 21:10
 * @Description 基本数据类型
 */
class DataTypeDemo {

    var mBoolean: Boolean = true
    val anotherBoolean: Boolean = false
}


val maxInt: Int = Int.MAX_VALUE
val minInt: Int = Int.MIN_VALUE


val maxLong: Long = Long.MAX_VALUE
val minLong: Long = Long.MIN_VALUE


val aFloat: Float = 2.0F
val aDouble: Double = 2.0


// 类型转换
val b: Byte = 1
val i: Int = b.toInt()


val aChar: Char = '中'


val string: String = "Hello"
val aString: String = String(charArrayOf('H', 'e', 'l', 'l', 'o'))


fun main() {

    println(maxInt)
    println(minInt)


    println(maxLong)
    println(minLong)


    println(aFloat)
    println(aDouble)


    println(string == aString)
    println(string === aString)


    // 多行字符串
    val text = """
    多行字符串
    多行字符串
    """
    println(text)


    val texts = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin()
    println(texts)


    // 字符串模板
    val i = 10
    val s = "i = $i"
    println(s)


    val ss = "周杰伦"
    val mSS = "$ss.length is ${ss.length}"
    println(mSS)


}