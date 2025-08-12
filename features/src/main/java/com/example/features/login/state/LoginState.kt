package com.example.features.login.state

data class LoginState(
    val showLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val loginFail: Boolean = false
)