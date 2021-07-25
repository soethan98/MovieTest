package com.soethan.movietest.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponseModel<T>(
    @SerializedName("results")
    val results: List<T>
)