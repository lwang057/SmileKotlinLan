package com.lwang.smilekotlinlan

/**
 * @Author lwang
 * @Date 2021/4/28 15:47
 * @Description 类和对象
 */

//----------- 类的修饰符 -----------
/*
classModifier: 类属性修饰符，标示类本身特性。
    abstract    // 抽象类
    final       // 类不可继承，默认属性
    enum        // 枚举类
    open        // 类可继承，类默认是final的
    annotation  // 注解类

accessModifier: 访问权限修饰符
    private    // 仅在同一个文件中可见
    protected  // 同一个文件中或子类可见
    public     // 所有调用的地方都可见
    internal   // 同一个模块中可见
*/


class Person {
    var lastName: String = "周杰伦"
        get() = field.toUpperCase() //将变量赋值后转换为大写
        set


    var no: Int = 100
        get() = field //后端变量
        set(value) {
            if (value < 10) {
                field = value //如果传入的值小于 10 返回该值
            } else {
                field = -1 //如果传入的值大于等于 10 返回 -1
            }
        }

    var height: Float = 145.4f
        private set
}


//fun main() {
//    var person: Person = Person()
//
//    person.lastName = "王力宏"
//    println("lastName: ${person.lastName}")
//    person.no = 9
//    println("no: ${person.no}")
//    person.no = 11
//    println("no: ${person.no}")
//}


//----------- 类的主构造器和次构造器 -----------
class Runoob constructor(name: String) {

    var url: String = "http://www.baidu.com"
    var country: String = "CN"
    var siteName = name

    init {
        println("初始化网站名为：${name}")
    }

    //次构造函数  如果类有主构造函数，每个次构造函数都要直接或间接 通过另一个次构造函数代理主构造函数。在同一个类中代理另一个构造函数使用 this 关键字：
    constructor(name: String, alexa: Int) : this(name) {
        println("Alexa的排名为：${alexa}")
    }


    fun printTest() {
        println("我这个类的函数呀")
    }
}


//fun main() {
//    val runoob = Runoob("滴答滴答", 1000)
//    println(runoob.url)
//    println(runoob.country)
//    println(runoob.siteName)
//    runoob.printTest()
//}


//----------- 抽象类 -----------
open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    override abstract fun f()
}


//----------- 嵌套类 -----------
class Outer { //外部类
    private val bar: Int = 1

    class Nested { //内部类
        fun foo() = 2
    }
}

//fun main() {
//    val demo = Outer.Nested().foo()  //调用格式：外部类.嵌套类().嵌套类方法/属性
//    println(demo)
//}


//----------- 内部类 -----------
//内部类使用 inner 关键字来表示。
//内部类会带有一个对外部类的对象的引用，所以内部类可以访问外部类成员属性和成员函数。
class OuterOne {
    private val bar: Int = 1
    var v = "成员属性"

    inner class OuterTwo {
        fun foo() = bar //访问外部类成员

        fun test() {
            var i = this@OuterOne  //获取外部类的成员变量
            println("内部类可以引用外部类的成员，" + i.v)
        }
    }
}

//fun main() {
//    val demo = OuterOne().OuterTwo().foo()
//    println(demo)
//
//    val demo1 = OuterOne().OuterTwo().test()
//    println(demo1)
//}


//----------- 匿名内部类 -----------
class Test {

    var v = "成员属性"

    fun setWork(tt: TestInterFace) {
        tt.test()
    }
}

interface TestInterFace {
    fun test()
}

fun main() {
    val test = Test()
    test.setWork(object : TestInterFace {
        override fun test() {
            println("对象表达式创建匿名内部类的实例---")
        }
    })
}