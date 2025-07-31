package com.example.mvi_architecture.ui_login

import com.example.mvi_architecture.data.ResponseStatus


sealed class LoginIntent {
    data class Login(
        val account: String,
        val showLoading: Boolean = false,
        val loginState: ResponseStatus = ResponseStatus.INIT,
    ) : LoginIntent()

    data class ResetState(
        val account: String = "",
        val showLoading: Boolean = false,
        val loginState: ResponseStatus = ResponseStatus.INIT,
    ) : LoginIntent()
}