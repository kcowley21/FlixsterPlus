package com.example.flixsterplus

annotation class SerializedName(val value: String)

class FlixMovies {

    val movie_title: Any? = null

            @SerializedName("title")
            var titleMovies: String? = null


            @SerializedName("")
            var descriptionMovies: String? = null


            @SerializedName("movie_image")
            var imageMovies: String? = null



        }


