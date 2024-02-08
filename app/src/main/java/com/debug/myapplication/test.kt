package com.debug.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    var localDate = LocalDate.now()
    localDate = localDate.plusMonths(2)
    println(localDate.dayOfWeek)
    println(localDate.dayOfMonth)
    println(localDate.dayOfYear)
    println(localDate.month)
    println(localDate.year)
}
