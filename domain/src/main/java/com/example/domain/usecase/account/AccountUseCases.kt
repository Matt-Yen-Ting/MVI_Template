package com.example.domain.usecase.account

import javax.inject.Inject

data class AccountUseCases @Inject constructor(
    val login: Login,
    val logout: Logout,
    val setToken: SetToken,
    val getToken: GetToken
)
