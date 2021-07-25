package com.soethan.movietest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soethan.movietest.data.model.FavoriteMovieDataModel
import com.soethan.movietest.data.model.MovieDetailDataModel
import com.soethan.movietest.data.model.PopularMovieDataModel
import com.soethan.movietest.data.model.UpcomingMovieDataModel

@Database(
    entities = [UpcomingMovieDataModel::class, PopularMovieDataModel::class, FavoriteMovieDataModel::class,MovieDetailDataModel::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}