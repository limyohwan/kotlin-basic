package com.yohwan.study.kotlininaction.chapter4

// 상속을 허용하지 않는 클래스에 데코레이터 패턴을 사용하여 해결함
fun main(args: Array<String>) {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1,1,2))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}

class DelegatingCollection<T> : Collection<T> { // Collection 같이 비교적 단순한 인터페이스를 구현하면서 아무 동작도 변경하지 않는 데코레이터를 만들 때조차도 다음과 같이 복잡한 코드를 작성해야 함
    private val innerList = arrayListOf<T>()

    override val size: Int
        get() = innerList.size

    override fun isEmpty(): Boolean = innerList.isEmpty()

    override fun iterator(): Iterator<T> = innerList.iterator()


    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)

    override fun contains(element: T): Boolean = innerList.contains(element)
}

class DelegatingCollectionBy<T>( // by를 사용하여 위의 보일러플레이트 코드들이 없어짐, 클래스를 위임하게 되고 이러한 위임을 언어가 제공하는 일급 시민 기능으로 지원함
    innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList {}

class CountingSet<T>( // CountingSet과 MutableCollection의 구현 방식에 대한 의존관계가 생기지 않음
    val innerSet: MutableCollection<T> = HashSet<T> ()
) : MutableCollection<T> by innerSet { // MutableCollection의 구현을 innerSet에게 위임함
    var objectsAdded = 0

    override fun add(element: T) : Boolean { // 위임하지 않고 새로운 구현을 제공
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>) : Boolean { // 위임하지 않고 새로운 구현을 제공
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}