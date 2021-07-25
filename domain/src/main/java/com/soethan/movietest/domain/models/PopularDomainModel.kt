package com.soethan.movietest.domain.models

data class PopularDomainModel(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)