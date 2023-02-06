package com.my.sapiaassignment.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtility {
    private val formatter = SimpleDateFormat("kk:mm:ss", Locale.ENGLISH)
    const val startTime: Long = 12600000/*"09:00:00"*/
    const val endTime: Long = 45000000/*"18:00:00"*/
    const val launchTime=2000L
    val workingDays = listOf(2, 3, 4, 5, 6)

    fun dayOfMonth(): Int {
        val cal = Calendar.getInstance()
        return cal[Calendar.DAY_OF_WEEK]
    }

    fun getCurrentTime(): Long {
        val currentDate = Date()
        return convertTime(formatter.format(currentDate))
    }


    private fun convertTime(dayTime: String): Long {
        try {
            val d = formatter.parse(dayTime)
            return d?.time ?: 0
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }
}