package com.example.features.login

sealed class LoginIntent {
    data class Login(
        val account: String,
        val showLoading: Boolean = false,
        val loginSuccess: Boolean = false,
        val loginFail:Boolean = false
    ) : LoginIntent()

    data class ResetState(
        val account: String = "",
        val showLoading: Boolean = false,
        val loginSuccess: Boolean = false,
        val loginFail:Boolean = false
    ) : LoginIntent()
}