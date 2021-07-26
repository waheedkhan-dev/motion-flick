package com.codecollapse.motionflick.di.modules

import com.codecollapse.motionflick.models.datasource.api.MotionFlickApi
import com.codecollapse.motionflick.models.repositories.StartUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStartUpRepository(motionFlickApi: MotionFlickApi) : StartUpRepository{
        return StartUpRepository(motionFlickApi = motionFlickApi)
    }
}