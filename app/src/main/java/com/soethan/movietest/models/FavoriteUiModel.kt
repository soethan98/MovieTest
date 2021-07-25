package com.soethan.movietest.models

data class FavoriteUiModel(override val id:Int,
    val poster_path: String,
    val backdrop_path:String,
    val release_date: String?,
    val title: String,
    val vote_average: Double,
    val overview:String,
    val lang:String,
    val vote_count: Int):BaseUiModel