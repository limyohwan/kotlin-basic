package com.yohwan.study.basic

fun main() {
    val tiger: Animal = Tiger()
    val lion = Lion()

    if(tiger is Tiger) {
        println("I am tiger")
        tiger.run()
        tiger.move()
    }

    if(tiger is Animal) {
        println("I am Animal")
        tiger.move()
        // tiger.run() Animal 타입이기 때문에 run()은 없음
    }

    if(tiger is Lion) {
        println("I am lion")
    } else {
        println("I am not lion")
    }

    tiger as Lion // 강제형변환, 당연히 런타임시 에러 발생함

    // val box = Box<Int>(10)
    val box = Box(10) // 타입 추론을하기 떄문에 Int 생략 가능
    println(box.value)
}

interface Runnable {
    fun run()
}

class Tiger : Animal(), Runnable {
    override fun run() {
        println("run")
    }
}

class Lion : Animal(), Runnable {
    override fun run() {
        println("run")
    }
}

class Box<T>(var value: T)