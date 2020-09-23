package com.example.week_3_movie_task

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results") val movies : MutableList<Movie>,
    @SerializedName("page") val currentPage : Int,
    @SerializedName("total_pages") val totalPages : Int
)