package com.example.features.ui_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.datastore.PreferencesDataStore
import com.example.domain.usecase.account.AccountUseCases
import com.example.features.ui_home.reducer.LogoutReducer
import com.example.features.ui_home.state.LogoutUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
    private val accountUseCases: AccountUseCases,
    private val logoutReducer: LogoutReducer
) : ViewModel() {

    private val _logoutUiState = MutableStateFlow(LogoutUiState())
    val logoutUiState: StateFlow<LogoutUiState> = _logoutUiState

    fun sendIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Logout -> {
                logout()
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(
            CoroutineExceptionHandler { context, throwable ->
                _logoutUiState.update {
                    logoutReducer.reduce(
                        _logoutUiState.value,
                        HomeIntent.Logout(false, logoutSuccess = false, logoutFail = true)
                    )
                }
            }
        ) {
            _logoutUiState.update {
                logoutReducer.reduce(
                    _logoutUiState.value,
                    HomeIntent.Logout(true)
                )
            }
            accountUseCases.logout()
            preferencesDataStore.setToken("")
            _logoutUiState.update {
                logoutReducer.reduce(
                    _logoutUiState.value,
                    HomeIntent.Logout(false, logoutSuccess = true, logoutFail = false)
                )
            }
        }
    }


}