package com.soethan.movietest.domain.usecases

import com.soethan.movietest.domain.repo.MovieRepo

class IsFavoriteMovie constructor(private val movieRepo: MovieRepo) {

    suspend fun execute(id:Int) = movieRepo.checkFavoriteStatus(id)
}