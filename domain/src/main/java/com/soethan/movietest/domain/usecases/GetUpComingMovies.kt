package com.soethan.movietest.domain.usecases

import com.soethan.movietest.domain.repo.MovieRepo

class GetUpComingMovies constructor(private val movieRepo: MovieRepo){
    suspend fun execute() = movieRepo.getUpComingMovies()

}