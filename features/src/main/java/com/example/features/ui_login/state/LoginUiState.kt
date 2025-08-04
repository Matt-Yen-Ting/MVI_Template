package com.example.features.ui_login.state


data class LoginUiState(
    val showLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val loginFail: Boolean = false
)

