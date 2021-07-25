package com.soethan.movietest.domain.repo

import com.soethan.movietest.domain.Result
import com.soethan.movietest.domain.models.FavoriteDomainModel
import com.soethan.movietest.domain.models.MovieDetailDomainModel
import com.soethan.movietest.domain.models.PopularDomainModel
import com.soethan.movietest.domain.models.UpcomingDomainModel
import kotlinx.coroutines.flow.Flow

interface MovieRepo {

    suspend fun getPopularMovies(): Flow<Result<List<PopularDomainModel>>>
    suspend fun getUpComingMovies(): Flow<Result<List<UpcomingDomainModel>>>
    suspend fun getMovieDetail(id:Int):Flow<Result<MovieDetailDomainModel>>
    suspend fun getFavoriteMovies():Flow<Result<List<FavoriteDomainModel>>>

    suspend fun saveFavoriteMovie(favDomainModel: FavoriteDomainModel):Result<Unit>
    suspend fun clearFavoriteMovie(favDomainModel: FavoriteDomainModel):Result<Unit>
    suspend fun checkFavoriteStatus(id:Int):Result<Boolean>

}