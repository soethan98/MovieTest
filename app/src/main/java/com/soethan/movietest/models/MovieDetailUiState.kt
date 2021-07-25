package com.soethan.movietest.models

data class MovieDetailUiState(
    val isLoading: Boolean = true,
    val throwable: Throwable? = null,
    val movie: MovieDetailUiModel? = null
)