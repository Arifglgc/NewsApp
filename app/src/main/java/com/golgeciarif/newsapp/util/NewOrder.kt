package com.golgeciarif.newsapp.util

sealed class NewOrder {
    data object Relevance : NewOrder()
    data object Date : NewOrder()
    data object Popularity: NewOrder()

    fun NewOrder.convertOrderToString(): String {
        return when (this) {
            NewOrder.Relevance -> "Relevance"
            NewOrder.Date -> "Upload Date"
            NewOrder.Popularity -> "Popularity"
        }
    }

    companion object {
        fun convertStringToNewOrder(text: String): NewOrder {
            return when (text) {
                "Relevance" -> NewOrder.Relevance
                "Upload Date" -> NewOrder.Date
                "Popularity" -> NewOrder.Popularity
                else -> NewOrder.Date // Dönüştürülemezse varsayılan olarak Date döndür
            }
        }
    }

}