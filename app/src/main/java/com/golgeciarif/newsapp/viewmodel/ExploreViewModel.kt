package com.golgeciarif.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.golgeciarif.newsapp.newsPojo.Article
import com.golgeciarif.newsapp.retrofit.NewsAPIService
import com.golgeciarif.newsapp.util.Filters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel@Inject constructor(
    private val newsAPIService: NewsAPIService
): ViewModel() {
    private var _news = MutableStateFlow<List<Article>>(emptyList())
    var news = _news.asStateFlow()

    private var _filterState = MutableStateFlow(Filters())
    var filterState= _filterState.asStateFlow()


    fun updateFilters(updatedFilters: Filters) {
        _filterState.value = updatedFilters
    }


}