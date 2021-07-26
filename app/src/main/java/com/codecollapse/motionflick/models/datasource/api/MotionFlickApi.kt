package com.codecollapse.motionflick.models.datasource.api

import com.codecollapse.motionflick.models.datamodels.MotionFlickMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MotionFlickApi {

    @GET("trending/all/week")
    suspend fun getTrendingMovies(@Query("api_key") apiKey : String) : Response<MotionFlickMovies>
}