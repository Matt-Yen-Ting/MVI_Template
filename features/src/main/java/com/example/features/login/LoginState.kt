package com.example.features.login

data class LoginState(
    val showLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val loginFail: Boolean = false
)