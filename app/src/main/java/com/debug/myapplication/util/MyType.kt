package com.debug.myapplication.util

import org.jxxy.debug.corekit.recyclerview.MultipleType

abstract class MyType(val type: Int) : MultipleType {
    companion object {
        val WEEK = 1026
        val DAY = 1027
        val INFO = 1028
        val CALENDAR = 1029
    }

    override fun viewType(): Int = type
}
