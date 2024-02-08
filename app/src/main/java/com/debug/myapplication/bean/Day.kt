package com.debug.myapplication.bean

import com.debug.myapplication.util.MyType
import java.time.LocalDate

class Day(val date: LocalDate) : MyType(DAY) {
    companion object {
        val GREY = 0
        val BLACK = 1
        val GREEN = 2
    }
    var flag = BLACK
    constructor(date: LocalDate, flag: Int) : this(date) {
        this.flag = flag
    }
}
