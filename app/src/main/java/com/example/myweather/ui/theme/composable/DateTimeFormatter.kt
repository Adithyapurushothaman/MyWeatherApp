package com.example.myweather.ui.theme.composable

import java.text.SimpleDateFormat
import java.util.*

fun convertStringToDate(dateTimeString: String): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return format.parse(dateTimeString)
}
fun getTimeFromDate(date: Date): String {
    val format = SimpleDateFormat("HH:mm a", Locale.getDefault())
    return format.format(date)
}
fun getDayFromDate(date: Date): String {
    val today = Date()
    val format = SimpleDateFormat("EEE", Locale.getDefault())
    return if (date.equals(today)) {
        "Today"
    } else {
        format.format(date)
    }
}
//fun getDayFromDate(date: Date): String {
//    val currentDate = Date()
//    if (date == currentDate){
//        return "Today"
//    }
//    else{
//        val format = SimpleDateFormat("EEE", Locale.getDefault())
//        return format.format(date)
//    }
//
//}