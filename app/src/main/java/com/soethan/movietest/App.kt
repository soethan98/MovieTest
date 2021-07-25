package com.soethan.movietest

import APP_DI_MODULES
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(APP_DI_MODULES)
        }
    }
}