package com.soethan.movietest.models

data class MovieListUiState<T>(
    val isLoading: Boolean = true,
    val movies: List<T> = emptyList(),
    val throwable: Throwable? = null
)