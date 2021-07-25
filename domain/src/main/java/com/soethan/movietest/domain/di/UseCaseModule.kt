package com.soethan.movietest.domain.di

import com.soethan.movietest.domain.usecases.DeleteFavoriteMovie
import com.soethan.movietest.domain.usecases.GetFavoriteMovies
import com.soethan.movietest.domain.usecases.GetMovieDetail
import com.soethan.movietest.domain.usecases.GetPopularMovies
import com.soethan.movietest.domain.usecases.GetUpComingMovies
import com.soethan.movietest.domain.usecases.IsFavoriteMovie
import com.soethan.movietest.domain.usecases.SaveFavoriteMovie
import org.koin.dsl.module

val USECASE_MODULE = module {
    single { GetPopularMovies(get()) }
    single { GetUpComingMovies(get()) }
    single { GetMovieDetail(get()) }
    single { GetFavoriteMovies(get()) }
    single { SaveFavoriteMovie(get()) }
    single { IsFavoriteMovie(get()) }
    single { DeleteFavoriteMovie(get()) }
}