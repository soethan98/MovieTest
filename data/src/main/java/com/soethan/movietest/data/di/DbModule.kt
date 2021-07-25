package com.soethan.movietest.data.di

import androidx.room.Room
import com.soethan.movietest.data.database.MovieDatabase
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

private const val DATABASE_NAME = "MovieDatabase"

val DB_MODULE = module {
    single {
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}