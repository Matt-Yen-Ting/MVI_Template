package com.example.features.ui_login.reducer

import com.example.core.common.Reducer
import com.example.features.ui_login.LoginIntent
import com.example.features.ui_login.state.LoginUiState
import javax.inject.Inject

class LoginReducer @Inject constructor(
) : Reducer<LoginUiState, LoginIntent> {

    override fun reduce(oldState: LoginUiState, intent: LoginIntent): LoginUiState {
        return when (intent) {
            is LoginIntent.Login -> {
                oldState.copy(
                    showLoading = intent.showLoading,
                    loginSuccess = intent.loginSuccess,
                    loginFail = intent.loginFail
                )
            }

            is LoginIntent.ResetState -> {
                oldState.copy(
                    showLoading = intent.showLoading,
                    loginSuccess = intent.loginSuccess,
                    loginFail = intent.loginFail
                )
            }
        }
    }


}