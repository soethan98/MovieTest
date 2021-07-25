package com.soethan.movietest.ui.page.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.soethan.movietest.domain.data
import com.soethan.movietest.domain.getDataOrThrow
import com.soethan.movietest.domain.usecases.DeleteFavoriteMovie
import com.soethan.movietest.domain.usecases.GetMovieDetail
import com.soethan.movietest.domain.usecases.IsFavoriteMovie
import com.soethan.movietest.domain.usecases.SaveFavoriteMovie
import com.soethan.movietest.mapper.FavoriteMoviesUiMapper
import com.soethan.movietest.mapper.MovieDetailUiMapper
import com.soethan.movietest.models.MovieDetailUiModel
import com.soethan.movietest.models.MovieDetailUiState
import com.soethan.movietest.models.MovieListUiState
import com.soethan.movietest.models.UpComingUiModel
import com.soethan.movietest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel constructor(
    private val getMovieDetail: GetMovieDetail,
    private val isFavoriteMovie: IsFavoriteMovie,
    private val saveFavoriteMovie: SaveFavoriteMovie,
    private val deleteFavoriteMovie: DeleteFavoriteMovie
) :
    BaseViewModel() {

    private val _movieDetailState = MutableLiveData<MovieDetailUiState>()
    val movieDetailState: LiveData<MovieDetailUiState>
        get() = _movieDetailState

    private val _isFavoriteMovie = MutableLiveData<Boolean>()
    val isFavoriteMovieLiveData: LiveData<Boolean>
        get() = _isFavoriteMovie

    private val mapper by lazy { MovieDetailUiMapper() }
    private val favoriteMapper by lazy { FavoriteMoviesUiMapper() }

    fun getDetail(id: Int) {
        viewModelScope.launch {
            _movieDetailState.postValue(MovieDetailUiState(isLoading = true))
            viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
                getMovieDetail.execute(id).catch { e -> handleError(e) }
                    .collect {
                        val movieResult = it.getDataOrThrow()
                        _movieDetailState.postValue(
                            MovieDetailUiState(
                                isLoading = false,
                                movie = mapper.mapItem(movieResult)
                            )
                        )
                    }
            }
        }
    }

    fun checkFavoriteStatus(id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _isFavoriteMovie.postValue(isFavoriteMovie.execute(id).getDataOrThrow())
        }
    }

    fun editFavoriteStatus() {
        val currentMovie = _movieDetailState.value?.movie
        val isFavorite = _isFavoriteMovie.value ?: false
        currentMovie?.let { movie ->
            val favoriteMovie = favoriteMapper.mapDomainModel(item = movie)
            viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
                if (isFavorite) {
                    deleteFavoriteMovie.execute(favoriteMovie)
                } else {
                    saveFavoriteMovie.execute(favoriteMovie)
                }
                _isFavoriteMovie.postValue(!isFavorite)
            }
        } ?: handleError(Throwable("Current Movie is empty"))

    }

    override fun handleError(t: Throwable) {
        _movieDetailState.postValue(MovieDetailUiState(isLoading = false, throwable = t))
    }
}