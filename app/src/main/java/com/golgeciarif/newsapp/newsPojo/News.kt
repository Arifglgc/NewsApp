package com.golgeciarif.newsapp.newsPojo

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)