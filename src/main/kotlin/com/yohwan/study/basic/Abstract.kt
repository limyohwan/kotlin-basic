package com.yohwan.study.basic

fun main() {

}

abstract class Animal {
    open fun move() { // open 키워드를 선언해야 자식 클래스에서 오버라이드가 가능함
        println("이동")
    }
}

class Dog : Animal() {
    override fun move() {
        println("껑충")
    }
}

class Cat : Animal() {
    override fun move() {
        println("살금")
    }
}

class Hero

// class SuperMan : Hero() // 그냥 클래스는 상속이 불가능함

open class Hero2

class SuperMan2 : Hero2() // open 클래스는 상속이 가능함

