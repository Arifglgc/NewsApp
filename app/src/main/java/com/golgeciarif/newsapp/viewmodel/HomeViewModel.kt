package com.golgeciarif.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.golgeciarif.newsapp.newsPojo.Article
import com.golgeciarif.newsapp.retrofit.NewsAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
private const val TAG ="HomeViewModel"
@HiltViewModel
class HomeViewModel@Inject constructor(
private val newsAPIService: NewsAPIService
): ViewModel() {
   private var _latestNews = MutableStateFlow<List<Article>>(emptyList())
    var latestNews = _latestNews.asStateFlow()

   private var _newsByCategory = MutableStateFlow<List<Article>>(emptyList())
    var newsByCategory = _newsByCategory.asStateFlow()

    private var _selectedPosition = MutableStateFlow<Int>(0)
    var selectedPosition = _selectedPosition.asStateFlow()



    suspend fun setSelectedPosition(newPosition:Int){
        _selectedPosition.emit(newPosition)
    }

    fun fetchLatestNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsAPIService.getLatestNews()
                Log.d(TAG, "TEST")

                if (response.isSuccessful) {
                    val newsList = response.body()?.articles
                    if (newsList != null) {
                        Log.d(TAG, newsList.size.toString())
                        Log.d(TAG, "test")

                    }
                    _latestNews.emit(newsList ?: emptyList())
                } else {
                    Log.d("nullll", "null")

                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())

            }
        }
}

    fun fetchNewsByCategory(category: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsAPIService.getLatestNewsByCategory(category)
                Log.d(TAG, "test")

                if (response.isSuccessful) {
                    _newsByCategory.emit(emptyList())
                    val newsList = response.body()?.articles
                    if (newsList != null) {
                        Log.d(TAG, newsList.size.toString()+newsList[5].title)
                        Log.d(TAG, "test")
                    }
                    _newsByCategory.emit(newsList ?: emptyList())

                } else {
                    Log.d(TAG, " is null")

                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())

            }
        }
    }

    }



