package com.yohwan.study.kotlininaction.chapter10

import kotlin.reflect.KFunction2
import kotlin.reflect.full.memberProperties

fun main(args: Array<String>) {
    val person = Person("Alice", 29)
    val kClass = person.javaClass.kotlin // .javaClass는 java.lang.Object.getClass()이며 자바클래스를 얻은 후 .kotlin 확장 프로퍼티를 통해 자바에서 코틀린 리플렉션 API로 옮겨올 수 있음
    println(kClass.simpleName)

    kClass.memberProperties.forEach {
        println(it.name)
    }

    val kFunction = ::foo // 함수 참조 5.1.5
    kFunction.call(42)

    val kFunction2: KFunction2<Int, Int, Int> = ::sum
    println(kFunction2.invoke(1, 2) + kFunction2(3, 4))

//    kFunction2(1) 에러 발생
//    call메소드는 타입 안전성을 보장해주지 않지만 kFunction을 사용하여 파라미터와 반환 값을 설정하여 타입 안전성을 보장할 수 있음

    val memberProperty = Person::age
    println(memberProperty.get(person))


}

fun foo(x:Int) = println(x)

fun sum(x: Int, y: Int) = x + y

/*
* Reflection = 실행 시점에 (동적으로) 객체의 프로퍼티와 메소드에 접근할 수 있게 해줌
* 타입과 관계없이 객체를 다뤄야 하거나 객체가 제공하는 메소드나 프로퍼치 이름을 오직 실행시점에 알 수 있는 경우 사용
* java.lang.reflect와 kotlin.reflect 패키지가 있으며 코틀린 리플렉션의 기능이 충분하지만 복잡한 기능이 필요할때는 자바 리플렉션을 사용해야함
*
* KClass: 클래스 안에 잇는 모든 선언을 열거하고 각 선언에 접근하거나 클래스의 상위 클래스를 얻는 등의 작업이 가능함
* MyClass::class라는 식을 쓰면 KClass의 인스턴스를 얻을 수 있음
*
* KClass에는 모든 멤법의 목록이 KCallable 인스턴스의 컬렉션이다
* KCallable은 함수와 프로퍼티를 아우르는 공통 상위 인터페이스임
* 그 안에는 call메소드가 들어있고 이를 사용하여 함수나 프로퍼티의 게터를 호출할 수 있음
*
* 리플렉션을 통해 실행 시점에 객체의 메소드와 프로퍼티를 열거하고 접근할수있음
* 리플렉션에 클래스(KClass), 함수(KFunction) 등 여러 종류의 선언을 표현하는 인터페이스가 있음
* 클래스를 컴파일 시점에 알고 있다면 KClass인스턴스를 얻기 위해 ClassName::class를 사용함
* 런타임 시점에는 obj.javaClass.kotlin을 사용함
* KFunction과 KProperty 인터페이스 모두 KCallable(call 메서드 제공)을 확장함
* KCallable.callBy 메서드를 사용하면 메소드를 호출하면서 디폴트 파라미터 값을 사용할 수 있음
* KFunction0, Kfunction1 등의 인터페이스는 모두 파라미터 수가 다른 함수를 표현하며 invoke 메소드를 사용해 함수를 호출함
* KProperty0는 최상위 프로퍼티나 변수, KProperty1은 수신 객체가 있는 프로퍼티에 접근할 때 쓰는 인터페이스
* KMutalbleProperty0, KMutalbleProperty1은 각각 KProperty0, KProperty1을 확장하여 set 메소드를 통해 프로퍼티 값을 변경할 수 있게 해줌
* */
