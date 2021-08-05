package com.codecollapse.motionflick.models.datasource.api

import com.codecollapse.motionflick.models.datamodels.MotionFlickMovies
import com.codecollapse.motionflick.models.datamodels.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MotionFlickApi {

    @GET("trending/all/day")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): Response<MotionFlickMovies>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<MotionFlickMovies>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<MovieDetail>
}