package com.soethan.movietest.ui.page.popularlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soethan.movietest.models.MovieListUiState
import com.soethan.movietest.models.PopularUiModel
import com.soethan.movietest.domain.getDataOrThrow
import com.soethan.movietest.domain.usecases.GetPopularMovies
import com.soethan.movietest.mapper.PopularMoviesUIMapper
import com.soethan.movietest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PopularViewModel constructor(private val getPopularMovies: GetPopularMovies) : BaseViewModel() {

    private val _popularMovieState = MutableLiveData<MovieListUiState<PopularUiModel>>()
    val popularUiMovieListState: LiveData<MovieListUiState<PopularUiModel>>
        get() = _popularMovieState

    init {
        getPopularMovies()
    }


    private val mapper by lazy { PopularMoviesUIMapper() }

    fun getPopularMovies() {
        _popularMovieState.postValue(MovieListUiState(isLoading = true))
        viewModelScope.launch(exceptionHandler+Dispatchers.IO) {
            getPopularMovies.execute().catch { e -> handleError(e) }
                .collect {
                    val movieResult =it.getDataOrThrow()
                    _popularMovieState.postValue(
                        MovieListUiState(
                            isLoading = false,
                            movies = mapper.transform(movieResult)
                        )
                    )
                }
        }
    }

    override fun handleError(t: Throwable) {
        _popularMovieState.postValue(MovieListUiState(isLoading = false, throwable = t))

    }
}