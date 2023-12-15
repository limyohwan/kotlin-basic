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

    val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/login", 12.0, OS.WINDOWS),
        SiteVisit("/signup", 8.0, OS.IOS),
        SiteVisit("/", 16.3, OS.ANDROID)
    )
    // OS.WINDOWS에만 적용되는 평균 값을 확장함수를 사용하여 OS를 인자로 받음
    val averageWindowsDuration = log
        .filter { it.os == OS.WINDOWS }
        .map(SiteVisit::duration)
        .average()
    println(averageWindowsDuration)
    // 확장함수를 사용하여 중복제거, 가독성 향상
    println(log.averageDurationFor(OS.WINDOWS))
    println(log.averageDurationFor(OS.MAC))

    val averageMobileDuration = log
        .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
        .map(SiteVisit::duration)
        .average()
    println(averageMobileDuration)
    // 고차 함수를 사용하여 중복제거, 코드의 일부분을 복사해 붙여 넣고 싶은 경우 그 코드를 람다로 만들어 중복을 제거할 수 있음
    println(log.averageDurationFor {
        it.os == OS.IOS && it.path == "/signup"
    })
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

data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

fun List<SiteVisit>.averageDurationFor(os: OS) =
    filter { it.os == os }.map(SiteVisit::duration).average()

fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()