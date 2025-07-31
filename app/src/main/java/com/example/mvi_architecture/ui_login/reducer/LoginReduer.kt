package com.example.mvi_architecture.ui_login.reducer

import com.example.mvi_architecture.base.Reducer
import com.example.mvi_architecture.ui_login.LoginIntent
import com.example.mvi_architecture.ui_login.state.LoginUiState
import javax.inject.Inject
import kotlin.math.acos

class LoginReducer @Inject constructor(
) : Reducer<LoginUiState, LoginIntent> {

    override fun reduce(oldState: LoginUiState, intent: LoginIntent): LoginUiState {
        return when (intent) {
            is LoginIntent.Login -> {
                oldState.copy(
                    showLoading = intent.showLoading,
                    loginStatus = intent.loginState
                )
            }

            is LoginIntent.ResetState -> {
                oldState.copy(
                    showLoading = intent.showLoading,
                    loginStatus = intent.loginState,
                )
            }
        }
    }


}