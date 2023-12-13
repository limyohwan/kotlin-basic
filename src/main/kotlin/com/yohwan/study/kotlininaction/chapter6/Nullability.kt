package com.yohwan.study.kotlininaction.chapter6

import java.awt.event.ActionEvent
import java.util.*
import javax.swing.AbstractAction
import javax.swing.JList

// 6.1.1 ~ 6.1.7
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

    val h1 = Human("yohwan", "lim")
    val h2 = Human("yohwan", "lim")
    println(h1 == h2) // == 연산자는 equals를 호출함
    println(h1.equals(42))

//    ignoreNulls(null) // NPE 발생

    val email: String? = "yohwan@naver.com"
    email?.let { sendEmailTo(it) } // let을 사용하여 null일 수 있는지 검사를 하고 그 결과를 변수에 넣는 작업을 간단히 처리할 수 있음

    val undefinedEmail: String? = null
    undefinedEmail?.let { sendEmailTo(it) }

    val undefinedPerson: Person? = getTheBestPersonInTheWorld()
    if(undefinedPerson != null) sendEmailTo(undefinedPerson.name)

    getTheBestPersonInTheWorld()?.let { sendEmailTo(it.name) } // let을 활용하면 위와같은 복잡한 식을 아래와 같이 간단하게 처리할 수 있음, let 호출이 중첩되어 코드가 복잡해지는 경우 if문을 사용해 한번에 처리하는 편이 나음
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
    // this.company!!.address!!.country -> 이런식의 코드는 절대 작성하지 말자
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

class Human(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherHuman = other as? Human ?: return false // 타입이 서로 일치하지 않으면 false를 반환함

        return otherHuman.firstName == firstName && otherHuman.lastName == lastName // 안전한 캐스트를 하고나면 otherHuman이 Human 타입으로 스마트 캐스트됨
    }

    override fun hashCode(): Int =
        firstName.hashCode() * 37 + lastName.hashCode()
}

fun ignoreNulls(s: String?) {
    val sNotNull: String = s!! // null이 아님을 단언함
    println(sNotNull.length)
}

class CopyRowAction(val list: JList<String>) : AbstractAction() {
    override fun isEnabled() : Boolean =
        list.selectedValue != null

    override fun actionPerformed(e: ActionEvent?) { // actionPerformed는 isEnabled가 true인 경우에만 호출됨
        val value = list.selectedValue!! // val value = list.selectedValue ?: return 처럼 non-null한 값을 얻어야하는데 이러면 selectedValue가 null일 시 조기 종료 될 수 있으므로 value에 단언을 주어 항상 null이 아니라고 단언함
        // 클립보드 복사 로직 ...
    }
}

fun sendEmailTo(email: String) {
    println("sending email to $email")
}

fun getTheBestPersonInTheWorld() : Person? = null