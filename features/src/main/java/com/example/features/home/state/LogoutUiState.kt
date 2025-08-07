package com.example.features.home.state


data class LogoutUiState(
    val showLoading: Boolean = false,
    val logoutSuccess: Boolean = false,
    val logoutFail: Boolean = false
)