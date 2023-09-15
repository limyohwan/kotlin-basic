package com.yohwan.grammar.basic

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
}

interface Runnable {
    fun run()
}

class Tiger : Animal(), Runnable {
    override fun run() {
        TODO("Not yet implemented")
    }
}

class Lion : Animal(), Runnable {
    override fun run() {
        TODO("Not yet implemented")
    }
}