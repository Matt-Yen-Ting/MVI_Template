package com.example.mvi_architecture.di

import com.example.mvi_architecture.api.TestApi
import com.example.mvi_architecture.repositories.ApiRepository
import com.example.mvi_architecture.repositories.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideApiRepository(
        testApi: TestApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ApiRepository = ApiRepositoryImpl(testApi, ioDispatcher)
}