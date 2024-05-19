package com.golgeciarif.newsapp.util

sealed class DateRange {
    data object Anytime: DateRange()
    data object LastOneDay : DateRange()
    data object LastOneWeek : DateRange()
    data object LastOneMonth : DateRange()

    fun DateRange.convertDateToString(): String {
        return when (this) {
            DateRange.Anytime -> "Anytime"
            DateRange.LastOneWeek -> "Last One Week"
            DateRange.LastOneMonth -> "Last One Month"
            DateRange.LastOneDay -> "Today"
            else -> {"Anytime"}
        }
    }

    companion object{
        fun convertStringToDate(text: String): DateRange {
            return when (text) {
                "Anytime" -> DateRange.Anytime
                "Last One Week" -> DateRange.LastOneWeek
                "Last One Month" -> DateRange.LastOneMonth
                "Today" -> DateRange.LastOneDay
                else -> DateRange.Anytime // Dönüştürülemezse varsayılan olarak Anytime döndür
            }
        }
    }


}