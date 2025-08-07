package com.example.features.login.state


data class LoginUiState(
    val showLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val loginFail: Boolean = false
)

