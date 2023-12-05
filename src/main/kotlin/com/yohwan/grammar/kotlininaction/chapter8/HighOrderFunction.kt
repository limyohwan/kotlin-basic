package com.yohwan.grammar.kotlininaction.chapter8

// 고차 함수(hight order function) = 람다를 인자로 받거나 반환하는 함수
fun main(args: Array<String>) {
    val sum1 = { x: Int, y: Int -> x + y}
    val action1 = { println(42)}

    val sum2: (Int, Int) -> Int = { x, y -> x + y} // Int 파라미터 2개를 받아 Int 값을 반환하는 함수
    val action2: () -> Unit = { println(42) } // 아무 인자도 받지 않고 아무 값도 반환하지 않는 함수

    val canReturnNull: (Int, Int) -> Int? = { x, y -> null } // 널이 될 수 있는 반환타입
    var funOrNull: ((Int, Int) -> Int)? = null // 널이 될 수 있는 함수타입 변수

    var url = "http://kotl.in"
    performRequest(url) { code, content -> /*...*/} // api에서 제공하는 이름을 람다에 사용
    performRequest(url) { code, page -> /*...*/} // 원하는 이름도 사용 가능

    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }

    println("ab1c".filter { it in 'a'..'z' })
}

fun performRequest(
    url: String,
    callback: (code: Int, content: String) -> Unit // 함수 타입 파라미터에 이름을 붙임
) {
    /*...*/
}

fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("result = $result")
}

fun String.filter(predicate: (Char) -> Boolean) : String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) {
            sb.append(element)
        }
    }
    return sb.toString()
}

fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

/* 자바에서 코틀린 사용
컴파일된 코드 안에서 함수 타입은 일반 인터페이스로 바뀜
processTheAnswer(number -> number + 1);

processTheAnswer(
    new Function1<Integer, Integer>() {
        @Override
        public Integer invoke(Integer number) {
            System.out.println(number);
            return number + 1;
        }
    });

List<String> strings = new ArrayList<>();
strings.add("42")
CollectionsKt.forEach(strings, s -> // Strings는 확장함수의 수신 객체 <- 코틀린 표준 라이브러리에서 가져온 함수를 자바 코드에서 호출할 수 있음
    System.out.println(s);
    return Unit.INSTANCE; // Unit 타입의 값을 명시적으로 반환해야함
});
 */