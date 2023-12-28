package com.yohwan.study.kotlininaction.chapter9

// 변성(variance) = List<String> 과 List<Any>와 같이 기저 타입이 같고 타입 인자가 다른 여러 타입이 서로 어떤 관계가 있는지 설명하는 개념
// 무공변(invariant) = 제네릭 타입을 인스턴스화할 때 타입 인자로 서로 다른 타입이 들어가면 인스턴스 타입 사이의 하위 타입 관계가 성립하지 않는 제네릭 타입
// 공변(covariant) = A가 B의 하위 타입이면 List<A>는 List<B>의 하위 타입인 클래스나 인터페이스
fun main(args: Array<String>) {
    printContents(arrayOf("abc", "bac"))

    val strings = mutableListOf("abc", "bac")
//    addAnswer(strings) // Any 타입과 맞지 않음
    println(strings.maxBy { it.length })

    // 널이 될 수 없는 타입은 널이 될 수 있는 타입의 하위 타입임
    val s: String = "abc"
    val t: String? = s
}

fun printContents(list: Array<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

fun test(i: Int) {
    val n: Number = i // Int는 Number의 하위 타입이어서 컴파일됨

    fun f(s: String) {}
//    f(i) // Int는 String의 하위 타입이 아니여서 컴파일 안됨
}
// 하위 타입은 하위 클래스와 근본적으로 같음

interface Producer<out T> { // 클래스가 T에 대해 공변적이라고 선언함
    fun produce() : T
}

open class Animal() {
    fun feed() {
        println("feed")
    }
}

class Cat : Animal() {
    fun cleanLitter() {
        println("cleanLitter")
    }
}

class Herd<T : Animal> { // 타입을 무공변성으로 지정
    val size: Int get() = 1
    operator fun get(i: Int) : T { return Animal() as T } // Cat을 리턴할 수 없음
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
//        feedAll(cats) // type missmatch 에러 발생 -> 이를 해결하기 위해 Herd를 공변적으로 변경함
    }
}

class Herd2<out T : Animal> { // 타입을 공변성으로 지정
    val size: Int get() = 1
    operator fun get(i: Int) : T { return Animal() as T } // Cat을 리턴할 수 없음
}

fun feedAll2(animals: Herd2<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun takeCareOfCats2(cats: Herd2<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
//        feedAll(cats) // type missmatch 에러 발생 -> 이를 해결하기 위해 Herd를 공변적으로 변경함
        feedAll2(cats) // 위와 달리 캐스팅할 필요가 없어짐
    }
}
