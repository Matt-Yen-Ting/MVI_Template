package com.example.mvi_architecture.ui_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.data.datastore.PreferencesDataStore
import com.example.mvi_architecture.repositories.ApiRepository
import com.example.mvi_architecture.ui_home.reducer.LogoutReducer
import com.example.mvi_architecture.ui_home.state.LogoutUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
    private val apiRepository: ApiRepository,
    private val logoutReducer: LogoutReducer
) : ViewModel() {

    private val _logoutUiState = MutableStateFlow(LogoutUiState())
    val logoutUiState: StateFlow<LogoutUiState> = _logoutUiState

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Logout -> {
                logout()
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(
            CoroutineExceptionHandler { context, throwable ->
                _logoutUiState.value =
                    logoutReducer.reduce(
                        _logoutUiState.value,
                        HomeIntent.Logout(false, ResponseStatus.FAIL)
                    )
            }
        ) {
            _logoutUiState.value =
                logoutReducer.reduce(_logoutUiState.value, HomeIntent.Logout(true))
            apiRepository.logout()
            preferencesDataStore.setToken("")
            _logoutUiState.value =
                logoutReducer.reduce(
                    _logoutUiState.value,
                    HomeIntent.Logout(false, ResponseStatus.SUCCESS)
                )


        }

    }


}