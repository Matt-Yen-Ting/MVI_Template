package com.example.domain.repository.account

import com.example.domain.model.entites.LoginResponseDto

interface AccountRepository {

    suspend fun login(account: String): LoginResponseDto

    suspend fun logout()
}