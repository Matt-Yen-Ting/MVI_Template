package com.example.mvi_architecture.di

import com.example.mvi_architecture.api.TestApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Reusable
    @Provides
    fun provideTestApi(retrofit: Retrofit) : TestApi = retrofit.create(TestApi::class.java)


}