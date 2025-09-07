package com.example.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.account.AccountUseCases
import com.example.features.login.reducer.LoginReducer
import com.example.features.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val loginReducer: LoginReducer
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun sendIntent(intent: LoginIntent) {
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
            _loginState.update { oldState ->
                loginReducer.reduce(
                    oldState,
                    LoginIntent.Login(
                        account,
                        showLoading = false,
                        loginSuccess = false,
                        loginFail = true
                    )
                )
            }
        }) {
            _loginState.update { oldState ->
                loginReducer.reduce(
                    oldState,
                    LoginIntent.Login(account, showLoading = true)
                )
            }
            accountUseCases.login(account)
            accountUseCases.setToken("TestLoginToken")
            _loginState.update { oldState ->
                loginReducer.reduce(
                    oldState,
                    LoginIntent.Login(
                        account,
                        showLoading = false,
                        loginSuccess = true,
                        loginFail = false
                    )
                )
            }
        }
    }

    private fun reductionUiState() {
        _loginState.update { oldState ->
            loginReducer.reduce(
                oldState,
                LoginIntent.ResetState(
                    "",
                    showLoading = false,
                    loginSuccess = false,
                    loginFail = false
                )
            )
        }
    }
}