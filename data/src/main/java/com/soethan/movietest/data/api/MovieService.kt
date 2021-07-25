package com.soethan.movietest.data.api

import com.soethan.movietest.data.model.MovieDetailDataModel
import com.soethan.movietest.data.model.MovieResponseModel
import com.soethan.movietest.data.model.PopularMovieDataModel
import com.soethan.movietest.data.model.UpcomingMovieDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/upcoming")
    suspend fun loadUpComingMovies(
        @Query("api_key") apiKey: String
    ): MovieResponseModel<UpcomingMovieDataModel>

    @GET("movie/popular")
    suspend fun loadPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponseModel<PopularMovieDataModel>

    @GET("movie/{movie_id}")
    suspend fun loadMovieDetails(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String
    ): MovieDetailDataModel?

}