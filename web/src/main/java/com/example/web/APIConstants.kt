package com.example.web

object APIConstants {

    const val serverPath = "https://api.themoviedb.org/3/"
    const val apiKey = "3fe0d67705c46ef0b5e8b8087933b8b6"
    const val prefixImage = "https://image.tmdb.org/t/p/w500/"
    const val language = "&language=es-MX"

    @JvmStatic
    var retrofitInstance: ApiService? = null


}