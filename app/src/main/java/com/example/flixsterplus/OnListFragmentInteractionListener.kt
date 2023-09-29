package com.example.flixsterplus

interface OnListFragmentInteractionListener {
    abstract var movie_title: Any?

    fun onItemClick(item: FlixMovies)
}