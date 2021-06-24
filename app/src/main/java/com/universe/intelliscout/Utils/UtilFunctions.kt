package com.universe.intelliscout.Utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class UtilFunctions {

    fun receiveBirthFromDatabaseToCalendar(date: String): Calendar {

        val replaceDate = date.replace("T00:00:00.000Z", "")

        val calendar = Calendar.getInstance()

        calendar.time = SimpleDateFormat("yyyy-MM-dd").parse(replaceDate)

        return calendar

    }

    fun receiveBirthFromDatabaseToString(date: String): String {

        return date.replace("T00:00:00.000Z", "")

    }

    fun datePickertoDateFormat(day: Int, month: Int, year: Int): Calendar {

        val finalDate = "${year}-${month}-${day}"
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"))

        calendar.time = SimpleDateFormat("yyyy-MM-dd").parse(finalDate)

        return calendar

    }

    fun dateToString(day: Int, month: Int, year: Int): String {

        val f: NumberFormat = DecimalFormat("00")

        return "$year-${f.format(month)}-${f.format(day)}"

    }


}