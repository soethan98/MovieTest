package com.soethan.movietest.data

import com.soethan.movietest.data.api.MovieService
import com.soethan.movietest.data.database.MovieDatabase
import com.soethan.movietest.data.mapper.DetailMapper
import com.soethan.movietest.data.mapper.FavoriteDomainMapper
import com.soethan.movietest.data.mapper.PopularDomainMapper
import com.soethan.movietest.data.mapper.UpComingDomainMapper
import com.soethan.movietest.data.utils.ConnectivityChecker
import com.soethan.movietest.data.utils.Constants
import com.soethan.movietest.data.utils.ext.result
import com.soethan.movietest.data.utils.networkBoundResource
import com.soethan.movietest.domain.Result
import com.soethan.movietest.domain.getDataOrThrow
import com.soethan.movietest.domain.models.FavoriteDomainModel
import com.soethan.movietest.domain.models.MovieDetailDomainModel
import com.soethan.movietest.domain.models.PopularDomainModel
import com.soethan.movietest.domain.models.UpcomingDomainModel
import com.soethan.movietest.domain.repo.MovieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieRepoImpl constructor(
    private val movieService: MovieService,
    private val movieDb: MovieDatabase,
    private val popularDomainMapper: PopularDomainMapper,
    private val upComingDomainMapper: UpComingDomainMapper,
    private val favoriteDomainMapper: FavoriteDomainMapper,
    private val movieDetailMapper: DetailMapper,
    private val connectivityChecker: ConnectivityChecker
) : MovieRepo {

    override suspend fun getPopularMovies(): Flow<Result<List<PopularDomainModel>>> {
        return networkBoundResource(query = {
            movieDb.movieDao().getAllPopularMovies()
        }, fetch = {
            withContext(Dispatchers.IO) {
                movieService.loadPopularMovies(Constants.API_KEY)
            }
        }, saveFetchResult = { items ->
            withContext(Dispatchers.IO) {
                movieDb.movieDao().saveAllPopularMovies(items.results)
            }
        }) {
            if ((!connectivityChecker.isNetworkAvailable() && it.isNullOrEmpty())) {
                true
            } else connectivityChecker.isNetworkAvailable()
        }.map {
            result { popularDomainMapper.mapItems(it.getDataOrThrow()) }
        }

    }

    override suspend fun getUpComingMovies(): Flow<Result<List<UpcomingDomainModel>>> {
        return networkBoundResource(query = {
            movieDb.movieDao().getAllUpComingMovies()
        }, fetch = {
            withContext(Dispatchers.IO) {
                movieService.loadUpComingMovies(Constants.API_KEY)
            }
        }, saveFetchResult = { items ->
            withContext(Dispatchers.IO) {
                movieDb.movieDao().saveAllUpComingMovies(items.results)
            }
        }, shouldFetch = {
            if ((!connectivityChecker.isNetworkAvailable() && it.isNullOrEmpty())) {
                true
            } else connectivityChecker.isNetworkAvailable()
        }).map {
            result { upComingDomainMapper.mapItems(it.getDataOrThrow()) }
        }

    }

    override suspend fun getMovieDetail(id: Int): Flow<Result<MovieDetailDomainModel>> {
        return networkBoundResource(query = {
            movieDb.movieDao().getMovieDetail(id)
        }, fetch = {
            withContext(Dispatchers.IO) {
                movieService.loadMovieDetails(id, Constants.API_KEY)
            }
        }, saveFetchResult = { item ->
            withContext(Dispatchers.IO) {
                item?.let {
                    movieDb.movieDao().saveMovieDetail(it)
                }
            }
        }, shouldFetch = {
            connectivityChecker.isNetworkAvailable()
        }).map {
            result { movieDetailMapper.mapItem(it.getDataOrThrow()) }
        }
    }

    override suspend fun getFavoriteMovies(): Flow<Result<List<FavoriteDomainModel>>> {
        return movieDb.movieDao().getAllFavoriteMovies().map {
            result { favoriteDomainMapper.mapItems(it) }
        }
    }

    override suspend fun saveFavoriteMovie(favDomainModel: FavoriteDomainModel): Result<Unit> {
        return result {
            movieDb.movieDao()
                .saveFavouriteMovie(favoriteDomainMapper.mapToDataItem(favDomainModel))
        }
    }

    override suspend fun clearFavoriteMovie(favDomainModel: FavoriteDomainModel): Result<Unit> {
        return result {
            movieDb.movieDao()
                .clearFavoriteMovie(favoriteDomainMapper.mapToDataItem(favDomainModel))
        }
    }

    override suspend fun checkFavoriteStatus(id: Int): Result<Boolean> {
        return result { movieDb.movieDao().isFavouriteMovie(id) > 0 }
    }

}
