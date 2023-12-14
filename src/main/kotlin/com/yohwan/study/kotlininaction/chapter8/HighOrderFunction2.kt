package com.yohwan.study.kotlininaction.chapter8

fun main(args: Array<String>) {
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    println("Shipping costs ${calculator(Order(3))}")

    val contacts = listOf(Person("Dmitry" , "Jemerov" , "123-4567"), Person("Svetlana", "Isakova", null))
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "Dm"
        onlyWithPhoneNumber = true
    }
    println(contacts.filter(contactListFilters.getPredicate())) // filter의 인자로 getPredicate의 반환 함수를 넘김

}

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery) : (Order) -> Double { // 함수를 반환하는 함수 선언, 일반적으로 함수가 함수를 반환하는 경우보다 함수가 함수를 인자로 받아야 하는 경우가 더 많음
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount }
    }

    return { order -> 1.2 * order.itemCount }
}

data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?
)

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean { // 함수를 반환하는 함수 정의
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }

        if(!onlyWithPhoneNumber) {
            return startsWithPrefix // 함수타입의 변수 반환
        }

        return { startsWithPrefix(it) && it.phoneNumber != null } // 람다 반환
    }
}