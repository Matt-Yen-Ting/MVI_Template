package com.example.mvi_architecture.ui_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.data.datastore.PreferencesDataStore
import com.example.mvi_architecture.repositories.ApiRepository
import com.example.mvi_architecture.ui_login.reducer.LoginReducer
import com.example.mvi_architecture.ui_login.state.LoginUiState
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
class LoginViewModel @Inject constructor(
    private val testApiRepository: ApiRepository,
    private val preferencesDataStore: PreferencesDataStore,
    private val loginReducer: LoginReducer
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> {
                login(intent.account)
            }

            is LoginIntent.ResetState -> {
                reductionUiState()
            }
        }
    }


    private fun login(account: String) {
        viewModelScope.launch(CoroutineExceptionHandler { context, throwable ->
            _loginUiState.value = loginReducer.reduce(
                _loginUiState.value,
                LoginIntent.Login(account, showLoading = false, ResponseStatus.FAIL)
            )
        }) {
            _loginUiState.value = loginReducer.reduce(
                _loginUiState.value,
                LoginIntent.Login(account, showLoading = true)
            )
            testApiRepository.login(account)
            preferencesDataStore.setToken("TestLoginToken")
            _loginUiState.value = loginReducer.reduce(
                _loginUiState.value,
                LoginIntent.Login(account, showLoading = false, ResponseStatus.SUCCESS)
            )

        }
    }

    private fun reductionUiState() {
        _loginUiState.value = loginReducer.reduce(
            _loginUiState.value,
            LoginIntent.ResetState("", false, ResponseStatus.INIT)
        )
    }
}