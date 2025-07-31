package com.example.mvi_architecture.main.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_architecture.base.SignInViewModelDelegate
import com.example.mvi_architecture.data.datastore.PreferencesDataStore
import com.example.mvi_architecture.main.SplashIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
    val signInViewModelDelegate: SignInViewModelDelegate
): ViewModel() {

    private val _uiIntentChannel = Channel<SplashIntent>()
    private val uiIntentChannel = _uiIntentChannel.receiveAsFlow()

    private val _mainPageUiState = Channel<MainPageState>()
    val mainPageUiState = _mainPageUiState.receiveAsFlow()

    init {
        handleIntent()
    }

    fun sendIntent(intent: SplashIntent) {
        viewModelScope.launch {
            _uiIntentChannel.send(intent)
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            uiIntentChannel.collectLatest { intent ->
                when (intent) {
                    is SplashIntent.GetToken -> {
                        getLoginToken()
                    }
                }
            }
        }
    }

    private fun getLoginToken() {
        viewModelScope.launch {
            preferencesDataStore.getToken().collectLatest { token ->
                Log.d("Token", "getLoginToken:$token ")
                if (token.isEmpty()) {
                    _mainPageUiState.send(MainPageState.LoginPage)
                } else {
                    _mainPageUiState.send( MainPageState.HomePage)
                }
            }
        }
    }
}