package com.example.mvi_architecture.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_architecture.base.SignInViewModelDelegate
import com.example.mvi_architecture.data.datastore.PreferencesDataStore
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

    private val _uiIntentChannel = Channel<MainIntent>()
    private val uiIntentChannel = _uiIntentChannel.receiveAsFlow()

    private val _token = Channel<String>()
    val token = _token.receiveAsFlow()

    init {
        handleIntent()
    }

    fun sendIntent(intent: MainIntent) {
        viewModelScope.launch {
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            uiIntentChannel.collectLatest { intent ->
                getLoginToken()
            }
        }
    }

    private fun getLoginToken() {
        viewModelScope.launch {
            preferencesDataStore.getToken().collectLatest { token ->
                _token.send(token)
            }
        }
    }
}