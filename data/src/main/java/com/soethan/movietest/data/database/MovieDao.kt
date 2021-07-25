package com.soethan.movietest.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soethan.movietest.data.model.FavoriteMovieDataModel
import com.soethan.movietest.data.model.MovieDetailDataModel
import com.soethan.movietest.data.model.PopularMovieDataModel
import com.soethan.movietest.data.model.UpcomingMovieDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM upcoming")
    fun getAllUpComingMovies(): Flow<List<UpcomingMovieDataModel>>

    @Query("SELECT * FROM popular")
    fun getAllPopularMovies(): Flow<List<PopularMovieDataModel>>

    @Query("SELECT * FROM favourite")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieDataModel>>

    @Query("SELECT * FROM movie_detail WHERE id=:id")
    fun getMovieDetail(id: Int): Flow<MovieDetailDataModel>

    @Delete
    fun clearFavoriteMovie(movie: FavoriteMovieDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllUpComingMovies(movies: List<UpcomingMovieDataModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPopularMovies(movies: List<PopularMovieDataModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieDetail(movie: MovieDetailDataModel)

    @Query("SELECT COUNT(*) FROM favourite WHERE id =:id")
    fun isFavouriteMovie(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteMovie(favouriteVo: FavoriteMovieDataModel)
}