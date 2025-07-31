package com.example.mvi_architecture.ui_login.state

import com.example.mvi_architecture.data.ResponseStatus

data class LoginUiState(
    val showLoading: Boolean = false,
    val loginStatus: ResponseStatus = ResponseStatus.INIT,
)

