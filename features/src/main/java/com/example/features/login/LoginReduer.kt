package com.example.features.login

import com.example.data.commondata.Reducer
import javax.inject.Inject

class LoginReducer @Inject constructor(
) : Reducer<LoginState, LoginIntent> {

    override fun reduce(oldState: LoginState, intent: LoginIntent): LoginState {
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