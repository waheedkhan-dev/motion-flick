package com.codecollapse.motionflick.models.repositories

import android.util.Log
import com.codecollapse.motionflick.commons.AppConstants
import com.codecollapse.motionflick.models.datamodels.MotionFlickMovies
import com.codecollapse.motionflick.models.datamodels.MovieCredits
import com.codecollapse.motionflick.models.datamodels.MovieDetail
import com.codecollapse.motionflick.models.datasource.api.MotionFlickApi
import com.codecollapse.motionflick.models.datasource.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StartUpRepository @Inject constructor(private var motionFlickApi: MotionFlickApi) {

    companion object {
        private const val TAG = "StartUpRepository"
    }

    fun getTrendingMoviesList(): Flow<Resource<MotionFlickMovies>> {
        return flow {

            motionFlickApi.getTrendingMovies(AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    Log.d(TAG, "getTrendingMoviesList: ${it.body()!!.results}")
                    emit(Resource.success(it.body()!!))
                } else {
                    emit(Resource.error(it.body()!!.status_message!!, data = null))
                }
            }
        }.flowOn(IO)
    }

    fun getTopRatedMovies(): Flow<Resource<MotionFlickMovies>> {
        return flow {

            motionFlickApi.getTopRatedMovies(AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    Log.d(TAG, "getTrendingMoviesList: ${it.body()!!.results}")
                    emit(Resource.success(it.body()!!))
                } else {
                    emit(Resource.error(it.body()!!.status_message!!, data = null))
                }
            }
        }.flowOn(IO)
    }

    fun getUpComingMovies(): Flow<Resource<MotionFlickMovies>> {
        return flow {

            motionFlickApi.getUpComingMovies(AppConstants.API_KEY).let {
                if (it.isSuccessful) {
                    Log.d(TAG, "getUpComingMovies: ${it.body()!!.results}")
                    emit(Resource.success(it.body()!!))
                } else {
                    emit(Resource.error(it.body()!!.status_message!!, data = null))
                }
            }
        }.flowOn(IO)
    }

    fun getMovieDetails(movieId: Int, movieLanguage: String): Flow<Resource<MovieDetail>> {
        return flow {
            try {
                motionFlickApi.getMovieDetails(movieId,AppConstants.API_KEY, movieLanguage).let {
                    if (it.isSuccessful) {
                        Log.d(TAG, "getMovieDetails: ${it.body()!!}")
                        emit(Resource.success(it.body()!!))
                    } else {
                        emit(Resource.error("something went wrong", data = null))
                    }
                } 
            }catch (ex : Exception){
                Log.d(TAG, "getMovieDetails: ${ex.message}")
                emit(Resource.error("something went wrong", data = null))
            }
         
        }.flowOn(IO)
    }

    fun getMovieCredits(movieId: Int, movieLanguage: String): Flow<Resource<MovieCredits>> {
        return flow {
            try {
                motionFlickApi.getMovieCredits(movieId,AppConstants.API_KEY, movieLanguage).let {
                    if (it.isSuccessful) {
                        Log.d(TAG, "MovieCredits: ${it.body()!!}")
                        emit(Resource.success(it.body()!!))
                    } else {
                        emit(Resource.error("something went wrong", data = null))
                    }
                }
            }catch (ex : Exception){
                Log.d(TAG, "getMovieDetails: ${ex.message}")
                emit(Resource.error("something went wrong", data = null))
            }

        }.flowOn(IO)
    }
}