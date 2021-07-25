package com.soethan.movietest.domain.usecases

import com.soethan.movietest.domain.models.FavoriteDomainModel
import com.soethan.movietest.domain.repo.MovieRepo

class DeleteFavoriteMovie constructor(private val movieRepo: MovieRepo) {
    suspend fun execute(favoriteDomainModel: FavoriteDomainModel) = movieRepo.clearFavoriteMovie(favoriteDomainModel)
}