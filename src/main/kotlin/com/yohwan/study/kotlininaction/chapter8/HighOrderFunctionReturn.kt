package com.yohwan.study.kotlininaction.chapter8

fun main(args: Array<String>) {
    val pets = listOf(Pet("alice", 29), Pet("bob", 31))
    lookForAlice(pets)
    lookForAlice2(pets)
    lookForAlice3(pets)
    lookForAlice4(pets)

    // 레이블이 붙은 this 식
    println(StringBuilder().apply sb@{// this@sb를 통해 이 람다의 묵시적 수신 객체에 접근할 수 있음
        listOf(1,2,3).apply {
            this@sb.append(this.toString()) // 모든 묵시적 수신 객체를 사용할수 있으며 바깥쪽 묵시적 수신 객체에 접근할 때는 레이블을 명시해야함
        }
    })

    lookForAlice5(pets)
}

data class Pet(val name: String, val age: Int)

fun lookForAlice(pets: List<Pet>) {
    for (pet in pets) {
        if (pet.name == "alice") {
            println("Found")
            return
        }
    }
    println("alice is not found")
}

fun lookForAlice2(pets: List<Pet>) { // forEach로 변경
    pets.forEach {
        if (it.name == "alice") {
            println("Found")
            return // 람다 안에서 return을 사용하면 람다로부터만 반환되는게 아니라 람다를 호출하는 함수가 실행을 끝냄,
            // 자신을 둘러싸고있는 블록보다 더 바깥에 있는 다른 블록을 반환하게 만드는 return문을 넌로컬(non-local) return 이라고 부름
        }
    }
    println("alice is not found")
}

fun lookForAlice3(pets: List<Pet>) {
    pets.forEach label@{
        if (it.name == "alice") return@label // 람다식에서의 로컬 리턴 사용법, 해당 반복문을 종료시킴
    }
    println("alice might be somewhere")
}

fun lookForAlice4(pets: List<Pet>) {
    pets.forEach {
        if (it.name == "alice") return@forEach // 람다식에서의 로컬 리턴 사용법, 해당 반복문을 종료시킴
    }
    // 람다 식의 레이블을 명시하면 함수 이름을 레이블로 사용할 수 없음, 람다식에는 레이블이 2개 이상 붙을 수 없음
    println("alice might be somewhere")
}

// 무명함수 = 함수 이름이나 파라미터 타입을 생략할 수 있음
fun lookForAlice5(pets: List<Pet>) {
    pets.forEach(fun (pet) { // 람다식 대신 무명함수를 사용
        if (pet.name == "alice") return // 이 return은 가장 가까운 함수를 가리키는데 이 위치에서 가장 가까운 함수는 무명함수임
        println("${pet.name} is not alice")
    })

    pets.filter(fun (pet) : Boolean {
        return pet.age < 30
    })

    pets.filter(fun (pet) = pet.age < 30)
}