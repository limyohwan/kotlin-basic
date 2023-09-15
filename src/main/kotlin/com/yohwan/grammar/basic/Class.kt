package com.yohwan.grammar.basic

fun main() {
    val john = Person("John", 20, "programmer")
    println(john.name)
    println(john.age)
    // println(john.job) private은 접근 불가
    // john.hobby = "농구" private set인 경우 외부에서 접근 불가능함
    println(john.hobby)

    val john2 = Person("John", 20, "programmer")
    println(john == john2) // false

    val john3 = Person2("John", 20, "programmer")
    val john4 = Person2("John", 20, "programmer")
    println(john3 == john4) // true   data class이기 때문에 equals, hashcode가 알아서 적용됨, toString도 재정의해주지 않아도 됨

    // john.name = "yohwan" val이라 컴파일 에러
    john.age = 21
}

class Person(
    val name: String,
    var age: Int,
    private val job: String
) { //()안의 값들은 생성자에 사용하는 값들
    var hobby = "축구"
        private set
        get() = "취미 : $field" // getter 재정의

    init {
        println("constructor init") // 생성자에서 추가적인 작업을 해야하는 경우
    }

    fun some() {
        hobby = "농구"
    }
}

data class Person2(
    val name: String,
    var age: Int,
    private val job: String
)

