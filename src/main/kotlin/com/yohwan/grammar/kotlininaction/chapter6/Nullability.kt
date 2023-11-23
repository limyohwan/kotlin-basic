package com.yohwan.grammar.kotlininaction.chapter6

import java.util.*

fun main(args: Array<String>) {
//    strLen(null) // 컴파일 에러 발생
    strLenSafe(null)
    printAllCaps("abc")
    printAllCaps(null)

    val ceo = Employee("Boss", null)
    val developer = Employee("Yohwan", ceo)
    println(managerName(developer))
    println(managerName(ceo))

    val person = Person("Yohwan", null)
    println(person.countryName())

    println(strLenSafeElvis("abc"))
    println(strLenSafeElvis(null))

    println(person.countryNameElvis())

    val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val yohwan = Person("Yohwan", jetbrains)
    printShippingLabel(yohwan)
//    printShippingLabel(person) // exception 발생

}

fun strLen(s: String) = s.length

fun strLenSafe(s: String?) = if (s != null) s.length else 0 // null 검사를 추가하면 됨

fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase(Locale.getDefault()) // allCaps는 null일 수 있음
    println(allCaps)
}

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name // ?.를 이용해 안전한 호출을 할 수 있음

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
    val country = this.company?.address?.country // 여러 안전한 호출 연산자를 인쇄해 사용함
    return if (country != null) country else "Unknown"
}

fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No address") // 주소가 없으면 예외 발생, Elvis와 throw를 활용해 전제 조건(precondition)을 검사하는 경우 유용함
    with (address) { // address는 null이 아님
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun foo(s: String?) {
    val t: String = s ?: "" // s가 null이면 t = 빈문자열 "" 임
}

fun strLenSafeElvis(s: String?) = s?.length ?: 0

fun Person.countryNameElvis(): String = company?.address?.country ?: "Unknown"