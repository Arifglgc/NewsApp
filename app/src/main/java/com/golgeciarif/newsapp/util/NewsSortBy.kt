package com.golgeciarif.newsapp.util

import com.golgeciarif.newsapp.model.SortByCriteria

object NewsSortBy {
    val sortByCriterias = arrayListOf(
        SortByCriteria("Upload Date","publishedAt"),
        SortByCriteria("Relevance","relevancy"),
        SortByCriteria("Popularity","popularity"),
        )
}