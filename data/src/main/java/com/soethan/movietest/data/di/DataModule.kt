package com.soethan.movietest.data.di

import com.soethan.movietest.data.MovieRepoImpl
import com.soethan.movietest.domain.repo.MovieRepo
import org.koin.dsl.module

val DATA_MODULE = module {
    single<MovieRepo> { MovieRepoImpl(get(), get(), get(),get(),get(),get(),get()) }
}
