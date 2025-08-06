package com.example.domain.repository.account

import com.example.domain.model.entites.AnnouncementResponseDto
import com.example.domain.model.entites.LoginResponseDto
import com.example.domain.repository.api.ApiRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiRepository: ApiRepository
) : AccountRepository {

    override suspend fun login(account: String): LoginResponseDto =
        apiRepository.login(account)


    override suspend fun logout() = apiRepository.logout()
}