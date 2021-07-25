package com.soethan.movietest.domain.usecases

import com.soethan.movietest.domain.models.FavoriteDomainModel
import com.soethan.movietest.domain.repo.MovieRepo

class SaveFavoriteMovie constructor(private val movieRepo: MovieRepo) {
    suspend fun execute(favoriteDomainModel: FavoriteDomainModel) = movieRepo.saveFavoriteMovie(favoriteDomainModel)
}