package com.soethan.movietest.ui.page.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.movietest.domain.getDataOrThrow
import com.soethan.movietest.domain.usecases.GetFavoriteMovies
import com.soethan.movietest.mapper.FavoriteMoviesUiMapper
import com.soethan.movietest.models.FavoriteUiModel
import com.soethan.movietest.models.MovieListUiState
import com.soethan.movietest.models.PopularUiModel
import com.soethan.movietest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel constructor(private val getFavoriteMovies: GetFavoriteMovies):BaseViewModel() {

    private val _favoriteMovieState = MutableLiveData<MovieListUiState<FavoriteUiModel>>()
    val favoriteUiMovieListState: LiveData<MovieListUiState<FavoriteUiModel>>
        get() = _favoriteMovieState

    private val mapper by lazy { FavoriteMoviesUiMapper() }

    init {
        getFavoriteMovies()
    }
    private fun getFavoriteMovies(){
        _favoriteMovieState.postValue(MovieListUiState(isLoading = true))
        viewModelScope.launch(exceptionHandler+Dispatchers.IO) {
            getFavoriteMovies.execute().catch { e -> handleError(e) }
                .collect {
                    val movieResult =it.getDataOrThrow()
                    _favoriteMovieState.postValue(
                        MovieListUiState(
                            isLoading = false,
                            movies = mapper.mapItems(movieResult)
                        )
                    )
                }
        }
    }
    override fun handleError(t: Throwable) {
        _favoriteMovieState.postValue(MovieListUiState(isLoading = false, throwable = t))
    }
}