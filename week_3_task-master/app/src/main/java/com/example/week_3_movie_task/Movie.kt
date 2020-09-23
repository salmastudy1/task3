package com.example.week_3_movie_task

import com.google.gson.annotations.SerializedName

data class Movie(
    //https://developers.themoviedb.org/3/movies/get-popular-movies
    //id actually integer
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,

    //poster_path actually nullable string

    @SerializedName("poster_path") val posterPath: String,

    //backdrop_path actually nullable string

    @SerializedName("backdrop_path") val backdropPath: String,

    //vote_average actually number

    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String,
    //I Added  Them
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("adult") val isAdult: Boolean,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("popularity") val popularity: Float,





    )
