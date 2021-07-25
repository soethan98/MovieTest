package com.soethan.movietest.models

data class UpComingUiModel(
    override val id: Int,
    val poster_path: String,
    val release_date: String?,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
) : BaseUiModel