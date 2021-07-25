package com.soethan.movietest.data.di

import com.soethan.movietest.data.api.MovieService
import com.soethan.movietest.data.utils.ConnectivityChecker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

val NET_MODULE = module {
    single<GsonConverterFactory> { provideGsonConverter() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get<HttpLoggingInterceptor>()) }
    single<Retrofit> {
        provideRetrofit(get(), get())
    }
    single<MovieService> { get<Retrofit>().create(MovieService::class.java) }
    single { ConnectivityChecker(get()) }
}

private fun provideGsonConverter() = GsonConverterFactory.create()
private fun provideLoggingInterceptor() =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder().addInterceptor(interceptor).build()

private fun provideRetrofit(gsonConverter: GsonConverterFactory, okHttpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverter)
        .client(okHttpClient)
        .build()