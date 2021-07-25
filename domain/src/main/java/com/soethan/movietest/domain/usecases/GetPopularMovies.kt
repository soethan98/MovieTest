package com.soethan.movietest.domain.usecases

import com.soethan.movietest.domain.repo.MovieRepo

class GetPopularMovies constructor(private val movieRepo: MovieRepo){
    suspend fun execute() = movieRepo.getPopularMovies()
}