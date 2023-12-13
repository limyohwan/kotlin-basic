package com.yohwan.study.kotlininaction.chapter5

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 23), Person("Bob", 28))

    // 같은 결과값
    findTheOldest(people)
    println(people.maxBy { it.age }) // 컴파일러가 추론할 수 있는 경우 it 사용 가능
    println(people.maxBy(Person::age))
    println(people.maxBy({ p: Person -> p.age }))
    println(people.maxBy() { p: Person -> p.age })
    println(people.maxBy { p: Person -> p.age }) // 파라미터 타입 명시
    println(people.maxBy { p -> p.age }) // 파라미터 타입 생략, 컴파일러 추론
    val getAge = { p: Person -> p.age } // 람다를 변수에 저장할 때에는 파라미터 타입을 추론하지 못하므로 꼭 파라미터 타입을 명시해야 함
    val getAge2 = Person::age // 클래스::멤버 = 멤버 참조 문법, 위의 람다식을 간결하게 표현하는 방법
    println(people.maxBy(getAge))

    // 같은 결과값
    println(people.joinToString(separator = " ", transform = { p: Person -> p.name }))
    println(people.joinToString(" ") { p: Person -> p.name })

    val sum = { x: Int, y: Int -> x + y}
    println(sum(1,2))
    run { println(42) }

    val printNumber = { println(52) }
    printNumber()

    val sum2 = { x: Int, y: Int ->
        println("computing the sum of $x and $y")
        x + y
    }
    println(sum2(10,20))

    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessageWithPrefix(errors, "Error :")

    val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
    printProblemCounts(responses)

    // ex1
    val counter = Ref(0)
    val inc = { counter.value++ } // 공식적으로는 변경 불가능한 변수를 포획했지만 그 변수가 가리키는 객체의 필드 값을 바꿀 수 있음

    // ex2
    var counter2 = 0
    val inc2 = { counter2++ }
    // ex1은 ex2가 작동하는 내부 모습을 보여줌

    run(::salute) // 최상위 함수인 salute를 참조함

    val createPerson = ::Person // 생성자 참조를 사용하여 클래스 생성 작업을 연기하거나 저장해둘 수 있음, Person의 인스턴스를 만드는 동작을 값으로 저장
    val p = createPerson("Alice", 20)
    println(p)

    val predicate = Person::isAdult // 확장 함수도 멤버 함수와 똑같은 방식으로 참조 가능

    val dmitry = Person("Dmitry", 34)
    val personAgeFunction = Person::age
    println(personAgeFunction(dmitry))

    val dmitryAgeFunction = p::age // 바운드 멤버 참조, 멤버 참조를 생성할 때 클래스 인스턴스를 함께 저장한 다음 나중에 그 인스턴스에 대해 멤버를 호출해줌
    println(dmitryAgeFunction())
}

data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }

    println(theOldest)
}

fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it") // 람다 안에서 함수의 파라미터(prefix)를 사용 가능함
    }
}

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var severErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++ // 람다 안에서 람다 밖의 변수 값을 변경할 수 있음, 코틀린 람다 안에서는 파이널 변수가 아닌 변수에 접근 가능함, 람다 안에서 사용하는 외부 변수를 람다가 포획한 변수라고 부름
        } else if (it.startsWith("5")) {
            severErrors++
        }
    }

    println(" $clientErrors client errors, $severErrors server errors")
}

class Ref<T>(var value: T) // 변경 가능한 변수를 포획하는 방법을 보여주기 위한 클래스

fun salute() = println("Salute!")

fun Person.isAdult() = age >= 21