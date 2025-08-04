package com.example.features.ui_home.state


data class LogoutUiState(
    val showLoading: Boolean = false,
    val logoutSuccess: Boolean = false,
    val logoutFail: Boolean = false
)