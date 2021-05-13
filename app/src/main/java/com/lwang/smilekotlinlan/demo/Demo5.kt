package com.lwang.smilekotlinlan

/**
 * @Author lwang
 * @Date 2021/5/7 17:10
 * @Description 类的继承
 */

//----------- 子类有主构造函数 -----------
//如果子类有主构造函数， 则基类必须在主构造函数中立即初始化。
open class PersonOne(var name: String, var age: Int) {

}

class StudentOneZ(name: String, age: Int, var score: Int) : PersonOne(name, age) {

}

//fun main() {
//    val s = StudentOneZ("周杰伦", 18, 89)
//
//    println("学生姓名：${s.name}")
//    println("学生年龄：${s.age}")
//    println("学生成绩：${s.score}")
//}


//----------- 子类没有主构造函数 -----------
//如果子类没有主构造函数，则必须在每一个二级构造函数中用 super 关键字初始化基类，或者在代理另一个构造函数。初始化基类时，可以调用基类的不同构造方法。
open class PersonTwo(name: String) {

    constructor(name: String, age: Int) : this(name) {
        println("-----基类次级构造函数")
    }
}

class PersonTwoZ : PersonTwo {

    constructor(name: String, age: Int, score: Int) : super(name, age) {
        println("-----继承类次级构造函数")
        println("学生姓名：${name}")
        println("学生年龄：${age}")
        println("学生成绩：${score}")
    }
}

//fun main() {
//    var s = PersonTwoZ("周杰伦", 18, 89)
//}


//----------- 重写函数 -----------
//在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。如果允许子类重写该函数，那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词：
open class PersonThree {

    open fun study() {
        println("-----基类我毕业啦")
    }
}

class PersonThreeZ : PersonThree() {
    override fun study() {
        println("-----我还在读大学呢")
    }
}

//如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，使用super范型去选择性地调用父类的实现。
interface PersonThreeI {
    fun study() {
        println("-----接口我毕业啦")
    }
}

class PersonThreeZI() : PersonThree(), PersonThreeI {

    override fun study() {
        super<PersonThree>.study()
        super<PersonThreeI>.study()
    }
}

fun main() {
    val s = PersonThreeZ()
    s.study()

    val ss = PersonThreeZI()
    ss.study()
}





















