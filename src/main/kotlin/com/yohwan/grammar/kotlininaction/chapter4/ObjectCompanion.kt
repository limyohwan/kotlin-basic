package com.yohwan.grammar.kotlininaction.chapter4

// 동반 객체(companion object) = 팩토리 메소드와 정적 멤버가 들어갈 장소
// 코틀린 클래스 안에는 정적인 멤버가 없으며 자바의 static 키워드를 지원하지 않음, 그 대신 패키지 수준의 최상위 함수와 객체 선언을 활용함
// 대부분의 경우에는 최상위 함수를 활용하는 편을 권장하지만 이를 활용할 수 없을 때 사용하는 방안이 동반 객체임
fun main(args: Array<String>) {
    A.bar()

    val subscribingBook = Book2.newSubscribingBook("bob@gmail.com")
    val accountBook = Book2.Companion.newAccountBook(4)
    println(subscribingBook.name)

    val slave = Slave.Loader.fromJSON("{name : 'Jack'}")
    println(slave.name)

    val b = Beverage.fromJSON("{}")
}

class A {
    companion object {
        fun bar() {
            println("companion object called")
        }
    }
}

class Book { // 부생성자가 여러개있는 클래스를 팩토리 메소드를 활영하여 만들 수 있음
    val name: String

    constructor(email: String) {
        name = email.substringBefore('@')
    }

    constructor(accountId: Int) {
        name = accountId.toString()
    }
}

class Book2 private constructor(val name: String) { // 주 생성자를 비공개로 만듬
    companion object { // 생성자를 통해서는 인스턴스를 만들 수 없고 팩토리 메소드를 통해서만 만들 수 있음
        fun newSubscribingBook(email: String) = Book2(email.substringBefore('@'))

        fun newAccountBook(accountId: Int) = Book2(accountId.toString())
    }
}

class Slave(val name: String) {
    companion object Loader { // 동반 객체에 이름을 줄 수 있음, 특별한 이름을 지정하지 않으면 이름은 자동으로 Companion이 됨
        fun fromJSON(jsonText: String) : Slave = Slave(jsonText)
    }
}

interface JSONFactory<T> {
    fun fromJSON(jsonText: String) : T
}

class Slave2(val name: String) {
    companion object : JSONFactory<Slave2> { // 동반 객체가 인터페이스를 구현할 수 있음
        override fun fromJSON(jsonText: String): Slave2 = Slave2(jsonText) // 클래스의 동반 객체는 일반 객체와 비슷한 방식으로 클래스에 정의된 인스턴스를 가리키는 정적 필드로 컴파일됨
    }
}

// 비즈니스 로직 모듈
class Beverage(val name: String, val kind: String) {
    companion object { // 비어있는 동반 객체 선언

    }
}

// 클라이언트 / 서버 통신 모듈
fun Beverage.Companion.fromJSON(json: String) : Beverage { // 확장 함수 선언
    return Beverage("포카리", "이온음료") // 동반 객체 안에서 fromJSON 함수를 정의한 것처럼 사용할 수 있음, 클래스 멤버 함수처럼 보이지만 실제로는 멤버 함수가 아님, 빈 동반 객체가 꼭 있어야함
}