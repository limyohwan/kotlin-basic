package com.yohwan.study.kotlininaction.chapter2

class Person2(
    val name: String, // val은 읽기 전용 프로퍼티로 비공개 필드와 필드를 읽는 단순한 공개 게터를 만들어냄
    var isMarried: Boolean // var은 쓸 수 있는 프로퍼티로 코틀린은 비공개 필드. 공개 게터, 공개 세터를 만들어냄
)
// 코드가 없이 데이터만 저장하는 클래스 = 값 객체(value object)
// 코틀린의 기본 가시성은 public임

// 자바 프로퍼티 = 필드(private)와 접근자(getter,setter)를 묶어서 제공함
// person.name(kotlin)  === person.getName()(java)
// person.isMarried === person.isMarried()
// person.isMarried = false === person.setMarried(false)