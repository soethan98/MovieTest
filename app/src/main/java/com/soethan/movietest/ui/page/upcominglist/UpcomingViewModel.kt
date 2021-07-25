package com.soethan.movietest.ui.page.upcominglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soethan.movietest.models.MovieListUiState
import com.soethan.movietest.models.UpComingUiModel
import com.soethan.movietest.domain.getDataOrThrow
import com.soethan.movietest.domain.usecases.GetUpComingMovies
import com.soethan.movietest.mapper.UpComingMoviesUIMapper
import com.soethan.movietest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class UpcomingViewModel constructor(private val getUpComingMovies: GetUpComingMovies):BaseViewModel(){

    init {
        getUpComingMovies()
    }
    private val _upComingMovieState = MutableLiveData<MovieListUiState<UpComingUiModel>>()
    val upComingUiMovieListState: LiveData<MovieListUiState<UpComingUiModel>>
        get() = _upComingMovieState

    private val mapper by lazy { UpComingMoviesUIMapper() }

    override fun handleError(t: Throwable) {
       _upComingMovieState.postValue(MovieListUiState(isLoading = false, throwable = t))
    }
    fun getUpComingMovies(){
        viewModelScope.launch(exceptionHandler+ Dispatchers.IO) {
            _upComingMovieState.postValue(MovieListUiState(isLoading = true))
            getUpComingMovies.execute().catch { e -> handleError(e) }
                .collect {
                    val movieResult =it.getDataOrThrow()
                    _upComingMovieState.postValue(
                        MovieListUiState(
                            isLoading = false,
                            movies = mapper.transform(movieResult)
                        )
                    )
                }
        }
    }



}