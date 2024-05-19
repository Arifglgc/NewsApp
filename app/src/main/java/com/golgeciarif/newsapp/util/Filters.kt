package com.golgeciarif.newsapp.util

class Filters(
    var orderType: NewOrder = NewOrder.Date,
    var dateRange: DateRange = DateRange.Anytime,
    var language: Language = Language.Default
)