package com.yohwan.study.kotlininaction.chapter9

import kotlin.Comparable as Comparable1

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

    val anyComparator = Comparator<Any> {
        e1, e2 -> e1.hashCode() - e2.hashCode()
    }
    strings.sortedWith(anyComparator)
    // Comparator<Any>는 Comparator<String>의 하위 타입임
    // Any는 String의 상위 타입임
    // 타입 B(String)가 A(Any)의 하위타입인 경우 Consumer<A>가 Consumer<B>의 하위 타입인 관계가 성립하면 제네릭 클래스 Consumer<T>는 타입 인자 T에 대해 반공변이다
    // in 키워드는 이 키워드가 붙은 타입이 이 클래스 메소드 안으로 전달돼 메소드에 의해 소비된다는 뜻임
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

class Herd2<out T : Animal>(
    vararg animals: T, // vararg 생성자 파라미터는 인, 아웃 어느 쪽도 아님 -> 타입 파라미터가 out이여도 그 타입을 여전히 생서자 파라미터에 선언 가능함
//    var leadAnimal: T // val, var 생성자 파라미터는 게터와 세터를 정의하는 것과 같으며 읽기 전용은 아웃 위치, 변경 가능 프로퍼티는 인, 아웃 위치 모두에 해당하므로 T를 out으로 표시할 수 없음
) { // 타입을 공변성으로 지정, T에 붙은 out 키워드는 공변성(하위 타입 관계 유지), 사용 제한(T를 아웃 위치에서만 사용 가능)을 의미함
    val size: Int get() = 1
    // 아웃 위치, T를 반환 타입으로 사용, 공변적으로 선언해도 안전함
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

interface Transformer<T> {
    // 함수 파라미터 타입은 인 위치(소괄호 사이), 함수 반환 타아입은 아웃 위치(: 다음)에 있음
    fun transform(t: T) : T
}

// 코틀린의 List는 읽기 전용이며 get 메서드는 있지만 추가하거나 기존값을 변경하는 메서드는 없음, 따라서 T에 대해 공변적임
//interface List<out T> : Collection<T> {
//    operator fun get(index: Int) : T // 읽기 전용 메서드로 T를 반환하는 메서드만 정의함(따라서 항상 T는 아웃 위치에 사용됨)
//
//    fun subList(fromIndex: Int, toIndex: Int) : List<T> // 여기서도 T는 아웃위치에 있음
//}

// 코를린의 MutableList는 타입 파라미터 T에 대해 공변적인 클래스로 선언할 수 없음, T를 인자로 받아서 그 타입의 값을 반환하는 메서드가 있음
//interface MutableList<T> : List<T>, MutableCollection<T> {
//    override fun add(element: T) : Boolean // T가 인위치에 쓰이기 때문에 MutableList는 T에 대해 공변적일 수 없음
//}

// 코틀린의 Comparator는 T 타입을 소비하기만 하므로 인 위치에서만 사용함
//interface Comparator<in T> { // 그러므로 T앞에 in을 붙여야함
//    fun compare(e1: T, e2: T) : Int
//}

// 공변성
// Producer<out T>, 타입 인자의 하위 타입 관계가 제네릭 타입에서도 유지됨, Producer<Cat>은 Producer<Animal>의 하위 타입임, T를 아웃 위치에서만 사용 가능함
// 반공변성
// Consumer<in T>, 타입 인자의 하위 타입 관계가 제네릭 타입에서 뒤집힘, Consumer<Animal>은 Consumer<Cat>의 하위 타입임, T를 인 위치에서만 사용 가능함
// 무공변성
// MutableList<T>, 하위 타입 관계가 성립하지 않음, T를 아무 위치에서나 사용 가능함

interface Function1<in P, out R> { // P(함수 파라미터의 타입), R(함수 반환 타입)
    operator fun invoke(p: P) : R
}
// 자바는 이를 지원하지 않고 와일드 카드를 사용해 그때그때 변성을 지정해야 함