package com.example.mvi_architecture.di

import com.example.mvi_architecture.base.SignInViewModelDelegate
import com.example.mvi_architecture.base.SignInViewModelDelegateImpl
import com.example.mvi_architecture.data.datastore.PreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DelegateModule {

    @Singleton
    @Provides
    fun provideSignInViewModelDelegate(
        preferencesDataStore: PreferencesDataStore,
        @ApplicationScope applicationScope: CoroutineScope,

        ): SignInViewModelDelegate {
        return SignInViewModelDelegateImpl(preferencesDataStore, applicationScope)
    }
}