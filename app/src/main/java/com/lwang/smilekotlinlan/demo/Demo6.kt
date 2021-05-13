package com.lwang.smilekotlinlan

/**
 * @Author lwang
 * @Date 2021/5/8 10:21
 * @Description 扩展
 */

//----------- 扩展函数 -----------
class User(var name: String) {
//    fun Print(){ //若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
//        println("成员函数的用户名：${name}")
//    }
}

//扩展函数
fun User.Print() {   //User表示函数的接受者，也就是函数扩展的对象。。。Print表示为函数的名称
    println("扩展函数的用户名：${name}")
}

//fun main() {
//    var user = User("周杰伦")
//    user.Print()
//}


//----------- 扩展函数是静态解析的 -----------
//扩展函数是静态解析的，在调用扩展函数时，具体被调用的的是哪一个函数，由调用函数的的对象表达式来决定的，而不是动态的类型决定的:
open class C

class D : C()


fun C.foo() = "c"

fun D.foo() = "d"

fun printFoo(c: C) {
    println("------${c.foo()}")
}

//fun main() {
//    printFoo(D())
//}


//----------- 伴生对象的扩展 -----------
//如果一个类定义有一个伴生对象 ，你也可以为伴生对象定义扩展函数和属性。
//伴生对象内的成员相当于 Java 中的静态成员，其生命周期伴随类始终，在伴生对象内部可以定义变量和函数，这些变量和函数可以直接用类名引用。
class MyClass {
    companion object {} //将被称为 "Companion"
}

fun MyClass.Companion.foo() {
    println("伴生对象的扩展函数")
}

val MyClass.Companion.no: Int
    get() = 10

//fun main() {
//    println("no:${MyClass.no}")
//    MyClass.foo()
//}


//----------- 扩展声明为成员 -----------
//在一个类内部你可以为另一个类声明扩展(在 CC 类内，创建了 DD 类的扩展。此时，CC 被成为分发接受者，而 DD 为扩展接受者。)
//假如在调用某一个函数，而该函数在分发接受者和扩展接受者均存在，则以扩展接收者优先，要引用分发接收者的成员你可以使用限定的 this 语法。
class DD {
    fun bar() {
        println("DD 的 bar")
    }
}

class CC {
    fun bar() { //与DD类的bar同名
        println("CC 的 bar")
    }


    fun DD.foo() {
        bar()
        this@CC.bar()
    }

    fun caller(d: DD) {
        d.foo()
    }
}

fun main() {
    val d: DD = DD()

    val c: CC = CC()
    c.caller(d)
}







