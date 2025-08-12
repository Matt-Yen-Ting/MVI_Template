package com.example.features.login.reducer

import com.example.data.commondata.Reducer
import com.example.features.login.LoginIntent
import com.example.features.login.state.LoginState
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