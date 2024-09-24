package com.yohwan.study.kotlininaction.chapter11

import kotlinx.html.stream.createHTML
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.tr

fun main(args: Array<String>) {

}

fun createSimpleTable() = createHTML().
    table {
        tr {
            td {
                + "cell"
            }
        }
    }

fun createSimpleTable2() = createHTML().
    table {
        (this@table).tr {
            (this@tr).td {
                + "cell"
            }
        }
    }

/*
* HTML 빌더: HTML을 만들기 위한 코틀린 DSL, 타입 안전한 빌더
*
* */