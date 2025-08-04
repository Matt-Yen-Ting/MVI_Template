package com.example.domain.di

import com.example.domain.repository.account.AccountRepository
import com.example.domain.repository.account.AccountRepositoryImpl
import com.example.domain.repository.announcement.AnnouncementRepository
import com.example.domain.repository.announcement.AnnouncementRepositoryImpl
import com.example.domain.repository.api.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAccountRepository(apiRepository: ApiRepository) : AccountRepository  {
        return AccountRepositoryImpl(apiRepository)
    }

    @Singleton
    @Provides
    fun provideAnnouncementRepository(apiRepository: ApiRepository): AnnouncementRepository {
        return AnnouncementRepositoryImpl(apiRepository)
    }
}