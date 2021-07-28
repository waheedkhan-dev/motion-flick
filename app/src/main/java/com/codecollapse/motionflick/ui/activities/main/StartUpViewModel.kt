package com.codecollapse.motionflick.ui.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codecollapse.motionflick.models.datamodels.MotionFlickMovies
import com.codecollapse.motionflick.models.datasource.utils.Resource
import com.codecollapse.motionflick.models.repositories.StartUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartUpViewModel @Inject constructor(private var startUpRepository: StartUpRepository) : ViewModel() {

    fun getTrendingMoviesList() : LiveData<Resource<MotionFlickMovies>> = startUpRepository.getTrendingMoviesList().asLiveData()

    fun getTopRatedMovies() : LiveData<Resource<MotionFlickMovies>> = startUpRepository.getTopRatedMovies().asLiveData()
}