package com.example.mvi_architecture.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.di.SignInViewModelDelegate
import com.example.data.datastore.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
    val signInViewModelDelegate: SignInViewModelDelegate
) : ViewModel() {



    private fun sendIntent(mainIntent: MainIntent) {

    }
}