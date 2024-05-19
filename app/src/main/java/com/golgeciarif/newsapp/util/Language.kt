package com.golgeciarif.newsapp.util

sealed class Language {
    data object Default : Language()
    data class Choosen(val code: String) : Language()
}