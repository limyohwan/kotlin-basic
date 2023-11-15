package com.yohwan.grammar.kotlininaction.chapter4

import java.io.File

// object 키워드를 사용하는 상황
// 1. 객체를 싱글턴으로 정의
// 2. 동반 객체(companion object)는 인스턴스 메소드는 아니지만 어떤 클래스와 관련있는 메소드와 팩토리 메소드를 담을 때 사용
// 3. 익명 내부 클래스 대신 사용

object Payroll { // 일반 클래스 인스턴스와 달리 싱글턴 객체는 객체 선언문이 있는 위치에서 생성자 호출없이 즉시 만들어짐
    val allEmployees = arrayListOf<Employee>()

    fun calculateSalary() {
        for (employee in allEmployees) {
            println(employee)
        }
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

data class Employee(val name: String) {
    object NameComparator : Comparator<Employee> { // 위와 같이 Comparator를 외부에 정의하는 것보다 사용하는 해당 클래스안에 선언하는 것이 바람직함
        override fun compare(o1: Employee, o2: Employee): Int =
            o1.name.compareTo(o2.name)
    }
}

fun main(args: Array<String>) {
    Payroll.allEmployees.add(Employee("임요환"))
    Payroll.calculateSalary()

    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))

    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator)) // object 클래스는 함수 인자로도 넘길 수 있음

    val employees = listOf(Employee("Bob"), Employee("Alice"))
    println(employees.sortedWith(Employee.NameComparator))
}