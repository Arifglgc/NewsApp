package com.golgeciarif.newsapp.model

//Daha soonra parcelize işlemi yapılacak....
data class User(
    var firstName:String,
    var email:String,
) {

    constructor() : this("","")
}

