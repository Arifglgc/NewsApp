package com.golgeciarif.newsapp.util

import com.golgeciarif.newsapp.model.Languages

object NewsLanguages {

    val languages = arrayListOf(
        Languages("Arabic","ar"),
        Languages("English","en"),
        Languages("German","de"),
        Languages("Spanish","es"),
        Languages("French","fr"),
        Languages("Hebrew","he"),
        Languages("Italian","it"),
        Languages("Dutch","nl"),
        Languages("Norwegian","no"),
        Languages("Portuguese","pt"),
        Languages("Russian","ru"),
        Languages("Swedish","sv"),
        Languages("Chinese","zh"),
    )

    fun getLanguageByName(name: String): String {
        val language = languages.find { it.name.equals(name, ignoreCase = true) }
        return language!!.code
    }
}