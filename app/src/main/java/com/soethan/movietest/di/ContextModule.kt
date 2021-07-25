package com.soethan.movietest.di

import com.soethan.movietest.App
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val CONTEXT_MODULE = module {
    single { androidApplication() as App }

}