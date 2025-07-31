package com.example.mvi_architecture.ui_home.state

import com.example.mvi_architecture.data.ResponseStatus

data class LogoutUiState(
    val showLoading: Boolean = false,
    val logoutStatus: ResponseStatus = ResponseStatus.INIT
)