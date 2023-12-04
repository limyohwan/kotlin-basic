package com.yohwan.grammar.kotlininaction.chapter7

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    val foo = Foo()
    val oldValue = foo.p
    println(oldValue)
    foo.p = "Updated value"
    println(foo.p)
    // 코틀린 라이브러리는 프로퍼티 위임을 사용해 프로퍼티 초기화를 지연시킬 수 있음

    val u = User("yohwan")
    u.emails
    u.emails

    val e = Employee("yohwan", 29, 2000)
    e.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
        }
    )
    e.age = 30
    e.salary = 2100

    val e2 = Employee2("yohwan", 19, 200)
    e2.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
        }
    )
    e2.age = 20
    e2.salary = 210

    val e3 = Employee3("yohwan", 9, 20)
    e3.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
        }
    )
    e3.age = 10
    e3.salary = 21

    val e4 = Employee4("yohwan", 3, 2)
    e4.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
        }
    )
    e4.age = 4
    e4.salary = 3

    val s = Student()
    val data = mapOf("name" to "yohwan", "company" to "x")
    for ((attrName, value) in data) {
        s.setAttribute(attrName,value)
    }
    println(s.name)
}

class Foo {
    var p: String by Delegate() // by를 사용해 프로퍼티와 위임 객체를 연결함
}

// foo가 컴파일 되었을 때 예시
//class Foo {
//    private val delegate = Delegate() // 컴파일러가 생성한 도우미 프로퍼티
//    var p: String
//    set(value: String) = delegate.setValue(..., value) // p 프로퍼티를 위해 컴파일러가 생성한 접근자는 delegate의 getValue와 setValue 메서드를 호출함
//    get() = delegate.getValue(...)
//}

class Delegate {
    private var storedValue: String = "Initial value" // 초기값 설정

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String { // 게터를 구현하는 로직
        println("Start - Get value: $storedValue")
        return storedValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) { // 세터를 구현하는 로직
        println("Start - Set value: $value")
        storedValue = value
    }
}

class Email(id: String, url: String)

fun loadEmails(user: User) : List<Email> {
    println("${user.name}의 이메일")
    return listOf(Email("dyghks7102", "naver.com"))
}

//class User(val name: String) {
//    // 지연 초기화해야하는 프로퍼티가 많아지면 뒷받침하는 프로퍼티들이 지저분하게 생기며 이 구현은 스레드 안전하지 않음
//    private var _emails: List<Email>? = null // 데이터를 저장하고 emails에 위임, 뒷받침하는 프로퍼티(backing property) 기법
//    val emails: List<Email>
//        get() {
//            if (_emails == null) {
//                _emails = loadEmails(this) // 최초 접근시 이메일 가져옴
//            }
//            return _emails!! // 저장해둔 데이터가 있으면 그대로 반환
//        }
//}

class User(val name: String) {
    val emails by lazy { loadEmails(this) } // lazy를 by 키워드와 함께 사용해 위임 프로퍼티를 만들 수 있음
}
// lazy 함수는 스레드 안전함, 동기화에 사용할 락을 lazy 함수에 전달할 수도 있고 다중 스레드 환경에서 사용하지 않을 프로퍼티를 위해 lazy 함수가 동기화 하지 못하게 막을 수도 있음

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Employee(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}

class ObservableProperty2( // employee 버전을 맞추기위해 숫자 변경
    val propName: String, var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    fun getValue() : Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class Employee2(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    val _age = ObservableProperty2("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }

    val _salary = ObservableProperty2("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) { _salary.setValue(value) }
}

class ObservableProperty3(
    var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(e: Employee3, prop: KProperty<*>) : Int = propValue

    operator fun setValue(e: Employee3, property: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(property.name, oldValue, newValue)
    }
}

class Employee3(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty3(age, changeSupport)
    var salary: Int by ObservableProperty3(salary, changeSupport)
}

class Employee4(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private  val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

// 위임 프로퍼티가 있으면 컴파일러가 모든 프로퍼티 접근자 안에 getValue와 setValue 호출 코드를 생성해줌

class Student {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

//    val name: String
//        get() = _attributes["name"]!! // 수동으로 맵에서 꺼냄

    val name: String by _attributes // 위임 프로퍼티로 맵을 사용
}