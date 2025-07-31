package com.example.mvi_architecture.ui_home

import com.example.mvi_architecture.data.ResponseStatus

sealed class HomeIntent {
    data class Logout(
        val showLoading: Boolean = false,
        val logoutStatus: ResponseStatus = ResponseStatus.INIT
    ) : HomeIntent()
}