package com.golgeciarif.newsapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.golgeciarif.newsapp.model.DateRanges
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object NewsDates {
    @RequiresApi(Build.VERSION_CODES.O)
    val dateRange = arrayListOf(
        DateRanges("Anytime",null,null),
        DateRanges("Today",LocalDate.now().minusDays(1).format((DateTimeFormatter.ISO_DATE)),LocalDate.now().format((DateTimeFormatter.ISO_DATE))),
        DateRanges("Last One Week",LocalDate.now().minusDays(7).format((DateTimeFormatter.ISO_DATE)),LocalDate.now().format((DateTimeFormatter.ISO_DATE))),
        DateRanges("Last One Month",LocalDate.now().minusDays(30).format((DateTimeFormatter.ISO_DATE)),LocalDate.now().format((DateTimeFormatter.ISO_DATE)))
    )
}