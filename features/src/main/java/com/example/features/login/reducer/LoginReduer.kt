package com.example.features.login.reducer

import com.example.data.commondata.Reducer
import com.example.features.login.LoginIntent
import com.example.features.login.state.LoginUiState
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