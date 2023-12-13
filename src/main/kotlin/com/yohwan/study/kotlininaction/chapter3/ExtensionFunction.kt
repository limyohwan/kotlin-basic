package com.yohwan.study.kotlininaction.chapter3

fun main(args: Array<String>) {
    println("임요환".lastChar())
}

//  수신객체타입 = String          수신객체 = this
fun String.lastChar(): Char = this[this.length - 1]
// 기존 API를 재정의하지 않고도 확장함수(extension function)를 통해 새로운 기능을 사용할 수 있음
// 확장 함수는 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있찌만 해당 클래스의 밖에서 선언됨 함수임
// 확장할 클래스의 이름(수신 객체 타입, receiver type)이라 부르며 확장 함수가 호출되는 대상이 되는 값을 수신 객체(receiver object)라고 부름


//fun String.lastChar(): Char = get(length -1)
// this를 생략할 수 있음
// 확장함수안에서는 클래스 내부에서만 사용하는 비공개(private) 멤버나 보호된(protected) 멤버를 사용할 수 없음