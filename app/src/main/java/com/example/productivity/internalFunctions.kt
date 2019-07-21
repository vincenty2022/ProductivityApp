package com.example.productivity

fun dateFormatLetters(form: Int, year: Int, month: Int, day: Int): String {
    val monthArr = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    when (form) {
        0 -> return ("$day ${monthArr[month]} $year")       // day, month, year
        1 -> return ("${monthArr[month]} $day $year")       // month, year, day
        2 -> return ("$year ${monthArr[month]} $day")       // year, month, day
        else -> throw IllegalArgumentException ("form value is out of setting bounds")
    }
}

fun dateFormatNumbers(form: Int, year: Int, month: Int, day: Int): String {
    when (form) {
        0 -> return ("$day/${month+1}/$year")       // day, month, year
        1 -> return ("${month+1}/$day/$year")       // month, year, day
        2 -> return ("$year/${month+1}/$day")       // year, month, day
        else -> throw IllegalArgumentException ("form value is out of setting bounds")
    }
}

