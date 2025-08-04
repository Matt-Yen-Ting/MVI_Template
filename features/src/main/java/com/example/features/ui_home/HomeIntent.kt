package com.example.features.ui_home

sealed class HomeIntent {
    data class Logout(
        val showLoading: Boolean = false,
        val logoutSuccess: Boolean = false,
        val logoutFail: Boolean = false
    ) : HomeIntent()
}