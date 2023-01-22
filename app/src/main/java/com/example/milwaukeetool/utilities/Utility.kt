package com.example.milwaukeetool.utilities

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Utility {
    companion object {
        fun getStartAndEndDates(): ArrayList<String> {
            val calendar = Calendar.getInstance()
            val today = calendar.time.getFormattedString()
            calendar.add(Calendar.DATE, 4)
            val fifthDay = calendar.time.getFormattedString()
            return arrayListOf(today, fifthDay)
        }
    }
}

fun Date.getFormattedString(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return formatter.format(this)
}