package com.yohwan.grammar.kotlininaction.chapter3

// 반복하지 마라(DRY, Don't Repeat Yourself) DRY 원칙
fun main(args: Array<String>) {
    saveUser4(User(1, "", ""))
}

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) { // 중복이 생기는 코드
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("cant save user ${user.id}")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException("cant save user ${user.id}")
    }
    // save to db
}

fun saveUser2(user: User) {
    fun validate(user: User, value: String, fieldName: String) { // 로컬 함수를 사용하여 코드의 중복을 줄일 수 있음
        if (value.isEmpty()) {
            throw IllegalArgumentException("cant save user ${user.id}")
        }
    }

    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
    // save to db
}

fun saveUser3(user: User) {
    fun validate(value: String, fieldName: String) { // 이미 user 객체를 받았으므로 로컬 함수에서 이 값을 사용할 수 있음
        if (value.isEmpty()) {
            throw IllegalArgumentException("cant save user ${user.id}")
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
    // save to db
}

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) { // 검증 로직을 User의 확장함수로 만들어 더 개선할 수 있음
        if (value.isEmpty()) {
            throw IllegalArgumentException("cant save user $id")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser4(user: User) {
    user.validateBeforeSave() // 함수가 중첩되어 깊이가 깊어지면 코드를 읽기가 상당히 어려워지므로 한 단계만 함수를 중첩시키는 것을 권장함

    // save to db
}