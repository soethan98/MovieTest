package com.soethan.movietest.domain.models

data class FavoriteDomainModel(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String?,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)