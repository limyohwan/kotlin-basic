package com.yohwan.study.kotlininaction.chapter10

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

fun main(args: Array<String>) {
    val person = Person("Alice", 20)
    val om = ObjectMapper()
    om.registerModules(kotlinModule())
    println(om.writeValueAsString(person))

    val json = """{"name":"Alice","age":20}"""
    println(om.readValue(json, Person::class.java))
}

@Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)")) // Deprecated 사용 예제
fun remove(index: Int) {

}

// @MyAnnotation(MyClass::class) 클래스를 애노테이션 인자로 지정할 때 클래스 이름을 뒤에 넣어야 함
// 배열을 인자로 지정하려면 @RequestMapping(path = arrayOf("/foo", "/bar")) 처럼 arrayOf 함수를 사용함
// 가변 인자의 경우 @JavaAnnotationWithArrayValue("abc", "foo", "bar") 처럼 arrayOf 함수를 쓰지 않아도 됨

// 애노테이션 인자를 컴파일 시점에 알 수 있어야 하므로 프로퍼티를 애노테이션 인자로 사용하려면 const 변경자를 붙여함 -> const가 붙은 프로퍼티는 파일의 맨 위나 object 안에 선언해야 함, 원시 타입이나 String으로 초기화해야함
//const val TEST_TIMEOUT = 100L
//@Test(timeout = TEST_TIMEOUT) fun testMethod() {}

// 코틀린 선언과 대응하는 여러 자바 선언에 각각 애노테이션을 붙여야할 경우 사용함
//class HasTempFolder {
//    @get:Rule // 프로퍼티가 아니라 getter에 애노테이션이 붙음
//    val folder = TemporaryFolder()
//
//    @Test
//    fun testUsingTempFolder() {
//        val createdFile = folder.newFile("myfile.txt")
//        val createdFolder = folder.newFolder("subfolder")
//    }
//}
// @property: = 프로퍼티 전체, 자바에서 선언된 애노테이션에는 이 사용 지점 대상을 사용할 수 없음
// @filed: = 프로퍼티에 의해 생성되는 필드
// @get: = 프로퍼티 게터
// @set: = 프로퍼티 세터
// @receiver: = 확장 함수나 프로퍼티의 수신 객체 파라미터
// @param: = 생성자 파라미터
// @setparam: = 세터 파라미터
// @delegate: = 위임 프로퍼티의 위임 인스턴스를 담아둔 필드
// @file: = 파일 안에 선언된 최상위 함수와 프로퍼티를 담아두는 클래스

fun test(list: List<*>) {
    @Suppress("UNCHECKED_CAST") // 코틀린은 애노테이션 인자로 클래스나 함수 선언이나 타입 외에 임의의 식을 허용함
    val strings = list as List<String>
}

// @Volatile = 자바의 volatile 키워드를 대신함
// @Strictfp = 자바의 strictfp 키워드를 대신함
// @JvmName = 코틀린 선언이 만들어내는 자바 필드나 메소드 이름을 변경함
// @JvmStatic = 메소드, 객체 선언, 동반 객체에 ㅅ적용하면 그 요소가 자바 정적 메소드로 노출됨
// @JvmOverloads =  디폴트 파라미터 값이 있는 함수에 대해 컴파일러가 자동으로 오버로딩한 함수를 생성해줌
// @JvmField = 프로퍼티에 사용하면 게터나 세터가 없는 공개된 자바 필드로 프로퍼티를 노출시킴

data class Person(
    val name: String,
    val age: Int
)

// 밑에 코드와 같음
//annotation class JsonName(val name: String) // 코틀린은 name, name은 첫번째 인자로 @JsonName(name = "first_name")은 @JsonName("first_name")과 같음

/*
public @interface JsonName {
    String value(); // 자바는  value, value 메서드는 특별하며 어떤 애노테이션을 적용할 때 value를 제외한 모든 애트리뷰트에는 이름을 명시해야함
}
*/

// 책에는 jkid(https://github.com/yole/jkid) 라이브러리를 사용하지만 나는 Jackson을 사용할 예정

/*
* @Retention 애노테이션
* 클래스를 소스 수준에서만 유지할지, .class 파일에 저장할지, 실행 시점에 리플렉션을 사용해 접글할 수 있게 할지를 지정하는 메타애노테이션
* 자바 컴파일러는 기본적으로 .class 파일에는 저잫아지만 런타임에는 사용할 수 없음
* 하지만 코틀린에서는 런타임에도 사용할 수 있어야 하므로 기본적으로 RUNTIME을 지정함
*
* @Target 애노테이션
* 지정하지 않으면 모든 선언에 적용할 수 있음
* 클래스, 파일, 프로퍼티, 프로퍼티 접근자, 타입, 식 등에 대한 이넘(Enum) 정의가 들어가 있음
*
* 코틀린 애노테이션을 적용하는 법은 자바와 매우 유사하며 자바보다 더 넓은 대상(파일, 식)으로 적용할 수 있음
* 애노테이션 인자로 원시 타입 값, 문자열, 이넘, 클래스 참조, 다른 애노테이션 클래스의 인스턴스, 여러 유형의 값으로 이뤄진 배열을 사용할 수 있음
* @get:Rule을 사용해 애노테이션 사용 대상을 명시하면 코틀린 선언이 여러 가지 바이트코드 요소를 만들어내는 경우 정확히 어떤 부분에 애노테이션을 적용할지 지정할 수 있음
* 애노테이션 클래스를 정의할 때 본문이 없고 주 생성자의 모든 파라미터를 val 프로퍼티로 표시한 코틀린 클래스를 사용함
* 메타애노테이션을 사용해 대상, 애노테이션 유지 방식 등 여러 특성을 지정할 수 있음
* */
