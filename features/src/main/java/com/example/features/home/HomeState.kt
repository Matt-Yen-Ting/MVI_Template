package com.example.features.home

data class HomeState(
    val showLoading: Boolean = false,
    val logoutSuccess: Boolean = false,
    val logoutFail: Boolean = false
)