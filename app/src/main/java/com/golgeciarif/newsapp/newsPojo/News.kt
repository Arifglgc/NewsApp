package com.golgeciarif.newsapp.newsPojo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Parcelable